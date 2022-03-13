/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import xogameclient.services.NetworkConnection;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class OnlineRecordsController implements Initializable, Presenters {

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
    ArrayList<String> fullRecords = new ArrayList<String>();
    String record = "No Record";
    NetworkConnection networkConnection;
    PrintStream ps;
    int id;
    int score;
    String username;
    
    
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
            networkConnection = NetworkConnection.getInstance();
            networkConnection.setPresenter(this);
            ps = networkConnection.getPrintStream();
            configureListView();
            
        } catch (IOException ex) {
            Logger.getLogger(OnlineRecordsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void init(int id, int score, String username) {
        ps.println("GetMyGames,"+id);
        this.id = id;
        this.score = score;
        this.username = username;
    }
    
    private void configureListView(){
        centerList.setItems(records);
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
               
                int requiredLineNumber = centerList.getSelectionModel().getSelectedIndex();
                record = fullRecords.get(requiredLineNumber);
                gotoDisplayGameScreen();
            }
        });
    }
    
    private void gotoDisplayGameScreen() {
        try {
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("DisplayOnlineRecordedGame.fxml"));
            Parent controller =  Loader.load();
            Scene scene = new Scene(controller);
            DisplayOnlineRecordedGameController vc = Loader.getController();
            vc.gameRecord = record;
            vc.id = id;
            vc.score = score;
            vc.username = username;
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
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("Profile.fxml"));
            Parent root = Loader.load();
            Stage stage = (Stage)((Node)leftImg).getScene().getWindow();
            Scene scene = new Scene(root);
            ProfileController vc = Loader.getController();
            vc.updateData(id, username, score);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("List Of Online Users");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void updateList(String[] recordsResponse) {
        for(int i = 0; i < recordsResponse.length; i++) {
            System.out.println("RESULTTT: "+recordsResponse[i]);
            fullRecords.add(recordsResponse[i]);
            records.add(recordsResponse[i].split(":")[1]);
        }
    }
    
    
    @Override
    public void performSuccessAction() {
        
    }

    @Override
    public void performFailureAction() {
        Stage stage = (Stage) leftImg.getScene().getWindow();
        Alert.AlertType type = Alert.AlertType.INFORMATION;
        Alert alert = new Alert(type);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.initOwner(stage);
        alert.setTitle("Connection Closed");
        alert.getDialogPane().setContentText("Server Closed Connection !!");
        alert.setHeaderText("Connection Closed!!");
        alert.showAndWait();
    }
}
