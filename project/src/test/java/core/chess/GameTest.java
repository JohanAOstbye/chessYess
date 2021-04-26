package core.chess;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.chess.pieces.Piece;

public class GameTest {
    private Game game;

    @BeforeEach
    void setup() {
        game = new Game();
    }
    
    @Test
    void testBoardandNewGame() {
        assertTrue(game.getBoard().getActiveWhitePieces().isEmpty());
        assertTrue(game.getBoard().getActiveBlackPieces().isEmpty());


        game.newGame();

        for(Piece p : game.getBoard().getActiveWhitePieces()){
            assertEquals(p, game.getBoard().getSquare(p.getCordinates()).getPiece());
        }

        for(Piece p : game.getBoard().getActiveBlackPieces()){
            assertEquals(p, game.getBoard().getSquare(p.getCordinates()).getPiece());
        }
    }

    @Test
    void testMove() {
        game.newGame();
        assertEquals("move made", game.move("e3"));
        assertEquals("piece doesnt exist", game.move("e3"));
        assertEquals("move made", game.move("Nf6"));
        assertEquals("move made", game.move("Ke2"));
        assertEquals("move made", game.move("Ne4"));
        assertEquals("move made", game.move("Kf3"));
        assertEquals("move made", game.move("Ng5")); //king in check
        assertEquals("move failed", game.move("a3"));
        assertEquals("move failed", game.move("Nc3"));
        assertEquals("move failed", game.move("Na3"));
        assertEquals("piece doesnt exist", game.move("e3"));
        assertEquals("piece doesnt exist", game.move("Qe3"));
        assertEquals("piece doesnt exist", game.move("Qa3"));
        assertEquals("move made", game.move("Ke2"));
    }


    @Test
    void testIsWhiteToMove() {
        assertTrue(game.isWhiteToMove());
        game.newGame();
        assertTrue(game.isWhiteToMove());
        game.move("e3");
        assertTrue(!game.isWhiteToMove());
        game.move("e3");
        assertTrue(!game.isWhiteToMove());
        game.move("e8");
        assertTrue(!game.isWhiteToMove());

    }


    @Test
    void testPieces() {
        assertTrue(game.getPieces().isEmpty());

        game.newGame();

        assertTrue(game.getAllActivePieces().containsAll(game.getActivePieces()));
        assertEquals(game.getActivePieces(), game.getBoard().getActivePieces(game.isWhiteToMove()));
    }
}
