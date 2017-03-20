package src.domain;

public class Movie{
  private String title;
  private String genre;
  private String regi;
  private String origin;
  private int year;

  public Movie(String title,String genre){
    this.title = title;
    this.genre = genre;
  }

  public Movie(String title, String genre, int year, String regi, String origin){
    this(title,genre);
    this.year = year;
    this.regi = regi;
    this.origin = origin;
  }

  public String title(){ return title; }

  public String genre(){ return genre; }

  public int year(){ return year; }

  public String regi(){ return regi; }

  public String origin(){ return origin; }

  public void setTitle(String title){this.title = title;}

  public void setGenre(String genre){this.genre = genre;}

  public String toString(){
    return title + ": " + genre + ": " + year + ": " + regi + ": " + origin;
  }

}
