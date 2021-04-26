package core.chess.pieces;

import core.chess.Board;

public interface IPiece {
    public boolean canMoveTo(String cordinates, Board board);
    public void move(String cordinates, Board board);
}