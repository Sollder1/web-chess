package de.sollder1.webchess.backend.tf

import de.sollder1.webchess.backend.api.user.PlayerPayload
import de.sollder1.webchess.backend.game.player.Player

class PlayerTF {

    companion object {
        fun toPayload(player: Player?): PlayerPayload {
            val payload = PlayerPayload();
            payload.id = player?.id;
            payload.userName = player?.userName;
            return payload;
        }

        fun toBean(payload: PlayerPayload): Player {
            val player = Player();
            player.id = payload.id;
            player.userName = payload.userName;
            return player;
        }
    }

}