package src.domain;
import java.util.List;
public interface Storage{
  public void addMovie(Movie m);
  public void addActor(Actor a);
  public void deleteMovie(String title);
  public void deleteActor(String name);
  public List<Movie> showAllMovies();
  public List<Actor> showAllActors();
  public List<Movie> getMoviesByActorName(String actorName);
  public List<Actor> getActorsByMovieTitle(String title);
  public int inlogg(String username, String password);
}
