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
  private Button searchButton, clearResultButton, submitButton;
  private Menu fileMenu, editMenu, sortMenu;
  private MenuItem showAllmovies;
  private MenuItem showAllactors;
  private MenuItem addMovie;
  private MenuItem deleteMovie;
  private MenuItem addActor;
  private MenuItem deleteActor;
  private MenuItem exitApplication;
  private MenuItem genre;
  private MenuItem action;
  private MenuItem comedy;
  private MenuItem drama;
  private MenuItem horror;
  private Button loginButton, logoutButton;
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

    loginButton = getLoginButton(storage);
    logoutButton = getLogoutButton();

    list = new ListView<>();
    pane2.setPadding(new Insets(10,10,10,10));
    pane2.setVgap(8);
    pane2.setHgap(10);
    pane2.setConstraints(loginButton,0,0);
    pane2.setConstraints(logoutButton,0,1);
    pane2.getChildren().addAll(loginButton,logoutButton);
    mainPane.setRight(pane2);

    //Making File menu items
    fileMenu = new Menu("File");
    getFileMenuItem("MOVIE", storage);
    getFileMenuItem("ACTOR", storage);
    exitApplication = new MenuItem("Exit");
    exitApplication.setOnAction( e -> primaryStage.close());

    //The following will populate the File menu with above items
    fileMenu.getItems().add(showAllmovies);
    fileMenu.getItems().add(showAllactors);
    fileMenu.getItems().add(new SeparatorMenuItem());
    fileMenu.getItems().add(exitApplication);

    //Making edit menu items
    editMenu = new Menu("Edit");
    getEditMenuItem("MOVIE", storage);
    getEditMenuItem("ACTOR", storage);

    //The following will populate the Edit menu with above items
    editMenu.getItems().add(addMovie);
    editMenu.getItems().add(deleteMovie);
    editMenu.getItems().add(new SeparatorMenuItem());
    editMenu.getItems().add(addActor);
    editMenu.getItems().add(deleteActor);

    //Sort menu initiation, not done..
    sortMenu = new Menu("Sort");
    action = new MenuItem("Action");
    comedy = new MenuItem("Comedy");
    drama = new MenuItem("Drama");
    horror = new MenuItem("horror");
    sortMenu.getItems().add(action);
    sortMenu.getItems().add(comedy);
    sortMenu.getItems().add(drama);
    sortMenu.getItems().add(horror);
    //sortMenu.getItems().add(new SeperatorMenuItem());

    //Adding the whole caboodle to the menubar
    MenuBar menubar = new MenuBar();
    menubar.getMenus().addAll(fileMenu);
    menubar.getMenus().addAll(editMenu);
    menubar.getMenus().addAll(sortMenu);
    mainPane.setLeft(pane3);

    clearResultButton = new Button("Clear result");
    clearResultButton.setOnAction( e ->list.getItems().clear());

    submitButton = new Button("Submit");
    submitButton.setOnAction( e ->{
      String message = "";
      ObservableList<String> strings = list.getSelectionModel().getSelectedItems();
      try{
        for(String var : strings){
          message = var;
        }
        String  mess = message.replaceAll("\\s+", "");
        // Alerts.display("Exception", mess);
        String adress = "https://www.youtube.com/results?search_query="+mess;
        Alerts.openWebpage(new URL(adress));
      }catch(Exception exc){
        System.out.println("Sucks to be you!");
      }
    });

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

  /**
  * This function initiate and returns a working login button.
  * @param storage a Storage that contains valid usernames and passwords
  * @return a Button for login
  */
  private Button getLoginButton(Storage storage){

    Button loginButton = new Button("Sign in");

    loginButton.setOnAction( e ->{
      SignIn.userLogin(storage);
      if(storage.logIn(SignIn.userField.getText(), SignIn.passField.getText()) != 0){
        addMovie.setDisable(false);
        addActor.setDisable(false);
        deleteMovie.setDisable(false);
        deleteActor.setDisable(false);
        loginButton.setDisable(true);
        logoutButton.setDisable(false);
      }
    });

    return loginButton;
  }

  /**
  * This function initiate and returns a working logout button.
  * @return a Button for logout
  */
  private Button getLogoutButton(){

    Button logoutButton = new Button("Sign out");

    logoutButton.setDisable(true);
    logoutButton.setOnAction( e ->{
      Alerts.display("Confirmation","you are now signed out");
      loginButton.setDisable(false);
      logoutButton.setDisable(true);
      addMovie.setDisable(true);
      addActor.setDisable(true);
      deleteMovie.setDisable(true);
      deleteActor.setDisable(true);
    });

    return logoutButton;
  }

  /**
  * This function initiates menuitems
  * @param name is the menu item to be initated
  * @param storage the storage that contians data connected to the menu item
  */
  private void getFileMenuItem(String name, Storage storage){

    //There is probably a better more general solution to this. Will most certainly change after use of tableviews
    if (name.equals("MOVIE")){
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
    }else{
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
    }
  }

  /**
  * This function initiates edit menu items
  * @param name is the menu item to be initated
  * @param storage the storage that contians data connected to the menu item
  */
  private void getEditMenuItem(String name, Storage storage){

    if (name.equals("MOVIE")) {

      addMovie = new MenuItem("Add new movie...");
      addMovie.setDisable(true);
      addMovie.setOnAction( e ->{
        new AddMovie(storage);
      });
      addMovie.setDisable(true);

      deleteMovie = new MenuItem("Delete movie...");
      deleteMovie.setOnAction( e -> {
        new DeleteEntryView(storage, name);
      });
      deleteMovie.setDisable(true);
    }else{
      addActor = new MenuItem("Add new actor...");
      addActor.setDisable(true);
      addActor.setOnAction( e ->{
        new AddActor(storage);
      });
            
      deleteActor = new MenuItem("Delete actor");
      deleteActor.setOnAction( e ->{
        new DeleteEntryView(storage, name);
      });
      deleteActor.setDisable(true);
    }
  }
}
