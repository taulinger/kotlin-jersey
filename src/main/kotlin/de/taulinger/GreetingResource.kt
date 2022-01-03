package de.taulinger

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType

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