package core.chess.pieces;

import core.chess.Board;
import core.chess.Square;

public abstract class Piece implements IPiece {
    private boolean active = true;
    private Square square;
    private boolean isWhite;

    public Piece(Square square, boolean isWhite){
        this.square = square;
        
        square.setPiece(this);
        this.isWhite = isWhite;
    }

    public Piece(boolean isWhite){
        this.isWhite = isWhite;
        active = false;
    }

    public Character getChar() {
        return 'P';
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public Square getSquare() {
        return square;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

     public int[] getCordinates() {
         if(square == null) {
             return null;
         }
         return square.getCordinates();
     }

     public String getStringCordinates() {
        if(square == null) {
            return null;
        }
        return Board.CordinatesToString(square.getCordinates());
    }

     public void capture(Square square) {
        if(square == null) {
            return;
        }
        if(square.getPiece() != null) {
            square.getPiece().setSquare(null);
            square.getPiece().setActive(false);
        }
        square.setPiece(this);
        this.setSquare(square);
     }
}