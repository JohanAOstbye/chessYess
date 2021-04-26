package core.chess;

import core.chess.pieces.Piece;

public class Square {
    private boolean isWhiteColor = true;
    private Piece piece;
    private int[] cordinates;

    public Square(boolean isWhiteColor, int[] cordinates) {
        this.isWhiteColor = isWhiteColor;
        this.cordinates = cordinates;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if(piece != null && piece.getSquare() != this){
            if(piece.getSquare() != null) {
                piece.getSquare().setPiece(null);
            }
            piece.setSquare(this);
            piece.setActive(true);
        }
    }
    public int[] getCordinates() {
        return cordinates;
    }

    public boolean getIsWhiteColor() {
        return isWhiteColor;
    }
}