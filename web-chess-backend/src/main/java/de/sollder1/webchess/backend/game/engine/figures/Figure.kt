package de.sollder1.webchess.backend.game.engine.figures

import de.sollder1.webchess.backend.game.engine.Coordinate
import de.sollder1.webchess.backend.game.engine.GameField

abstract class Figure(protected val gameField: GameField) {

    abstract val id: String

    abstract fun isMoveValid(target: Coordinate): Boolean

    abstract fun getValidMoves(): List<Coordinate>


}
