package src.domain;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
public class Utils implements Storage{
  private static Connection con;
  static{
    try{
      Class.forName("org.sqlite.JDBC");
      con = DriverManager.getConnection("jdbc:sqlite:movies");
      System.out.println("we have a Connection");
    }catch(ClassNotFoundException|SQLException e){
      System.err.println("exception getting movies: " + e.getMessage());
    }
  }
  public boolean hasConnection(){
    return con != null;
  }
  public void addMovie(Movie m){
    int id =0;
    String title = m.title();
    String genre = m.genre();
    String query="INSERT INTO movie(title,genre)"+
    " VALUES('" +  title + "'," + "'" + genre +"')";
    System.out.println(query);
    Statement stm=null;
    ResultSet rs;
    if(hasConnection()){
      try{
        stm=con.createStatement();
        stm.executeQuery(query);
      }catch(Exception e){
        System.err.println("executeQuery: " + e.getMessage());
      }
      try{
        String s = "SELECT movie_id FROM movie WHERE title='" + title + "'";
      ResultSet rs1=con.createStatement().executeQuery(s);
      System.out.println(s);
        rs1.next();
        m.setId(rs1.getInt("movie_id"));
      }catch(Exception e){
        System.err.println("Getting ID: " + e.getMessage());
      }
    /*  finally{
        try{
          rs.close();
          stm.close();
        }catch(Exception e){}
      } */
    }
  }
  public void addActor(Actor a){
  try{
    String query="insert into actor values(a.id(),a.name(),a.title())";
    Statement stm = con.createStatement();
    ResultSet rs =stm.executeQuery(query);
    System.out.println("actor " + a.name() + "has been successfully added");
  }catch(SQLException e){
    System.err.println("Error: " + e.getMessage());
  }
  }
/*  public void deleteMovie(int id){}
  public void deleteActor(int id){}
  public List<Movie> showAllMovies(){}
  public Movie getByName(String name){} */
}
