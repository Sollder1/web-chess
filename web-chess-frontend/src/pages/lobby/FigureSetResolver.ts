export default class FigureSetResolver {





    public static getFigure(theme: string, code: number): string {

        switch (code) {
            case 2: return `/themes/${theme}/whitePawn.png`
            case -2: return `/themes/${theme}/blackPawn.png`
            case 10: return `/themes/${theme}/whiteRook.png`
            case -10: return `/themes/${theme}/blackRook.png`
            case 7: return `/themes/${theme}/whiteBishop.png`
            case -7: return `/themes/${theme}/blackBishop.png`
            case 6: return `/themes/${theme}/whiteKnight.png`
            case -6: return `/themes/${theme}/blackKnight.png`
            case 18: return `/themes/${theme}/whiteQueen.png`
            case -18: return `/themes/${theme}/blackQueen.png`
            case 127: return `/themes/${theme}/whiteKing.png`
            case -127: return `/themes/${theme}/blackKing.png`
            //TODO: add nothing
            default: return `/themes/nothing.png`;
        }
    }

}