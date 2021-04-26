package core.chess.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.chess.Board;

public class KingTest {
  Board board = new Board();

  @BeforeEach
  void setUp() {
    board = new Board();
  }

  @Test
  void testCanMoveToAndCaputre() {
    King b7 = new King(board.getSquare("b7"), false);
    Pawn c7 = new Pawn(board.getSquare("c7"), true);
    Pawn b6 = new Pawn(board.getSquare("b6"), false);

    assertTrue(b7.canMoveTo("c7", board));
    assertFalse(b7.canMoveTo("b6", board));
  }

  @Test
  void testCanMoveTo() {
    King b7 = new King(board.getSquare("b7"), false);
    Rook c1 = new Rook(board.getSquare("c1"), true);
    
    //move
    assertTrue(b7.canMoveTo("b6", board));
    assertFalse(b7.canMoveTo("b7", board));
    //move into check
    assertFalse(b7.canMoveTo("c7", board));
  }
}
