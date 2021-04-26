package core;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.chess.*;
import core.chess.pieces.Piece;

public class AppController implements Initializable{

    @FXML private GridPane board;
    @FXML private TextField txtMove;

    private Game game = new Game();

    @FXML
    public void makeMove(KeyEvent key) {
        if(key.getCode().toString().equals("ENTER"))
        {
            try {
                String move = txtMove.getText();
                System.out.println(move);
                System.out.println(game.move(move));
            } catch (CloneNotSupportedException e) {
                System.out.println("ya fucked up m8");
            }
            updateGame();
            if(game.isWhiteToMove()) {
                System.out.println("white to move");
            } else {
                System.out.println("black to move");
            }
            txtMove.setText("");
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        game.newGame();
        updateGame();
    }


    public void updateGame() {
        board.getChildren().clear();
        for (Piece piece : game.getAllActivePieces()) {
            String color = "w";
            if(!piece.isWhite()){
                color = "b";
            }
            board.add(new Text("" + color + piece.getChar()), piece.getCordinates()[0], piece.getCordinates()[1]);
        }
    }
    
    public void loadGame() {
        try {
            game.LoadGame(StorageHandler.readGame());
        } catch (IOException e) {
            System.out.println("we fucked up:)");
        };
        updateGame();
    }

    public void saveGame() {
        try {
            StorageHandler.writeGame(game.getMoves());
        } catch (IOException e) {
            System.out.println("we fucked up:)");
        };
    }
    
}
