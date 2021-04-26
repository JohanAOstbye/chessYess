package core.chess;

import java.util.Arrays;
import java.util.List;

public class Move {
  private String targetCordinates, column, move;
  private boolean isCapture = false;
  private char piece;

  private static List<Character> pieceNotations = Arrays.asList(
    'K',
    'Q',
    'B',
    'N',
    'R'
  );

  private static String columns = "abcdefgh";

  public Move(String move) {
    this.move = move;
    //castling
    if (move == "o-o-o" || move == "O-O-O") { // queen side castle
      piece = 'O';
    } else if (move == "o-o" || move == "O-O") {
      piece = 'o';
    }
    int length = move.length();

    if (pieceNotations.contains(move.charAt(0))) {
      piece = move.charAt(0);
      isCapture = move.contains("x");
    } else {
      piece = 'P';
      if (length == 3) {
        isCapture = true;
      }
      //TODO future: add promoting
    }
    column = Character.toString(move.charAt(length-2));
    if(columns.contains(column)) {
      column = "x";
    }

    targetCordinates = move.substring(length - 2);
  }

  public boolean validCapture(Board board) {
    return (
      (isCapture && board.getSquare(targetCordinates).getPiece() != null) ||
      (
        !isCapture &&
        isCapture &&
        board.getSquare(targetCordinates).getPiece() == null
      )
    );
  }

  public boolean isCapture() {
    return isCapture;
  }

  public String getTargetCordinates() {
    return targetCordinates;
  }

  public String getColumn() {
    return column;
  }

  public char getPiece() {
    return piece;
  }

  @Override
  public String toString() {
    return move;
  }
}
