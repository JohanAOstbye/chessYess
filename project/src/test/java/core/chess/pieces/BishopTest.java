package core.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.chess.Board;

public class BishopTest {
  Board board = new Board();

  @BeforeEach
  void setUp() {
    board = new Board();
  }

  @Test
  void testCanMoveToAndCaputre() {
    Bishop b2 = new Bishop(board.getSquare("b2"), true);
    Bishop a3 = new Bishop(board.getSquare("a1"), true);
    Bishop c1 = new Bishop(board.getSquare("c1"), false);
    Bishop d2 = new Bishop(board.getSquare("d2"), false);


    assertTrue(b2.canMoveTo("h8", board));
    assertTrue(b2.canMoveTo("c1", board));
    assertTrue(b2.canMoveTo("a3", board));
    assertFalse(c1.canMoveTo("e3", board));

    assertFalse(c1.canMoveTo("c2", board));
    assertFalse(c1.canMoveTo("h1", board));
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
