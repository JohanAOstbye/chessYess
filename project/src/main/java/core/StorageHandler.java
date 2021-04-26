package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageHandler {

  public static void writeGame(List<String> moves) throws IOException {
    FileWriter writer = new FileWriter("game.txt");
    for (int i = 0; i < moves.size(); i++) {
      writer.write(moves.get(i) + "\n");
      writer.flush();
    }
    writer.close();
  }

  public static List<String> readGame() throws IOException {
    BufferedReader reader;
    List<String> moves = new ArrayList<>();
    try {
      reader = new BufferedReader(new FileReader("game.txt"));
      moves = reader.lines().collect(Collectors.toList());
      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println("file not found");
    }
    return moves;
  }
}
