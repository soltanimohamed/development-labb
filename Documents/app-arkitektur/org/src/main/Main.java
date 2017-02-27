package src.main;
import src.domain.*;
import java.sql.*;
import java.util.Scanner;
public class Main{
  public static void main(String[] args) {
  Storage storage = new DBStorage();
  Scanner scan = new Scanner(System.in);
  System.out.println("Choose One of the menu below: ");
  System.out.println("1: Add  movie: ");
  System.out.println("2: Add  actor: ");
  System.out.println("3: show all movies: ");
  System.out.println("4: show all movies by actor");
  System.out.println("5: delete a movie: ");
  System.out.println("6:delete an actor: ");
  System.out.println("7: exit");
  String answer = scan.nextLine();
  if(answer.equals("1")){
    System.out.println("Enter Title: ");
     String title = scan.nextLine();
     System.out.println("Enter Genre: ");
     String genre = scan.nextLine();
     storage.addMovie(new Movie(title,genre));
     System.out.println("Congratulations:the movie has been added");
    }
     else if(answer.equals("2")){
     System.out.println("Enter Name: ");
     String name = scan.nextLine();
     System.out.println("Enter Sex: ");
     String sex = scan.nextLine();
     storage.addActor(new Actor(name, sex));
     System.out.println("Congrat:Actor has been added");
   }
   else if(answer.equals("3")){
    System.out.println(storage.showAllMovies());
   }
   else if(answer.equals("4")){
     System.out.println("Enter actor name:");
     String name = scan.nextLine();
     System.out.println(storage.getMoviesByActorName(name));
   }
   else if(answer.equals("5")){
     System.out.println("Enter the title: ");
     String title = scan.nextLine();
     storage.deleteMovie(title);
   }
   else if(answer.equals("6")){
     System.out.println("Enter actors name: ");
     String name = scan.nextLine();
     storage.deleteActor(name);
   }
   else if(answer.equals("7")){
     System.exit(1);
   }
}
}
