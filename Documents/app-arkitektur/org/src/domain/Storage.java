package src.domain;

import java.util.List;
import java.sql.*;

public interface Storage{
  public void addMovie(Movie m);
  public void addActor(Actor a);
  public void deleteMovie(String title);
  public void deleteActor(String name);
  //public List<Movie> showAllMovies();
  //public ResultSet showAllActors();
  //public List<Movie> getMovieByTitle(String title);
  //public List<Actor> getActorByName(String name);
  public List<Movie> getMoviesByActorName(String actorName);
  public List<Actor> getActorsByMovieTitle(String title);
  public int logIn(String username, String password);

  public ResultSet getActorByNameRS(String actorName);
}
