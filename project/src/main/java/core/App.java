package core;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

  /**
   * Funksjonen som starter appen
   *
   * @param primaryStage stage som skal vises
   * @throws Exception dersom filen ikke finnes
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    URL xmlUrl = getClass().getResource("/mainScene.fxml");
    loader.setLocation(xmlUrl);
    Parent root = loader.load();

    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  public static void main(final String[] args) {
    App.launch(args);
  }
}