package src.domain;
public class Movie{
  private String title;
  private String genre;

  public Movie(String title, String genre){
    this.title = title;
    this.genre = genre;
  }
  public String title(){ return title; }
  public String genre(){ return genre; }
 public void setTitle(String title){this.title = title;}
 public void setGenre(String genre){this.genre = genre;}
 public String toString(){
   return title + ": " + genre;
 }

}
