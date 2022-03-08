/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import com.sun.org.apache.xml.internal.utils.NameSpace;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.Pair;
import xogameclient.services.NetworkConnection;
import xogameclient.services.ResponseManager;

/**
 * FXML Controller class
 *
 * @author sandra
 */
public class OnlineUsersListController implements Initializable, Presenters {

//    private ImageView BGImage;
//    private ListView<String> onlineuserLV;

    /**
     * Initializes the controller class.
     */
    /*
    ObservableList<String> names = FXCollections.observableArrayList(
          "Sandra George", "Tasnim Hatem", "Sameh Reda", "Youssef Sameh", "Mohamed Amr", "Sarah Nassrat", "Eman Abo Bakr","Sameh Reda", "Youssef Sameh", "Mohamed Amr", "Sarah Nassrat", "Eman Abo Bakr","Sameh Reda", "Youssef Sameh", "Mohamed Amr", "Sarah Nassrat", "Eman Abo Bakr");
    */      
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
    
    private ArrayList<Pair<String, Integer> > onlinePlayers = new ArrayList<Pair<String, Integer> >();
    
    private String currentUsername;
    private NetworkConnection networkConnection;
    private DataInputStream dis;
    private PrintStream ps;
    private ResponseManager responseManager;

    
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            System.out.println("This is OnlineUsersList Initializer");
            responseManager = ResponseManager.getInstance();
            networkConnection = NetworkConnection.getInstance();
            dis = networkConnection.getDataInputStream();
            ps = networkConnection.getPrintStream();
            networkConnection.setPresenter(this);
            ps.println("GetOnlinePlayersList");
            
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }    
    
//    private void configureUI() {
//        Image image = new Image(getClass().getResource("listAssets/BG.jpg").toExternalForm());
//        BGImage.setImage(image);
//    }
    private void configureListView(){
       
        System.out.println("Configure List View Function !!!");
        for (int i=0 ; i<onlinePlayers.size() ; i++)
        {
            if (!onlinePlayers.get(i).getKey().equals(currentUsername))
                centerList.getItems().add(onlinePlayers.get(i).getKey());
        }
        centerList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //onlineuserLV.setStyle("-fx-background-color: transparent;");
        centerList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new UsersCustomCell();
            }
        });
    }
    
    public String[] parseServerResponse(String msg)
    {
        String[] parts = msg.split(",");
        return parts;
    }
    
    public void setCurrentUsername(String _username)
    {
        System.out.println("Player username sent: " + _username);
        currentUsername = _username;
    }
    
    
    @Override
    public void performSuccessAction() {
        String serverResponse = networkConnection.getResponse();
        System.out.println("This is the server response from getting online players " + serverResponse);
        String[] parsedResponse = serverResponse.split(",");
        String[] allUsers = parsedResponse[1].split(" ");
        for (int i=0 ; i<allUsers.length ; i++)
        {
            
            String[] parsedPlayer = allUsers[i].split(":");
            String currentPlayerUsername = parsedPlayer[0];
            Integer currentPlayerID = Integer.parseInt(parsedPlayer[1]);
            if (!currentUsername.equals(currentPlayerUsername))
            {
                onlinePlayers.add(new Pair<String, Integer>(currentPlayerUsername, currentPlayerID) );
            }
        }
        
        configureListView();
        System.out.println("There are " + onlinePlayers.size() + " online players in the server...");
    }

    @Override
    public void performFailureAction() {
        System.out.println("Failed to get online players list !!");
    }
    
}
