package de.sollder1.webchess.backend.game.lobby

import de.sollder1.webchess.backend.api.lobby.LobbyDeltaPayload
import de.sollder1.webchess.backend.game.WebChessException
import de.sollder1.webchess.backend.game.engine.Color
import de.sollder1.webchess.backend.game.engine.Coordinate
import de.sollder1.webchess.backend.game.engine.Move
import de.sollder1.webchess.backend.game.engine.figures.Figure
import de.sollder1.webchess.backend.game.engine.figures.FigureApi
import de.sollder1.webchess.backend.game.engine.figures.King
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
            entry.value.name.lowercase().matches(Regex(".*${query.lowercase()}.*"))
        }.map { entry -> entry.value }.collect(Collectors.toList())
    }

    fun get(id: String): Lobby? {
        //TODO: permission checks...!
        return state[id]
    }

    fun add(payload: Lobby): Lobby {
        val lobby = Lobby()
        lobby.id = UUID.randomUUID().toString()
        lobby.name = payload.name
        if (payload.players.size != 1) {
            throw WebChessException("Player beim erstellen der Lobby nicht angegeben!")
        }

        val creatingPlayer = PlayerRegistry.getInstance().getPlayer(payload.players[0].player.id)
        if (creatingPlayer == null) {
            throw WebChessException("Player existiert nicht!")
        }

        lobby.players = ArrayList(listOf(LobbyToPlayer(creatingPlayer, Color.WHITE)))
        lobby.gameField = FigureApi.getStartGameField();
        state[lobby.id] = lobby

        return lobby
    }

    fun joinOrReconnect(lobbyId: String, playerId: String): Lobby {
        val lobby = getLobby(lobbyId)
        synchronized(lobby) {
            val player = PlayerRegistry.getInstance().getPlayer(playerId)
            if (player == null) {
                throw  WebChessException("Player by '$playerId' nicht gefunden!")
            }
            if (lobby.players.size == 2) {
                val playerFromLobby = getPlayerFromLobby(lobby, playerId)
                if (playerFromLobby.isPresent) {
                    playerFromLobby.get().isConnected = true
                } else {
                    throw  WebChessException("Lobby schon voll!")
                }
            } else if (lobby.players.size == 1) {
                val newPlayer = LobbyToPlayer(player, Color.BLACK);
                lobby.players.add(newPlayer)
                lobby.players.stream().filter { p -> p.player.id != newPlayer.player.id }
                    .forEach { p -> p.updates.add(LobbyPollData(newPlayer)) }
            } else {
                throw  WebChessException("Lobby schon voll!")
            }
            return lobby
        }
    }

    fun updateBeforeStart(delta: LobbyDeltaPayload): Lobby {
        val lobby = getLobby(delta.lobbyId)

        synchronized(lobby) {
            if (lobby.isStarted) {
                throw  WebChessException("Lobby '${lobby.name}' bereits gestartet!")
            }

            if (lobby.players.size != 2) {
                throw  WebChessException("Lobby '${lobby.name}' noch nicht vollständig!")
            }

            lobby.isStarted = delta.isStart
            lobby.players.forEach { player -> player.updates.add(LobbyPollData(lobby.isStarted)) }

            return lobby;
        }
    }


    fun getPossibleMoves(lobbyId: String, playerId: String, coordinate: Coordinate): List<Coordinate> {

        val lobby = getLobby(lobbyId)
        val player = getPlayerFromLobby(lobby, playerId).orElseThrow { WebChessException("Player not present") }

        if (!player.isYourTurn) {
            return Collections.emptyList();
        }

        if (!lobby.isStarted) {
            return Collections.emptyList();
        }

        val field = lobby.gameField
        val figureCode = lobby.gameField[coordinate.y][coordinate.x]

        if (!player.playerColor.isFigureCodeOfColor(figureCode)) {
            return Collections.emptyList();
        }

        return FigureApi.getBehaviourModelById(figureCode)
            .getValidMoves(coordinate, field, false)
    }

    fun move(lobbyId: String, playerId: String, move: Move) {
        val lobby = getLobby(lobbyId)

        val player = getPlayerFromLobby(lobby, playerId).orElseThrow { WebChessException("Spieler nicht in Lobby!") }

        if (!player.isYourTurn) {
            throw WebChessException("Spieler nicht dran!")
        }

        if (!lobby.isStarted) {
            throw WebChessException("Spiel noch nicht gestartet")
        }

        val field = lobby.gameField
        val figureId = field[move.from.y][move.from.x];

        if (!player.playerColor.isFigureCodeOfColor(figureId)) {
            throw WebChessException("Gegnerische Figur kann nicht bewegt werden...!!")
        }

        val model = FigureApi.getBehaviourModelById(figureId);

        if (model.isMoveValid(move, field, false)) {

            val targetValue = field[move.to.y][move.to.x];
            field[move.from.y][move.from.x] = Figure.EM_F
            field[move.to.y][move.to.x] = figureId

            if (King.kingInCheck(field, player.playerColor)) {
                field[move.from.y][move.from.x] = figureId
                field[move.to.y][move.to.x] = targetValue
                return
            }


            player.isYourTurn = false
            val newPlayer = lobby.players.stream().filter { p -> p.player.id != player.player.id }
                .findAny().get()
            newPlayer.isYourTurn = true
            lobby.addMove(move)
            lobby.players.forEach { p -> p.updates.add(LobbyPollData(move, newPlayer.player.id)) }
        }

    }


    fun pollUpdates(lobbyId: String, playerId: String): Optional<LobbyPollData> {
        val player = getPlayerFromLobby(getLobby(lobbyId), playerId)
            .orElseThrow { WebChessException("Spieler nicht in Lobby!") }
        return Optional.ofNullable(player.updates.poll())
    }

    private fun getLobby(lobbyId: String): Lobby {
        return get(lobbyId) ?: throw  WebChessException("Lobby by '${lobbyId}' nicht gefunden!")
    }

    //Private Functions:
    private fun getPlayerFromLobby(lobby: Lobby, playerId: String): Optional<LobbyToPlayer> {
        return lobby.players.stream().filter { lobbyPlayer -> lobbyPlayer.player.id == playerId }.findAny()
    }

}