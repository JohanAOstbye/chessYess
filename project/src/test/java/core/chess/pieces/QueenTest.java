package core.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.chess.Board;

public class QueenTest {
  Board board = new Board();
  Queen queen = new Queen(board.getSquare("c6"), true);

  @BeforeEach
  void setUp() {
    board = new Board();
    queen = new Queen(board.getSquare("c6"), true);
  }

  @Test
  void testCanMoveToAndCaputre() {
    Pawn blackPawnB7 = new Pawn(board.getSquare("b7"), false);
    Pawn whitePawnA6 = new Pawn(board.getSquare("a6"), true);

    //caputre
    assertTrue(queen.canMoveTo("b7", board));
    assertFalse(queen.canMoveTo("a6", board));

  }

  @Test
  void testCanMoveTo() {
    Pawn whitePawnA6 = new Pawn(board.getSquare("a6"), true);
    Pawn whitePawnE2 = new Pawn(board.getSquare("e2"), true);
    Pawn whitePawnB6 = new Pawn(board.getSquare("b6"), true);
    //move simple move
    assertTrue(queen.canMoveTo("c1", board));
    assertTrue(queen.canMoveTo("h6", board));
    assertTrue(queen.canMoveTo("f3", board));
    assertTrue(queen.canMoveTo("d7", board));

    //blocked
    assertFalse(queen.canMoveTo("b6", board));
    assertFalse(queen.canMoveTo("a6", board));

    //edgecases
    assertFalse(queen.canMoveTo("d8", board));
    assertFalse(queen.canMoveTo("g1", board));
    assertFalse(queen.canMoveTo("b8", board));
  }
}
