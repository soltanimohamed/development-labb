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

public class AddActorGui{
  private Storage storage;
  private JFrame frame;
  private JPanel panel;
  private JLabel nameLabel;
  private JLabel sexLabel;
  private JTextField nameField;
  private JTextField sexField;
  private JButton saveButton;
  private JButton cancelButton;

  public AddActorGui(Storage storage){
    this.storage = storage;
    addActor();
  }
  private static void message(String msg, String titleBar){
    //JOptionPane.showMessageDialog(this, msg, "Infobox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(null,msg, titleBar, JOptionPane.WARNING_MESSAGE);
  }
  private void addActor(){
    nameLabel = new JLabel("Enter the name:");
    nameField = new JTextField(25);
    sexLabel = new JLabel("Enter the sex:");
    sexField = new JTextField(25);
    saveButton = new JButton("Save");
    cancelButton = new JButton("Cancel");
  /*  cancelButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        message("Please enter all the informations required!", "Missing input");
      }
1    }); */
    panel = new JPanel();
    panel.add(nameLabel);
    panel.add(nameField);
    panel.add(sexLabel);
    panel.add(sexField);
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
        if(nameField.getText().equals("") || sexField.getText().equals("")){
          message("Please enter all the informations required!", "Missing input");
        }
        else{
        Actor a = new Actor(nameField.getText(), sexField.getText());
        storage.addActor(a);
        message("Congratulations: the actor "+ a.name() + " has been added", "input saved");
      }}
    });
    cancelButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        frame.dispose();
      }
    });
  }
}
