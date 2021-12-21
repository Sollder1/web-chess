package de.sollder1.webchess.backend.game.engine

enum class Color {
    WHITE, BLACK;



    companion object {
        public fun notColor(color: Color): Color {
            return if (color == BLACK) {
                WHITE;
            } else {
                BLACK;
            }
        }
    }

    fun isFigureCodeOfColor(figureCode: Byte): Boolean {

        if (figureCode > 0 && name == WHITE.name) {
            return true;
        }

        if (figureCode < 0 && name == BLACK.name) {
            return true;
        }

        return false;

    }

}