package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import src.domain.Storage;
import src.domain.DBStorage;
import src.domain.Actor;
import src.domain.Movie;
import javax.swing.border.Border;

public class Gui{
  private JFrame mainFrame;
  private JTextField searchField;
  private JButton searchButton;
  private JButton showAllMovieButton;
  private JLabel addMovieLabel;
  private JLabel addActorLabel;
  private JLabel deleteMovieLabel;
  private JLabel deleteActorLabel;
  private JButton clearResultButton;
  private JButton closeButton;
  private JTextArea results;
  private JLabel searchLabel;
  private JPanel top1;
  private JPanel top2;
  private JPanel top3;
  private JPanel top4;
  private JPanel top5;
  private Storage storage;

  public Gui(Storage storage){
    this.storage = storage;
    createMenu();
  }
  private void createMenu(){
    mainFrame = new JFrame("film-lager");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    searchField = new JTextField(20);
    Border border = BorderFactory.createLineBorder(Color.black);
    searchField.setBorder(border);
    searchLabel = new JLabel("search for movie or actor: ");
    results = new JTextArea(10,50);
    results.setEditable(false);
    results.setLineWrap(true);
    results.setWrapStyleWord(true);
    JScrollPane scrollpane = new JScrollPane(results);
    scrollpane.setBorder(border);
    searchButton = new JButton(" search ");
    searchButton.setBorder(border);
     searchButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String input =searchField.getText();
        List<Movie> movies = storage.getMoviesByActorName(input);
        List<Actor> actors = storage.getActorsByMovieTitle(input);
        List<String> result = new ArrayList<>();
        for(Actor a : actors){
          result.add(a.name());
        }
        for(Movie m :movies){
          result.add(m.title() + " : " + m.genre());
        }
        results.setText("");
        for(String s : result){
          results.append(s);
          results.append("\n");
        }
        }
    });
    searchField.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e){
       String input =searchField.getText();
       List<Movie> movies = storage.getMoviesByActorName(input);
       List<Actor> actors = storage.getActorsByMovieTitle(input);
       List<String> result = new ArrayList<>();
       for(Actor a : actors){
         result.add(a.name());
       }
       for(Movie m :movies){
         result.add(m.title() + " : " + m.genre());
       }
       results.setText("");
       for(String s : result){
         results.append(s);
         results.append("\n");
       }
       }
   });
    showAllMovieButton = new JButton("show all Movies");
    showAllMovieButton.setBorder(border);
    showAllMovieButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        List<Movie> allMovies = storage.showAllMovies();
        List<String> movies = new ArrayList<>();
        int i = 1;
        for(Movie m : allMovies){
          movies.add(i + ": " + m.toString());
          i++;
        }
        results.setText("");
        for(String s : movies){
          results.append(s);
          results.append("\n");
        }
      }
    });

    top1 = new JPanel(new GridBagLayout());
    top1.setBackground(Color.GRAY);
    top1.setBorder(border);
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(10,5,10,5);
    c.gridx=0;
    c.gridy=0;
    c.anchor = GridBagConstraints.LINE_START;
    top1.add((searchLabel), c);
    c.gridx ++;
    top1.add((searchField), c);
    c.gridx ++;
    top1.add((searchButton), c);
    c.gridy ++;
    top2 = new JPanel(new BorderLayout());
    top2.setBorder(border);
    top2.add(showAllMovieButton,BorderLayout.NORTH);
    addMovieLabel = new JLabel("add new movie");
    addMovieLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    addMovieLabel.addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent e){
      new AddMovieGui(storage);
      }
    });
    addActorLabel = new JLabel("add new actor");
    addActorLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    addActorLabel.addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent e){
      new AddActorGui(storage);
      }
    });
    deleteMovieLabel = new JLabel("delete a movie");
    deleteMovieLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    deleteMovieLabel.addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent e){
        new DeleteMovie(storage);
      }
    });
    deleteActorLabel = new JLabel("delete an actor");
    deleteActorLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    deleteActorLabel.addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent e){
        new DeleteActor(storage);
      }
    });
    top3 = new JPanel(new GridBagLayout());
    top3.setBorder(border);
    GridBagConstraints c1 = new GridBagConstraints();
    c1.insets = new Insets(10,5,10,5);
    c1.gridx=0;
    c1.gridy=1;
    c1.anchor = GridBagConstraints.LINE_END;
    top3.add(addMovieLabel, c1);
    c1.gridx=0;
    c1.gridy=3;
    top3.add(addActorLabel, c1);
    c1.gridx=0;
    c1.gridy=5;
    top3.add(deleteMovieLabel, c1);
    c1.gridx=0;
    c1.gridy=7;
    top3.add(deleteActorLabel, c1);
    top5 = new JPanel();
    top5.setBorder(border);
    clearResultButton = new JButton("clear results");
    clearResultButton.setBorder(border);
    clearResultButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        results.setText("");
      }
    });
    closeButton = new JButton("close application");
    closeButton.setBorder(border);
    closeButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.exit(1);
      }
    });
    top5.add(clearResultButton);
    top5.add(closeButton);
    mainFrame.add(top1, BorderLayout.NORTH);
    mainFrame.add(scrollpane, BorderLayout.CENTER);
    mainFrame.add(top2, BorderLayout.EAST);
    mainFrame.add(top3, BorderLayout.WEST);
    mainFrame.add(top5, BorderLayout.SOUTH);
    mainFrame.setPreferredSize(new Dimension(600,400));
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
  }
  public static void main(String[] args){
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new Gui(new DBStorage());
      }
    });
  }
}
