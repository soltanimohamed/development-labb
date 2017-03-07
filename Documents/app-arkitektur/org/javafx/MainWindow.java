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
import javafx.collections.*;
import java.util.List;
import java.util.ArrayList;
import src.domain.Storage;
import src.domain.DBStorage;
import src.domain.Actor;
import src.domain.Movie;
import java.awt.Desktop;
import java.net.*;
public class MainWindow extends Application{
  private TextField searchField;
  private Button searchButton;
  private Button clearResultButton;
  private Button submitButton;
  private Menu editMenu;
  private Menu fileMenu;
  private MenuItem showAllmovies;
  private MenuItem showAllactors;
  private MenuItem addMovie;
  private MenuItem deleteMovie;
  private MenuItem addActor;
  private MenuItem deleteActor;
  private MenuItem exitApplication;
  private Button logginButton;
  private Button loggoutButton;
  private ListView<String> list;
  private BorderPane mainPane;
  private GridPane pane1;
  private GridPane pane2;
  private GridPane pane3;
  private VBox pane4;
  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage primaryStage) throws Exception{
    Storage storage = new DBStorage();
    primaryStage.setTitle("film-lager");
    mainPane = new BorderPane();
    pane1 = new GridPane();
    pane2 = new GridPane();
    pane4 = new VBox(20);
    pane3 = new GridPane();
    logginButton = new Button("Sign in");
    logginButton.setOnAction( e ->{
      SignIn.userLogin(storage);
      if(storage.inlogg(SignIn.userField.getText(), SignIn.passField.getText()) != 0){
        addMovie.setDisable(false);
        addActor.setDisable(false);
        deleteMovie.setDisable(false);
        deleteActor.setDisable(false);
        logginButton.setDisable(true);
        loggoutButton.setDisable(false);
      }
    });
    loggoutButton = new Button("Sign out");
    loggoutButton.setDisable(true);
    loggoutButton.setOnAction( e ->{
      Alerts.display("Confirmation","you are now signed out");
      logginButton.setDisable(false);
      loggoutButton.setDisable(true);
      addMovie.setDisable(true);
      addActor.setDisable(true);
      deleteMovie.setDisable(true);
      deleteActor.setDisable(true);
    });
    list = new ListView<>();
    pane2.setPadding(new Insets(10,10,10,10));
    pane2.setVgap(8);
    pane2.setHgap(10);
    pane2.setConstraints(logginButton,0,0);
    pane2.setConstraints(loggoutButton,0,1);
    pane2.getChildren().addAll(logginButton,loggoutButton);
    mainPane.setRight(pane2);
    fileMenu = new Menu("File");
    showAllmovies = new MenuItem("Show all movies");
    showAllmovies.setOnAction( e -> {
      List<Movie> allMovies = storage.showAllMovies();
      List<String> movies = new ArrayList<>();
      int i = 1;
      for(Movie m : allMovies){
        movies.add(m.title());
        i++;
      }
      list.getItems().clear();
      for(String s : movies){
        list.getItems().add(s);
      }
    });
    showAllactors = new MenuItem("Show all actors");
    showAllactors.setOnAction( e -> {
      List<Actor> allActors = storage.showAllActors();
      List<String> actors = new ArrayList<>();
      int i = 1;
      for(Actor a : allActors){
        actors.add(i + ": " + a.name());
        i++;
      }
      list.getItems().clear();
      for(String s : actors){
        list.getItems().add(s);
      }
    });
    exitApplication = new MenuItem("Exit");
    exitApplication.setOnAction( e -> primaryStage.close());
    fileMenu.getItems().add(showAllmovies);
    fileMenu.getItems().add(showAllactors);
    fileMenu.getItems().add(new SeparatorMenuItem());
    fileMenu.getItems().add(exitApplication);
    editMenu = new Menu("Edit");
    addMovie = new MenuItem("Add new movie...");
    addMovie.setDisable(true);
    deleteMovie = new MenuItem("Delete movie...");
    deleteMovie.setDisable(true);
    addActor = new MenuItem("Add new actor...");
    addActor.setDisable(true);
    deleteActor = new MenuItem("Delete actor");
    deleteActor.setDisable(true);
    editMenu.getItems().add(addMovie);
    editMenu.getItems().add(deleteMovie);
    editMenu.getItems().add(new SeparatorMenuItem());
    editMenu.getItems().add(addActor);
    editMenu.getItems().add(deleteActor);
    //fileMenu.getItems().add(new SeperatorMenuItem());
    MenuBar menubar = new MenuBar();
    menubar.getMenus().addAll(fileMenu);
    menubar.getMenus().addAll(editMenu);
    mainPane.setLeft(pane3);
    clearResultButton = new Button("Clear result");
    clearResultButton.setOnAction( e ->list.getItems().clear());
    submitButton = new Button("Submit");
    pane1.setVgap(10);
    pane1.setHgap(10);
    pane1.setPadding(new Insets(10,10,10,250));
    pane1.setConstraints(submitButton,0,0);
    pane1.setConstraints(clearResultButton,1,0);
    pane1.getChildren().addAll(submitButton,clearResultButton);
    mainPane.setBottom(pane1);
    mainPane.setCenter(list);
    pane3.setPadding(new Insets(10,10,10,10));
    pane3.setVgap(8);
    pane3.setHgap(10);
    searchField = new TextField();
    searchField.setPromptText("movie or actor");
    searchField.setOnAction( e ->{
      String input =searchField.getText();
      List<Movie> movies = storage.getMoviesByActorName(input);
      List<Actor> actors = storage.getActorsByMovieTitle(input);
      List<String> result = new ArrayList<>();
      for(Actor a : actors){
        result.add(a.name());
      }
      for(Movie m :movies){
        result.add(m.title());
      }
      list.getItems().clear();
      for(String s : result){
        list.getItems().add(s);
      }
    });
    pane3.setConstraints(searchField, 0, 0);
    searchButton = new Button("search");
    searchButton.setOnAction( e ->{
      String input =searchField.getText();
      List<Movie> movies = storage.getMoviesByActorName(input);
      List<Actor> actors = storage.getActorsByMovieTitle(input);
      List<String> result = new ArrayList<>();
      for(Actor a : actors){
        result.add(a.name());
      }
      for(Movie m :movies){
        result.add(m.title());
      }
      list.getItems().clear();
      for(String s : result){
        list.getItems().add(s);
      }
    });
    pane3.setConstraints(searchButton, 0, 1);
    pane3.getChildren().addAll(searchField,searchButton);
    mainPane.setTop(menubar);
    Scene scene = new Scene(mainPane, 600,500);
    scene.getStylesheets().add("javafx/style.css");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
