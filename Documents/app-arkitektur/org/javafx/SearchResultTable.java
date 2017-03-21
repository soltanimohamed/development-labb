package javafx;

import java.sql.Connection;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import src.domain.Storage;
import src.domain.DBStorage;
import src.domain.Actor;
import src.domain.Movie;

/**
 *
 * @author Narayan
 */

public class SearchResultTable {

    //TABLE VIEW AND DATA
    private Storage storage;
    private ObservableList<ObservableList> data;
    private TableView tableview;

    //MAIN EXECUTOR
  //  public static void main(String[] args) {
    //    launch(args);
    //}
    public SearchResultTable(Storage storage){
      this.storage = storage;
      tableview = new TableView();
    }

    //CONNECTION DATABASE
    public TableView buildData(String searchCriteria, int variety){
          data = FXCollections.observableArrayList();
          try{
            //ResultSet
            ResultSet rs; // = storage.getActorByNameRS(searchCriteria);
            switch (variety) {
              case 1: rs = storage.getActorByNameRS(searchCriteria);
              break;
              //case 2: rs = storage.getMovieByTitleRS(searchCriteria);
              //break;
              default: rs = null; //ugly and unsafe FIX!
            }

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
             System.out.println("Antal kolumner: "+rs.getMetaData().getColumnCount());
             System.out.println("Metadata: "+rs.getMetaData().toString());

            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                System.out.println("colname: "+rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");
          }
          return tableview;
      }
}
