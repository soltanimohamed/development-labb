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
import javafx.stage.Modality;
import java.awt.Desktop;
import java.net.*;
import java.util.List;
import java.util.ArrayList;
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
public void deleteA(){
  Stage window = new Stage();
  window.initModality(Modality.APPLICATION_MODAL);
  window.setTitle("Delete Movie");
  window.setMinWidth(200);
  Label label = new Label("Enter name:");
  label.setId("alert-text");
  TextField titleField = new TextField();
  Button okButton = new Button("ok");
  okButton.setOnAction( e ->{
    if(!(titleField.getText().equals(""))){
      storage.deleteActor(titleField.getText());
      window.close();
    }
  });
  Button noButton = new Button("cancel");
  noButton.setOnAction( e -> window.close());
  GridPane pane = new GridPane();
  pane.setVgap(10);
  pane.setHgap(10);
  pane.setPadding(new Insets(10,10,10,10));
  pane.setConstraints(label,0,0);
  pane.setConstraints(titleField,1,0);
  pane.setConstraints(okButton,0,1);
  pane.setConstraints(noButton,1,1);
    pane.getChildren().addAll(label,titleField,okButton,noButton);
  pane.setAlignment(Pos.CENTER);
  Scene scene = new Scene(pane);
  scene.getStylesheets().add("javafx/style.css");
  window.setScene(scene);
  window.showAndWait();
}
}
