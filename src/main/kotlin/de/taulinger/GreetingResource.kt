package de.taulinger

import com.fasterxml.jackson.annotation.JsonProperty
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("greeting")
class GreetingResource {

    data class GreetingJson(@JsonProperty("greeting") val greeting: String)

    @Inject
    lateinit var greetingService: IGreetingService

    @GET
    fun get(@QueryParam("name") name: String?): String = this.greetingService.getGreetings(name)

    @Path("json")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAsJson(@QueryParam("name") name: String?): GreetingJson {
        return GreetingJson(this.greetingService.getGreetings(name))
    }
}