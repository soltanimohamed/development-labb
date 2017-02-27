package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import src.domain.Storage;
import src.domain.DBStorage;
import src.domain.Actor;
import src.domain.Movie;
public class DeleteActor{
  private Storage storage;
public DeleteActor(Storage storage){
  this.storage = storage;
  deleteA();
}
  private  void deleteA(){
  String input = JOptionPane.showInputDialog(null, "Enter name of the actor:", "Dialog for Input",
          JOptionPane.WARNING_MESSAGE);
          System.out.println(input);
      storage.deleteActor(input);
}
}
