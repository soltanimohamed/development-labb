package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import src.domain.Storage;
import src.domain.DBStorage;
import src.domain.Actor;
import src.domain.Movie;
public class DeleteMovie{
  private Storage storage;
public DeleteMovie(Storage storage){
  this.storage = storage;
  deleteM();
}
  private  void deleteM(){
  String input = JOptionPane.showInputDialog(null, "Enter movie title:", "Dialog for Input",
          JOptionPane.WARNING_MESSAGE);
          System.out.println(input);
      storage.deleteMovie(input);
}
}
