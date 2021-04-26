package core.chess.pieces;

import core.chess.Board;
import core.chess.Square;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class King extends Piece {
  private boolean canCastle = true;

  public King(Square square, boolean isWhite) {
    super(square, isWhite);
  }

  public King(boolean isWhite) {
    super(isWhite);
  }

  public Character getChar() {
    return 'K';
  }

  public boolean IsInCheck(Board board) {
    return IsInCheck(getStringCordinates(), board);
  }

  public boolean IsInCheck(String cordinates, Board board) {
    List<Piece> threateningPieces = board.getActivePieces(!this.isWhite())
    .stream()
    .filter(p -> p.canMoveTo(this.getStringCordinates(), board))
    .collect(Collectors.toList());
    if(threateningPieces.isEmpty()) {
      return false;
    }
    Optional<Piece> threateningPiece = threateningPieces
      .stream()
      .findFirst();
    return threateningPiece.isPresent();
  }

  @Override
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

    int distance = Math.max(
      Math.abs(oldCords[0] - newCords[0]),
      Math.abs(oldCords[1] - newCords[1])
    );

    if (distance > 1) {
      return false;
    }

    if(IsInCheck(cordinates, board)) {
      return false;
    }
    return true;
  }

  @Override
  public boolean move(String cordinates, Board board) {
    if (this.canMoveTo(cordinates, board)) {
      canCastle = false;
      Square targetSquare = board.getSquare(cordinates);

      if (targetSquare.getPiece() != null) {
        capture(targetSquare);
      }

      targetSquare.setPiece(this);
      return true;
    }
    return false;
  }

  public boolean canCastle() {
    return canCastle;
  }

  public boolean castle(boolean isQueenSide) {
    if (isQueenSide) {}
    return false;
  }

  public boolean isCheckMate(Board board, Piece threateningPiece) {
    Board newBoard = board;

    try {
      newBoard = (Board) board.clone();
    } catch (CloneNotSupportedException e) {
      System.out.println("clone failed");
      return false;
    }

    //move away
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (
          this.canMoveTo(Board.CordinatesToString(new int[] { i, j }), board)
        ) {
          return false;
        }
      }
    }

    //block or capture
    List<int[]> cordinatesBetween = new ArrayList<int[]>();

    int[] tempCords = getCordinates();
    int[] newCords = threateningPiece.getCordinates();

    cordinatesBetween.add(newCords); // captureing the threatening piece

    int xdir = Integer.compare(getCordinates()[0], newCords[0]); // retning vertikalt
    int ydir = Integer.compare(getCordinates()[1], newCords[1]); // retning horisontalt

    if (getCordinates()[0] == newCords[0]) { // horisonalt like -> endre
      tempCords[1] += ydir;
      while (!Arrays.equals(tempCords, newCords)) {
        cordinatesBetween.add(tempCords.clone());
        tempCords[1] += ydir;
      }
    } else if (getCordinates()[1] == newCords[1]) { // vertikalt like
      tempCords[0] += xdir;
      while (!Arrays.equals(tempCords, newCords)) {
        cordinatesBetween.add(tempCords.clone());
        tempCords[1] += xdir;
      }
    } else if (
      Math.abs(getCordinates()[0] - newCords[0]) ==
      Math.abs(getCordinates()[1] - newCords[1])
    ) { //diagonal
      tempCords[0] += ydir;
      tempCords[1] += xdir;
      while (!Arrays.equals(tempCords, newCords)) {
        cordinatesBetween.add(tempCords.clone());
        tempCords[0] += ydir;
        tempCords[1] += xdir;
      }
    }

    for (int[] c : cordinatesBetween) {
      for (Piece p : newBoard.getActivePieces(isWhite())) {
        if (p.canMoveTo(Board.CordinatesToString(c), newBoard)) {
          p.move(Board.CordinatesToString(c), newBoard);
          if (!newBoard.getKing(isWhite()).IsInCheck(newBoard)) {
            return false;
          }
        }
      }
    }

    return true;
  }
}
