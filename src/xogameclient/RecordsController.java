/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;



/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class RecordsController implements Initializable {

    @FXML
    private BorderPane OUBorderPane;
    @FXML
    private ImageView OUTopImg;
    @FXML
    private AnchorPane leftAnchor;
    @FXML
    private ImageView leftImg;
    @FXML
    private AnchorPane centerAnchor;
    @FXML
    private ListView<String> centerList;
    @FXML
    private AnchorPane rightAnchor;
    @FXML
    private ImageView rightImg;

    
    ObservableList<String> records = FXCollections.observableArrayList();
    String record = "No Record";
    
    
    public class UsersCustomCell extends ListCell<String>{
        public UsersCustomCell(){
            
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
            
            if(item != null){
                setText(item);
                setFont(new Font("Gaegu", 25));
                setTextFill(Color.BLACK);
            }
        }
        
        
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
      File recordsFile = new File("Records.txt");
      Scanner myReader = new Scanner(recordsFile);
      while (myReader.hasNextLine()) {
        String record = myReader.nextLine();
        records.add(record.split(",")[1]);
        System.out.println(record);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
        configureListView();
    }    
    
    private void configureListView(){
        centerList.setItems(records);
        System.out.println("RRRRRRRRRRR");
        centerList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //onlineuserLV.setStyle("-fx-background-color: transparent;");
        centerList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new UsersCustomCell();
            }
        });
        
        centerList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    int requiredLineNumber = centerList.getSelectionModel().getSelectedIndex();
                    int counter = 0;
                    
                    System.out.println("clicked on " + centerList.getSelectionModel().getSelectedIndex());
                    File recordsFile = new File("Records.txt");
                    Scanner myReader = new Scanner(recordsFile);
                    while (myReader.hasNextLine() && counter <= requiredLineNumber) {
                        record = myReader.nextLine();
                        counter++;
                    }
                    System.out.println("The record: "+record);
                    gotoDisplayGameScreen();
                    myReader.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void gotoDisplayGameScreen() {
        try {
            System.out.println("Sent Record is: "+record);
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("DisplayRecordedGame.fxml"));
            Parent controller =  Loader.load();
            Scene scene = new Scene(controller);
            DisplayRecordedGameController vc = Loader.getController();
            
            vc.gameRecord = record;
            
            Stage window =(Stage)rightImg.getScene().getWindow();
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void didPressback(MouseEvent event) {
        try {
            Stage stage = (Stage) leftImg.getScene().getWindow();
            Parent profileScene = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Scene scene = new Scene(profileScene);
            //scene.getStylesheets().add("onlineuserslist.css");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Profile");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
