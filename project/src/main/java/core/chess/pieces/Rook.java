package core.chess.pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.chess.Board;
import core.chess.Square;

public class Rook extends Piece {
    private boolean canCastle = true;

    public Rook(Square square, boolean isWhite){
        super(square, isWhite);
    }

    public Rook(boolean isWhite){
        super(isWhite);
    }

    public Character getChar() {
        return 'R';
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

        int xdir = Integer.compare(newCords[0], oldCords[0]); // retning vertikalt 
        int ydir = Integer.compare(newCords[1], oldCords[1]); // retning horisontalt

        List<int[]> cordinatesBetween = new ArrayList<int[]>();

        int[] tempCords = oldCords.clone();

        if(oldCords[0] == newCords[0]) {// horisonalt like -> endre 
            tempCords[1] += ydir;
            while(!Arrays.equals(tempCords, newCords)) {
                cordinatesBetween.add(tempCords.clone());
                tempCords[1] += ydir;
            }
        } else if(oldCords[1] == newCords[1]) {// vertikalt like
            tempCords[0] += xdir;
            while(!Arrays.equals(tempCords, newCords)) {
                cordinatesBetween.add(tempCords.clone());
                tempCords[1] += xdir;
            }
        } else {
            return false;
        }

        for (int[] cordinate : cordinatesBetween) {
            if(board.getSquare(cordinate).getPiece() != null) {
                return false;
            }
        }

        return true;
    }

    public boolean canCastle() {
        return canCastle;
    }

    public void removeCastle() {
        canCastle = false;
      }
}