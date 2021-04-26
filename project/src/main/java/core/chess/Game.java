package core.chess;

import core.chess.pieces.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Game {
  private Board board = new Board();
  private boolean whiteToMove = true;

  private String currentMove;
  private List<String> moves = new ArrayList<String>(); // for storage

  private List<Piece> whitePieces = new ArrayList<Piece>();
  private List<Piece> blackPieces = new ArrayList<Piece>();

  public Board getBoard() {
    return board;
  }

  public List<Piece> getPieces() {
    if (whiteToMove) {
      return whitePieces;
    } else {
      return blackPieces;
    }
  }

  public boolean isWhiteToMove() {
    return whiteToMove;
  }

  public List<Piece> getActivePieces() {
    return board.getActivePieces(whiteToMove);
  }

  public List<Piece> getAllPieces() {
    List<Piece> pieces = new ArrayList<>();
    pieces.addAll(whitePieces);
    pieces.addAll(blackPieces);
    return pieces;
  }

  public List<Piece> getAllActivePieces() {
    return getAllPieces()
      .stream()
      .filter(p -> p.isActive())
      .collect(Collectors.toList());
  }

  public String move(String stringMove) throws CloneNotSupportedException {
    Move move = new Move(stringMove);
    Optional<Piece> optPiece = null;

    if (
      (move.getPiece() != 'O' || move.getPiece() != 'o') &&
      !Board.validateCordinates(move.getTargetCordinates()) ||
      move.validCapture(board)
    ) {
      return "invalid castling";
    }

    Board oldBoard = (Board) board.clone();

    try {
      switch (move.getPiece()) {
        case 'P':
          optPiece =
            getActivePieces()
              .stream()
              .filter(
                p ->
                  p instanceof Pawn &&
                  p.canMoveTo(move.getTargetCordinates(), board) &&
                  (
                    (
                      move.getColumn() == null &&
                      p.getCordinates()[0] == Board.getColumn(move.getColumn())
                    ) ||
                    move.getColumn() == "x"
                  )
              )
              .findFirst();

          break;
        case 'K':
          optPiece =
            getActivePieces()
              .stream()
              .filter(
                p ->
                  p instanceof King &&
                  p.canMoveTo(move.getTargetCordinates(), board) &&
                  (
                    (
                      move.getColumn() == null &&
                      p.getCordinates()[0] == Board.getColumn(move.getColumn())
                    ) ||
                    move.getColumn() == "x"
                  )
              )
              .findFirst();
          break;
        case 'Q':
          optPiece =
            getActivePieces()
              .stream()
              .filter(
                p ->
                  p instanceof Queen &&
                  p.canMoveTo(move.getTargetCordinates(), board) &&
                  (
                    (
                      move.getColumn() == null &&
                      p.getCordinates()[0] == Board.getColumn(move.getColumn())
                    ) ||
                    move.getColumn() == "x"
                  )
              )
              .findFirst();
          break;
        case 'B':
          optPiece =
            getActivePieces()
              .stream()
              .filter(
                p ->
                  p instanceof Bishop &&
                  p.canMoveTo(move.getTargetCordinates(), board) &&
                  (
                    (
                      move.getColumn() == null &&
                      p.getCordinates()[0] == Board.getColumn(move.getColumn())
                    ) ||
                    move.getColumn() == "x"
                  )
              )
              .findFirst();
          break;
        case 'N':
          optPiece =
            getActivePieces()
              .stream()
              .filter(
                p ->
                  p instanceof Knight &&
                  p.canMoveTo(move.getTargetCordinates(), board) &&
                  (
                    (
                      move.getColumn() == null &&
                      p.getCordinates()[0] == Board.getColumn(move.getColumn())
                    ) ||
                    move.getColumn() == "x"
                  )
              )
              .findFirst();
          break;
        case 'R':
          optPiece =
            getActivePieces()
              .stream()
              .filter(
                p ->
                  p instanceof Rook &&
                  p.canMoveTo(move.getTargetCordinates(), board) &&
                  (
                    (
                      move.getColumn() == null &&
                      p.getCordinates()[0] == Board.getColumn(move.getColumn())
                    ) ||
                    move.getColumn() == "x"
                  )
              )
              .findFirst();
          break;
        case 'O':
          break;
        case 'o':
          break;
      }
    } catch (Exception e) {
      board = oldBoard;
      return e.getMessage();
    }

    if (optPiece != null && optPiece.isPresent()) {
      Piece movingPiece = optPiece.get();
      System.out.println(
        "moving " + movingPiece.getChar() + movingPiece.getStringCordinates()
      );
      movingPiece.move(move.getTargetCordinates(), board);
      if (board.getKing(movingPiece.isWhite()).IsInCheck(board)) {
        board = oldBoard;
        return "move failed";
      } else {
        whiteToMove = !whiteToMove;
        recordMove(move.toString());
        return "move made";
      }
    }
    return "piece doesn exist";
  }

  private void recordMove(String move) {
    if (!whiteToMove) {
      currentMove = move + " ";
    } else {
      moves.add(currentMove + move);
      currentMove = "";
    }
  }

  public void newGame() {
    board = new Board();

    //white pieces
    for (int i = 0; i < 8; i++) {
      board.getSquare(new int[] { i, 6 }).setPiece(new Pawn(true));
    }
    board.getSquare("a1").setPiece(new Rook(true));
    board.getSquare("b1").setPiece(new Knight(true));
    board.getSquare("c1").setPiece(new Bishop(true));
    board.getSquare("d1").setPiece(new Queen(true));
    board.getSquare("e1").setPiece(new King(true));
    board.getSquare("f1").setPiece(new Bishop(true));
    board.getSquare("g1").setPiece(new Knight(true));
    board.getSquare("h1").setPiece(new Rook(true));

    //black pieces
    for (int i = 0; i < 8; i++) {
      board.getSquare(new int[] { i, 1 }).setPiece(new Pawn(false));
    }
    board.getSquare("a8").setPiece(new Rook(false));
    board.getSquare("b8").setPiece(new Knight(false));
    board.getSquare("c8").setPiece(new Bishop(false));
    board.getSquare("d8").setPiece(new Queen(false));
    board.getSquare("e8").setPiece(new King(false));
    board.getSquare("f8").setPiece(new Bishop(false));
    board.getSquare("g8").setPiece(new Knight(false));
    board.getSquare("h8").setPiece(new Rook(false));

    //resetting the rest of the gameVariables
    whiteToMove = true;
    currentMove = "";
    whitePieces = board.getActiveWhitePieces();
    blackPieces = board.getActiveBlackPieces();
  }

  public void LoadGame(List<String> moves) {
    newGame();
    for (String move : moves) {
      String[] turn = move.split(" ");
      try {
        move(turn[0]);
        if(turn.length > 1) {
          move(turn[1]);
        }
      } catch (CloneNotSupportedException e) {
        System.out.println("huston we have problem");
      }
    }
  }

  public List<String> getMoves() {
    List<String> currentMoves = new ArrayList<String>();
    currentMoves.addAll(moves);
    if(currentMove != ""){
      currentMoves.add(currentMove);
    }
    return currentMoves;
  }
}
