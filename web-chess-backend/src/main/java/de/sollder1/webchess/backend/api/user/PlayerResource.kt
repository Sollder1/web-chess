package de.sollder1.webchess.backend.api.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import de.sollder1.webchess.backend.game.player.PlayerRegistry
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import java.lang.Exception

@Path("/player")
class PlayerResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{player-id}")
    fun getPlayer(@PathParam("player-id") userId: String): Response {
        val player = PlayerRegistry.getInstance().getPlayer(userId);
        return Response.ok().entity(jacksonObjectMapper().writeValueAsString(player)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun registerPlayer(payload: String): Response {
        try {
            val payloadBean: PlayerPayload = jacksonObjectMapper().readValue(payload);
            val player = PlayerRegistry.getInstance().createNewPlayer(payloadBean);
            return Response.ok().entity(jacksonObjectMapper().writeValueAsString(player)).build();
        }catch (ex: Exception) {
            ex.printStackTrace();
        }
        return Response.ok().build();
    }

}