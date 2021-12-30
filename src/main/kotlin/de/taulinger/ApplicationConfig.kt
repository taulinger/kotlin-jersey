package de.taulinger

import org.glassfish.jersey.internal.inject.AbstractBinder
import org.glassfish.jersey.server.ResourceConfig
import javax.ws.rs.ApplicationPath

@ApplicationPath("/api/*")
class ApplicationConfig() : ResourceConfig() {

    init {
        packages("de.taulinger")
        register(object : AbstractBinder() {
            override fun configure() {
                bind(GreetingService::class.java).to(IGreetingService::class.java)
            }
        })
    }
}