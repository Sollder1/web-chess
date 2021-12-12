package de.sollder1.webchess.backend.game.lobby

import de.sollder1.webchess.backend.game.player.PlayerRegistry
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.stream.Collectors

class LobbyRegistry {

    companion object {
        private var INSTANCE: LobbyRegistry? = null
        fun getInstance(): LobbyRegistry {
            if (INSTANCE == null) {
                INSTANCE = LobbyRegistry()
            }
            return INSTANCE as LobbyRegistry
        }
    }

    private val state = ConcurrentHashMap<String, Lobby>()

    fun query(query: String): List<Lobby> {
        return state.entries.stream().filter { entry ->
            entry.value.name.lowercase().matches(Regex(".*" + query.lowercase() + ".*"))
        }.map { entry -> entry.value }.collect(Collectors.toList())
    }

    fun get(id: String): Lobby? {
        return state[id]
    }

    fun add(payload: Lobby): Lobby {
        val lobby = Lobby()
        lobby.id = UUID.randomUUID().toString()
        lobby.name = payload.name
        if (payload.players.size != 1) {
            throw RuntimeException("Player beim erstellen der Lobby nicht angegeben!")
        }

        val creatingPlayer = PlayerRegistry.getInstance().getPlayer(payload.players[0].player.id)
        if (creatingPlayer == null) {
            throw RuntimeException("Player existiert nicht!")
        }

        lobby.players = listOf(LobbyToPlayer(creatingPlayer))
        state[lobby.id] = lobby

        return lobby
    }


}