package core.chess.pieces;

import core.chess.Board;
import core.chess.Square;

public class Pawn extends Piece {

  public Pawn(Square square, boolean isWhite) {
    super(square, isWhite);
  }

  public Pawn(boolean isWhite){
    super(isWhite);
}

public Character getChar() {
  return 'P';
}

  private int getDirection() {
    if (isWhite()) {
      return -1;
    } else {
      return 1;
    }
  }

  public boolean move(String cordinates, Board board) {
    if (this.canMoveTo(cordinates, board)) {
      Square targetSquare = board.getSquare(cordinates);

      if (targetSquare.getPiece() != null) {
        capture(targetSquare);
      }

      targetSquare.setPiece(this);
      return true;
    }
    return false;
  }

  public boolean canMoveTo(String cordinates, Board board) {
    int[] oldCords = getSquare().getCordinates();
    int[] newCords = Board.convertCordinates(cordinates);

    Piece capturingPiece = board.getSquare(newCords).getPiece();

    if (
      oldCords == newCords ||
      (capturingPiece != null && capturingPiece.isWhite() == isWhite())
    ) {
      return false;
    }

    if (
      capturingPiece != null &&
      Math.abs(oldCords[0] - newCords[0]) ==
      Math.abs(oldCords[1] - newCords[1]) &&
      Math.abs(oldCords[0] - newCords[0]) == 1 &&
      capturingPiece.isWhite() != isWhite()
    ) { //capture
      return true;
    } else if (
      capturingPiece == null &&
      (oldCords[1] + getDirection() == newCords[1]) &&
      oldCords[0] == newCords[0]
    ) { // move one ahead
      return true;
    } else if (
      capturingPiece == null &&
      (oldCords[1] + getDirection() * 2 == newCords[1]) &&
      ((isWhite() && oldCords[1] == 6) || (!isWhite() && oldCords[1] == 1)) &&
      board
        .getSquare(new int[] { oldCords[0], oldCords[1] + getDirection() })
        .getPiece() ==
      null &&
      oldCords[0] == newCords[0]
    ) { // move one ahead
      return true;
    }

    return false;
  }
}
