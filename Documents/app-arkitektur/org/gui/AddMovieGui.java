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

public class AddMovieGui{
  private Storage storage;
  private JFrame frame;
  private JPanel panel;
  private JLabel titleLabel;
  private JLabel genreLabel;
  private JTextField titleField;
  private JTextField genreField;
  private JButton saveButton;
  private JButton cancelButton;

  public AddMovieGui(Storage storage){
    this.storage = storage;
    addMovie();
  }
  private static void message(String msg, String titleBar){
    //JOptionPane.showMessageDialog(this, msg, "Infobox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null,msg, titleBar, JOptionPane.WARNING_MESSAGE);
  }
  private void addMovie(){
    titleLabel = new JLabel("Enter the title:");
    titleField = new JTextField(25);
    genreLabel = new JLabel("Enter the genre:");
    genreField = new JTextField(25);
    saveButton = new JButton("Save");
    cancelButton = new JButton("Cancel");
    panel = new JPanel();
    panel.add(titleLabel);
    panel.add(titleField);
    panel.add(genreLabel);
    panel.add(genreField);
    panel.add(saveButton);
    panel.add(cancelButton);
    frame = new JFrame();
    frame.add(panel);
    frame.setPreferredSize(new Dimension(500,300));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    saveButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(titleField.getText().equals("") || genreField.getText().equals("")){
          message("Please enter all the informations required!", "Missing input");
        }
        else{
        Movie m = new Movie(titleField.getText(), genreField.getText());
        storage.addMovie(m);
        message("Congratulations: the movie has been added", "input saved");
      }}
    });
    cancelButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        frame.dispose();
      }
    });
  }
}
