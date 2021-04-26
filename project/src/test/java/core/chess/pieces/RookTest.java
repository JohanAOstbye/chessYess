package core.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.chess.Board;

public class RookTest {
  Board board = new Board();

  @BeforeEach
  void setUp() {
    board = new Board();
  }

  @Test
  void testCanMoveToAndCaputre() {
    Pawn blackPawnB7 = new Pawn(board.getSquare("b7"), false);
    Pawn whitePawnC6 = new Pawn(board.getSquare("c6"), true);
    Pawn whitePawnA6 = new Pawn(board.getSquare("a6"), true);

    //caputre
    assertTrue(whitePawnC6.canMoveTo("b7", board));
    assertTrue(whitePawnA6.canMoveTo("b7", board));
    assertFalse(whitePawnC6.canMoveTo("d7", board));
  }

  @Test
  void testCanMoveTo() {
    Pawn whitePawnA6 = new Pawn(board.getSquare("a6"), true);
    Pawn whitePawnE2 = new Pawn(board.getSquare("e2"), true);
    Pawn whitePawnB6 = new Pawn(board.getSquare("b6"), true);
    Pawn blackPawnB7 = new Pawn(board.getSquare("b7"), false);
    //move 1
    assertTrue(whitePawnA6.canMoveTo("a7", board));
    assertTrue(whitePawnE2.canMoveTo("e3", board));

    //move 2
    assertTrue(whitePawnE2.canMoveTo("e4", board));
    assertFalse(whitePawnE2.canMoveTo("d4", board));
    assertFalse(whitePawnE2.canMoveTo("f4", board));

    //blocked
    assertFalse(whitePawnB6.canMoveTo("b7", board));

    //edgecases
    assertFalse(whitePawnB6.canMoveTo("h1", board));
    assertFalse(whitePawnB6.canMoveTo("h8", board));
  }
}
