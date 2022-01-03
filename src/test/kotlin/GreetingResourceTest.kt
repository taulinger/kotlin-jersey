import de.taulinger.GreetingResource
import de.taulinger.IGreetingService
import jakarta.ws.rs.core.Application
import org.glassfish.jersey.internal.inject.AbstractBinder
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import jakarta.ws.rs.core.Response
import kotlin.test.assertEquals


class GreetingResourceTest : JerseyTest() {

    val greetingService : IGreetingService =  Mockito.mock(IGreetingService::class.java)

    override fun configure(): Application {
        val resourceConfig = ResourceConfig(GreetingResource::class.java)
        resourceConfig.register(object : AbstractBinder() {
            override fun configure() {
                bind(greetingService).to(IGreetingService::class.java)
            }
        })
        return resourceConfig
    }

    @Test
    fun `when query param 'name' is missing, it should provide a fallback message`() {
        val expectedResult = "unknown"
        Mockito.`when`(greetingService.getGreetings(null)).thenReturn(expectedResult)

        val response = target("greeting")
            .request()
            .get()

        assertEquals(Response.Status.OK.statusCode, response.status)
        assertEquals(expectedResult, response.readEntity(String::class.java))
    }

    @Test
    fun `it should provide a greeting message`() {
        val name = "foo"
        val expectedResult = "Hello Foo!"
        Mockito.`when`(greetingService.getGreetings(name)).thenReturn(expectedResult)

        val response = target("greeting")
            .queryParam("name", name)
            .request()
            .get()

        assertEquals(Response.Status.OK.statusCode, response.status)
        assertEquals(expectedResult, response.readEntity(String::class.java))
    }

    @Test
    fun `when query param 'name' is missing, it should provide a fallback message as JSON`() {
        val expectedResult = "unknown"
        Mockito.`when`(greetingService.getGreetings(null)).thenReturn(expectedResult)

        val response = target("greeting")
            .path("json")
            .request()
            .get()

        assertEquals(Response.Status.OK.statusCode, response.status)
        assertEquals(GreetingResource.GreetingJson(expectedResult), response.readEntity(GreetingResource.GreetingJson::class.java))
    }

    @Test
    fun `it should provide a greeting message as JSON`() {
        val name = "foo"
        val expectedResult = "Hello Foo!"
        Mockito.`when`(greetingService.getGreetings(name)).thenReturn(expectedResult)

        val response = target("greeting")
            .path("json")
            .queryParam("name", name)
            .request()
            .get()

        assertEquals(Response.Status.OK.statusCode, response.status)
        assertEquals(GreetingResource.GreetingJson(expectedResult), response.readEntity(GreetingResource.GreetingJson::class.java))
    }
}