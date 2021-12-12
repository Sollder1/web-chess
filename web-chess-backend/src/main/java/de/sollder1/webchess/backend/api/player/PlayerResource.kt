package de.sollder1.webchess.backend.api.player

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.sollder1.webchess.backend.game.player.PlayerRegistry
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/player")
class PlayerResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{player-id}")
    fun getPlayer(@PathParam("player-id") userId: String): Response {
        println("GET player")
        val player = PlayerRegistry.getInstance().getPlayer(userId)
        return Response.ok().entity(jacksonObjectMapper().writeValueAsString(player)).build()
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun registerPlayer(payload: String): Response {
        println("POST player")

        try {
            val player = PlayerRegistry.getInstance().createNewPlayer(jacksonObjectMapper().readValue(payload))
            return Response.ok().entity(jacksonObjectMapper().writeValueAsString(player)).build()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return Response.ok().build()
    }

}