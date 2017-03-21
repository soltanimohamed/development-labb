package javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.geometry.*;
import src.domain.Storage;
import src.domain.DBStorage;
import src.domain.Actor;
import src.domain.Movie;
import javafx.stage.Modality;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class AddActor{
  private Storage storage;

  public AddActor(Storage storage){
    this.storage = storage;
    createAview();
  }

  public  void createAview(){
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Actor");
    window.setMinWidth(250);
    Label  nameLabel = new Label("Name:");
    TextField nameField = new TextField();
    Label sexLabel = new Label("Sex:");
    ComboBox sexBox = new ComboBox();
    sexBox.getItems().add("Male");
    sexBox.getItems().add("Female");
    Label nationalityLabel = new Label("Nationality:");
    TextField nationalityField = new TextField();
    Label bornLabel = new Label("Born:");
    TextField bornField = new TextField();
    bornField.setPromptText("Year-Month-Day");
    Button submitButton = new Button("ok");
    submitButton.setOnAction( e ->{
      if(nameField.getText().equals("") || sexBox.getValue() == null){
        Alerts.display("Alert","Please enter all information required");
      }
      else{
        try{
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
          Date date = formatter.parse(bornField.getText());
          //String sex = sexBox.getValue().toString();
          //DateTime bornDT = formatter.parseDateTime(born.getText());
          Actor a = new Actor(nameField.getText(), sexBox.getValue().toString(), nationalityField.getText(), formatter.format(date));
          storage.addActor(a);
          Alerts.display("Succeeded","Congratulation the actor has been added");
          window.close();} catch(ParseException pe){
            Alerts.display("Wrong" ,"date format incorrect");
          }
        }
      });
      Button cancelButton = new Button("cancel");
      cancelButton.setOnAction( e ->{
        window.close();
      });
      GridPane pane = new GridPane();
      pane.setPadding(new Insets(5,5,5,5));
      pane.setVgap(8);
      pane.setHgap(10);
      pane.setConstraints(nameLabel,0,0);
      pane.setConstraints(nameField,1,0);
      pane.setConstraints(sexLabel,0,1);
      pane.setConstraints(sexBox,1,1);
      pane.setConstraints(nationalityLabel,0,2);
      pane.setConstraints(nationalityField,1,2);
      pane.setConstraints(bornLabel,0,3);
      pane.setConstraints(bornField,1,3);
      pane.setConstraints(submitButton,0,4);
      pane.setConstraints(cancelButton,1,4);
      pane.getChildren().addAll(nameLabel,nameField,sexLabel,
      sexBox,nationalityLabel,nationalityField,bornLabel,bornField,submitButton,cancelButton);
      Scene scene = new Scene(pane);
      scene.getStylesheets().add("javafx/style.css");
      window.setScene(scene);
      window.showAndWait();
    }
  }
