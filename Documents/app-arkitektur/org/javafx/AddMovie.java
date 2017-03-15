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
import javafx.collections.*;
import java.awt.Desktop;
import java.net.*;
import java.util.List;
import java.util.ArrayList;
import src.domain.Storage;
import src.domain.DBStorage;
import src.domain.Actor;
import src.domain.Movie;
public class AddMovie{
  //private Label titleLable, yearLabel, originLabel, regiLabel,genreLabel;
//  private Combobox genreBox1;
//  private TextField titleField, yearField, originField, regiField, genreField;
  //private Button addButton, cancelButton;
  private Storage storage;
  public AddMovie(Storage storage){
    this.storage = storage;
    createMView();
  }
  public void createMView(){
  Stage window = new Stage();
  window.initModality(Modality.APPLICATION_MODAL);
  window.setTitle("Movie");
  GridPane addMoviePane = new GridPane();
  addMoviePane.setPadding(new Insets(5,5,5,5));
  addMoviePane.setVgap(8);
  addMoviePane.setHgap(10);
  Label titleLable = new Label("Title:");
  TextField titleField = new TextField();
  Label genreLabel = new Label("Genre:");
  ObservableList<String> genreOptions =
    FXCollections.observableArrayList(
        "Action",
        "Comedy",
        "Drama",
        "Horror"
    );
final ComboBox genreBox = new ComboBox(genreOptions);
final ComboBox yearBox = new ComboBox();
int j = 1900;
for(int i = 0;i<117;i++){
  j++;
  yearBox.getItems().addAll(j);
}
Label yearLabel = new Label("Year:");
TextField yearField = new TextField();
Label regiLabel = new Label("Regi:");
TextField regiField = new TextField();
Label originLabel = new Label("Origin:");
TextField originField = new TextField();
Button addButton = new Button("ok");
addButton.setOnAction( e ->{
  if(titleField.getText().equals("") || genreBox.getValue() == null){
Alerts.display("Alert","Please enter all information requi");  }
  else{
  int year = Integer.parseInt(yearBox.getValue().toString());
  Movie m = new Movie(titleField.getText(), genreBox.getValue().toString(), year, regiField.getText(), originField.getText());
  storage.addMovie(m);
Alerts.display("Succeeded","Congratulation the movie has been added");
window.close();}
});
Button cancelButton = new Button("cancel");
cancelButton.setOnAction( e ->{
  window.close();
});
  addMoviePane.setConstraints(titleLable,0,0);
  addMoviePane.setConstraints(titleField,1,0);
  addMoviePane.setConstraints(genreLabel,0,1);
  addMoviePane.setConstraints(genreBox,1,1);
  addMoviePane.setConstraints(yearLabel,0,2);
  addMoviePane.setConstraints(yearBox,1,2);
  addMoviePane.setConstraints(regiLabel,0,3);
  addMoviePane.setConstraints(regiField,1,3);
  addMoviePane.setConstraints(originLabel,0,4);
  addMoviePane.setConstraints(originField,1,4);
  addMoviePane.setConstraints(addButton,0,5);
  addMoviePane.setConstraints(cancelButton,1,5);
  addMoviePane.getChildren().addAll(titleLable,titleField,genreLabel,genreBox,yearLabel,
  yearBox,regiLabel,regiField,originLabel,originField,addButton,cancelButton);
  Scene scene = new Scene(addMoviePane);
  scene.getStylesheets().add("javafx/style.css");
  window.setScene(scene);
  window.showAndWait();
}
}
