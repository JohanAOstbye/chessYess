package core.chess;

import java.util.ArrayList;
import java.util.List;

import core.chess.pieces.King;
import core.chess.pieces.Piece;

public class Board implements Cloneable{
    private Square[][] squares = new Square[8][8];

    public List<Piece> getActivePieces(boolean isWhite) {
        List<Piece> pieces = new ArrayList<Piece>();
        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                if(squares[i][j].getPiece() != null && squares[i][j].getPiece().isWhite() == isWhite) {
                    pieces.add(squares[i][j].getPiece());
                }
            }
        }
        return pieces;
    }

    public List<Piece> getActiveWhitePieces() {
        return getActivePieces(true);
    }

    public List<Piece> getActiveBlackPieces() {
        return getActivePieces(false);
    }

    public King getKing(boolean isWhite) {
        King king = (King) getActivePieces(isWhite).stream().filter(p-> p instanceof King).findFirst().get();
        return king;
    }
    
    public Board() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                squares[i][j] = new Square(i+j%2 ==  0, new int[]{i,j});
            }
        }
    }   

    private static String columns = "abcdefgh";

    public static int[] convertCordinates(String cordinates) {
        validateCordinates(cordinates);

        return new int[]{columns.indexOf(Character.toLowerCase(cordinates.charAt(0))), 8-Character.getNumericValue(cordinates.charAt(1))};
    }

    public static String CordinatesToString(int[] cordinates) {
        if(cordinates[0] < 0 || cordinates[0] > 7) {
            throw new IllegalArgumentException("column out of bounce for: " + cordinates[0]);
        }
        String newCordinates = "" + columns.charAt(cordinates[0]) + Integer.toString(8-cordinates[1]);
        validateCordinates(newCordinates);

        return newCordinates;
    }

    public static boolean validateCordinates(String cordinates) {
        int length = cordinates.length();
        if(length != 2){
            throw new IllegalArgumentException("invalid cordinates: " + cordinates);
        }

        //validating row
        int row = Character.getNumericValue(cordinates.charAt(1))-1;
        if (row > 7 || row < 0) {
            throw new IllegalArgumentException("row out of bounce: " + row);
        }

        //validating column
        int column = columns.indexOf(Character.toLowerCase(cordinates.charAt(0)));
        if(column == -1) {
            throw new IllegalArgumentException("column out of bounce: " + column);
        }

       return true;

    }

    public static int getColumn(String character) {
        int column = columns.indexOf(Character.toLowerCase(character.charAt(0)));
        if(column == -1) {
            throw new IllegalArgumentException("column out of bounce: " + column);
        }
        return column;
    }

    public static boolean ValidateCordinates(int[] cordinates) {
        int length = cordinates.length;
        if(length != 2){
            throw new IllegalArgumentException("invalid cordinates: " + cordinates.toString());
        }

        if((cordinates[0] >= 7 && cordinates[0] <= 0) || (cordinates[1] >= 7 && cordinates[1] <= 0) ) {
            throw new IllegalArgumentException("cordinates out of bounce: " + cordinates.toString());
        }
        
       return true;
    }

    public Square getSquare(String cordinates) {
        int[] c = convertCordinates(cordinates);
        return squares[c[0]][c[1]];
    }

    public Square getSquare(int[] cordinates) {
        return squares[cordinates[0]][cordinates[1]];
    }

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}