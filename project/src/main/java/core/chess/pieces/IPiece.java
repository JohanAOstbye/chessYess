package core.chess.pieces;

import core.chess.Board;

public interface IPiece {
    public boolean canMoveTo(String cordinates, Board board);
    public boolean move(String cordinates, Board board);
}