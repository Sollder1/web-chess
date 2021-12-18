package de.sollder1.webchess.backend.api.lobby

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.sollder1.webchess.backend.game.lobby.LobbyRegistry
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/lobbies")
class LobbyResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun gets(@QueryParam("query") query: String): Response {
        println("GET lobbies")
        val lobbies = LobbyRegistry.getInstance().query(query)
        return Response.ok().entity(jacksonObjectMapper().writeValueAsString(lobbies)).build()
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun post(payload: String): Response {
        println("POST lobbies")
        val lobby = LobbyRegistry.getInstance().add(jacksonObjectMapper().readValue(payload))
        return Response.ok().entity(jacksonObjectMapper().writeValueAsString(lobby)).build()
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    fun update(payload: String): Response {
        println("Update lobby before start")
        val lobby = LobbyRegistry.getInstance().updateBeforeStart(jacksonObjectMapper().readValue(payload))
        return Response.ok().entity(jacksonObjectMapper().writeValueAsString(lobby)).build()
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{lobby-id}")
    fun get(@PathParam("lobby-id") lobbyId: String): Response {
        println("GET lobby")
        val lobby = LobbyRegistry.getInstance().get(lobbyId)
        return Response.ok().entity(jacksonObjectMapper().writeValueAsString(lobby)).build()
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{lobby-id}/players/{player-id}")
    fun join(@PathParam("lobby-id") lobbyId: String, @PathParam("player-id") playerId: String): Response {
        println("PUT lobby join")
        val lobby = LobbyRegistry.getInstance().joinOrReconnect(lobbyId, playerId)
        return Response.ok().entity(jacksonObjectMapper().writeValueAsString(lobby)).build()
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{lobby-id}/updates/{player-id}")
    fun pollUpdates(@PathParam("lobby-id") lobbyId: String, @PathParam("player-id") playerId: String): Response {
        println("POLLING updates")
        //TODO...
        return Response.ok().entity(jacksonObjectMapper().writeValueAsString(null)).build()
    }

}