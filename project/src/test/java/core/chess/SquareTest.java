package core.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.chess.pieces.Pawn;

public class SquareTest {
    Square square;

    @BeforeEach
    void setUp() {
        square = new Square(true, new int[]{1,0});
    }
    
    @Test
    void testPiece() {
        Pawn pawn = new Pawn(square, true);
        Square square2 = new Square(true, new int[]{0,0});

        assertEquals(square.getPiece(), pawn);
        square2.setPiece(pawn);
        assertEquals(square2.getPiece(), pawn);
    }

    @Test
    void testCordinates() {
        int[] cords = new int[]{1,0};
        int[] wrongCords = new int[]{2,0};

        Square square2 = new Square(true, cords);

        assertTrue(Arrays.equals(square.getCordinates(), cords));
        assertFalse(Arrays.equals(square.getCordinates(), wrongCords));
        assertEquals(square2.getCordinates(), cords);
    }

    @Test
    void testColor() {

        Square square2 = new Square(false, new int[]{4,6});

        assertTrue(square.getIsWhiteColor());
        assertFalse(square2.getIsWhiteColor());
    }
}