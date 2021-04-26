package core.chess.pieces;

import core.chess.Board;
import core.chess.Square;

public class Knight extends Piece {

    double knightMoveDist = Math.hypot(2,1);

    public Knight(Square square, boolean isWhite){
        super(square, isWhite);
    }

    public Knight(boolean isWhite){
        super(isWhite);
    }

    public Character getChar() {
        return 'N';
    }

    public void move(String cordinates, Board board) {
        Square targetSquare = board.getSquare(cordinates);

            if(targetSquare.getPiece() != null) {
                capture(targetSquare);
            }

            targetSquare.setPiece(this);
    }

    public boolean canMoveTo(String cordinates, Board board) {
        int[] oldCords = getSquare().getCordinates();
        int[] newCords = Board.convertCordinates(cordinates);

        Piece capturingPiece = board.getSquare(newCords).getPiece();

        if(oldCords == newCords || (capturingPiece != null && capturingPiece.isWhite() == isWhite())) {
            return false;
        }

        double moveDist = Math.hypot((Math.abs(oldCords[0]-newCords[0])), (Math.abs(oldCords[1]-newCords[1])));

        if (moveDist != knightMoveDist) {
            return false;
        }
        return true;
    }
}