package src.domain;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import javafx.*;

public class DBStorage implements Storage{

  private final static String DB_CON="jdbc:sqlite:movies2.db";
  private static Connection con;

  static{
    try{
      Class.forName("org.sqlite.JDBC");
      con = DriverManager.getConnection(DB_CON);
    }catch(ClassNotFoundException | SQLException e){
      System.err.println("Error getting connection to the database " + e.getMessage());
    }
  }

  public boolean hasConnection(){
    return con != null;
  }

  public void addMovie(Movie m){
    if(hasConnection()){
      try{
        Statement stm = null;
        String title = m.title();
        String genre = m.genre();
        int year = m.year();
        String regi = m.regi();
        String origin = m.origin();
        String sql = "INSERT INTO movies(title,genre,year,regi,origin) VALUES('" + title +
        "','" + genre + "',"+ year + ",'" + regi + "','" + origin +   "')";
        System.out.println(sql);
        stm = con.createStatement();
        stm.executeUpdate(sql);
        System.out.println("the movie " + title + " has been successfully added");
      }catch(SQLException e){
        System.err.println("Error " + e.getMessage());
      }
    }
  }

  public void addActor(Actor a){
      if(hasConnection()){
      try{
        Statement stm = null;
        String name = a.name();
        String sex = a.sex();
        String nationality = a.nationality();
        String born = a.born();
        String sql = "INSERT INTO actors(name,sex,nationality,born) VALUES('" + name +
        "','" + sex + "','"+ nationality + "','" + born + "')";
        System.out.println(sql);
        stm = con.createStatement();
        stm.executeUpdate(sql);
      //  a.setId(rs.getInt("actor_id"));
        System.out.println("the actor " + name + " has been successfully added");
      }catch(SQLException e){
        Alerts.display("Wrong" ,"date format incorrect");
      }
    }
  }


  /*public List<Movie> showAllMovies(){
    List<Movie> allMovies = new ArrayList<>();
    try{
      String sql = "SELECT title, genre FROM movies";
      ResultSet rs = con.createStatement().executeQuery(sql);
      while(rs.next()){
        Movie m = new Movie(rs.getString("title") , rs.getString("genre"));
        allMovies.add(m);
      }
    }catch(Exception e){
      System.err.println("Error: " + e.getMessage());
    }
    return allMovies;
  }*/

  /*public ResultSet showAllActors(){
    List<Actor> allActors = new ArrayList<>();
    try{
      String sql = "SELECT name, sex FROM actors";
      ResultSet rs = con.createStatement().executeQuery(sql);
      while(rs.next()){
        Actor a = new Actor(rs.getString("name") , rs.getString("sex"));
        allActors.add(a);
      }
    }catch(Exception e){
      System.err.println("Error: " + e.getMessage());
    }
    return allActors;
  }*/

/*  public List<Movie> getMovieByTitle(String movieTitle){
    List<Movie> movieByTitle = new ArrayList<>();
    try{
      String sql = "SELECT title,genre FROM movies WHERE title LIKE '" + movieTitle +"%'";
      ResultSet rs = con.createStatement().executeQuery(sql);
      while(rs.next()){
        String title = rs.getString("title");
        String genre = rs.getString("genre");
        Movie m = new Movie(title, genre);
        movieByTitle.add(m);
      }
    }catch(SQLException e){
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
    return movieByTitle;
  }*/

  public ResultSet getActorByNameRS(String actorName){
    ResultSet rs = null;
    try{
      String sql = "SELECT name, sex, nationality, born FROM actors WHERE name LIKE '" + actorName +"%'";
      rs = con.createStatement().executeQuery(sql);
    }catch(SQLException e){
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
    return rs;
  }

  public ResultSet getMovieByTitleRS(String movieTitle){
    ResultSet rs = null;
    try{
      String sql = "SELECT title, genre, year, regi, origin FROM movies WHERE title LIKE '" + movieTitle +"%'";
      rs = con.createStatement().executeQuery(sql);
    }catch(SQLException e){
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
    return rs;
  }

  /*public List<Actor> getActorByName(String actorName){
    List<Actor> actorByName = new ArrayList<>();
    try{
      String sql = "SELECT name, sex FROM actors WHERE name LIKE '" + actorName +"%'";
      ResultSet rs = con.createStatement().executeQuery(sql);
      while(rs.next()){
        String name = rs.getString("name");
        String sex = rs.getString("sex");
        Actor a = new Actor(name, sex);
        actorByName.add(a);
      }
    }catch(SQLException e){
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
    return actorByName;
  }*/

  public List<Movie> getMoviesByActorName(String actorName){
    List<Movie> moviesByActor = new ArrayList<>();
    try{
      String sql = "SELECT title,genre FROM movies NATURAL JOIN actors_movies "+
      "NATURAL JOIN actors WHERE actors.name LIKE '" + actorName +"%'";
      ResultSet rs = con.createStatement().executeQuery(sql);
      while(rs.next()){
        String title=rs.getString("title");
        String genre=rs.getString("genre");
        Movie m = new Movie( title, genre);
        moviesByActor.add(m);
      }
    }catch(SQLException e){
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
    return moviesByActor;
  }

  public List<Actor> getActorsByMovieTitle(String movieTitle){
    List<Actor> actorsByMovie = new ArrayList<>();
    try{
      String sql = "SELECT name, sex FROM movies NATURAL JOIN actors_movies "+
      "NATURAL JOIN actors WHERE movies.title LIKE '" + movieTitle + "%'";
      ResultSet rs = con.createStatement().executeQuery(sql);
      while(rs.next()){
        String name = rs.getString("name");
        String sex = rs.getString("sex");
        Actor a = new Actor(name, sex);
        actorsByMovie.add(a);
      }
    }catch(SQLException e){
      System.err.println("Error: " + e.getMessage());
    }
    return actorsByMovie;
  }

  public void deleteMovie(String title){
    try{
      int id = 0;
      String sql="SELECT movie_id FROM movies WHERE title='" + title + "'";
      ResultSet rs = con.createStatement().executeQuery(sql);
      if(rs.next()){
        //Movie m = new Movie(rs.getString("title"), rs.getString("genre"));
        id = rs.getInt("movie_id");
        //  ResultSet r = con.createStatement().executeQuery("SELECT movie_id from movies where title='" + m.title() + "'");
        String delete="DELETE FROM movies WHERE movie_id='" + id + "'";
        con.createStatement().executeUpdate(delete);
        con.createStatement().executeUpdate("DELETE FROM actors_movies WHERE movie_id='" + id + "'");
        System.out.println("the movie " + title + " has been deleted");
      }
      else{
        System.out.println("the movie " + title + " does not exist");
      }
    }catch(SQLException e){
      System.err.println("Error: " + e.getMessage());
    }
  }

  public void deleteActor(String name){
    try{
      int id = 0;
      String sql="SELECT actor_id FROM actors WHERE name='" + name + "'";
      ResultSet rs = con.createStatement().executeQuery(sql);
      if(rs.next()){
        id = rs.getInt("actor_id");
        String delete="DELETE FROM actors WHERE actor_id='" + id + "'";
        con.createStatement().executeUpdate(delete);
        con.createStatement().executeUpdate("DELETE FROM actors_movies WHERE actor_id='" + id + "'");
        System.out.println(name + " has been deleted");
      }
      else{
        System.out.println(name + " does not exist");
      }
    }catch(SQLException e){
      System.err.println("Error " + e.getMessage());
    }
  }

  public int logIn(String username, String password){
    int result = 0;
    try{
      String sql="SELECT admin_id FROM admins WHERE username='" + username+
      "' AND password='" + password + "'";
      ResultSet rs = con.createStatement().executeQuery(sql);
      if(rs.next()){
        result = rs.getInt("admin_id");
        //System.out.println(result);
        /*  }
        else{
        result = false;
        System.out.println(result);
      } */
      }
    }catch(SQLException|NullPointerException e){
      //System.err.println("Error :" + e.getMessage());
      System.err.println("there is an err");
    }
    return result;
  }
}
