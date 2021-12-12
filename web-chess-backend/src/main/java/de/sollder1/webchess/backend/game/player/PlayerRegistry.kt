package de.sollder1.webchess.backend.game.player

import java.util.*
import java.util.concurrent.ConcurrentHashMap

class PlayerRegistry {

    companion object {
        private var INSTANCE: PlayerRegistry? = null
        fun getInstance(): PlayerRegistry {
            if (INSTANCE == null) {
                INSTANCE = PlayerRegistry()
            }
            return INSTANCE as PlayerRegistry
        }
    }

    private val state = ConcurrentHashMap<String, Player>()

    fun getPlayer(id: String): Player? {
        return state[id]
    }

    fun createNewPlayer(payload: Player): Player? {

        var id = payload.id
        if (id == null || state[id] == null) {
            id = UUID.randomUUID().toString()
            state[id] = Player(id, payload.userName)
        }
        return state[id]
    }


}