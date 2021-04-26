package core.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import core.chess.Board;

public class BoardTest {

    @Test
    void validateCordinatesTest() {
        assertTrue(Board.validateCordinates("a1"));
        assertTrue(Board.validateCordinates("h1"));
        assertTrue(Board.validateCordinates("a8"));
        assertTrue(Board.validateCordinates("h8"));
        assertTrue(Board.validateCordinates("e4"));

        assertThrows(IllegalArgumentException.class, () -> {Board.validateCordinates("w4");} );
        assertThrows(IllegalArgumentException.class, () -> {Board.validateCordinates("e0");} );
        assertThrows(IllegalArgumentException.class, () -> {Board.validateCordinates("w70");} );
    }
    
    @Test
    void convertCordinatesTest() {
        assertTrue(Arrays.equals(Board.convertCordinates("a1"), new int[]{0,7}));
        assertTrue(Arrays.equals(Board.convertCordinates("b2"), new int[]{1,6}));
        assertTrue(Arrays.equals(Board.convertCordinates("c6"), new int[]{2,2}));
        assertTrue(Arrays.equals(Board.convertCordinates("h1"), new int[]{7,7}));
    }
}
