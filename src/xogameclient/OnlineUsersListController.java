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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import xogameclient.services.NetworkConnection;
import xogameclient.services.ResponseManager;

/**
 * FXML Controller class
 *
 * @author sandra
 */
public class OnlineUsersListController implements Initializable, OnlineUsersListControllerInterface {

//    private ImageView BGImage;
//    private ListView<String> onlineuserLV;
    /**
     * Initializes the controller class.
     */
    /*
    ObservableList<String> names = FXCollections.observableArrayList(
          "Sandra George", "Tasnim Hatem", "Sameh Reda", "Youssef Sameh", "Mohamed Amr", "Sarah Nassrat", "Eman Abo Bakr","Sameh Reda", "Youssef Sameh", "Mohamed Amr", "Sarah Nassrat", "Eman Abo Bakr","Sameh Reda", "Youssef Sameh", "Mohamed Amr", "Sarah Nassrat", "Eman Abo Bakr");
     */
    Stage stg;
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
    
    private ArrayList<Pair<String, Integer>> onlinePlayers = new ArrayList<Pair<String, Integer>>();
    
    private String currentUsername;
    private NetworkConnection networkConnection;
    private DataInputStream dis;
    private PrintStream ps;
    private ResponseManager responseManager;
    
    @Override
    public void gotoGamme(String id1, String id2, String name1, String name2, String score1, String score2, String first) {
        Platform.runLater(() -> {
            navigateToGameScreen(id1, id2, name1, name2, score1, score2, "false");
        });
    }
    
    @Override
    public void showrefuseAleart(String name) {
        Platform.runLater(() -> {
            stg = (Stage) leftImg.getScene().getWindow();
            
            Alert.AlertType type = Alert.AlertType.WARNING;
            Alert alert = new Alert(type);
            
            alert.initModality(Modality.WINDOW_MODAL);
            alert.initOwner(stg);
            alert.setTitle("Oopps");
            alert.getDialogPane().setContentText("player " + name + " did not accept your Request");
            alert.showAndWait();
        });
        
    }
    
    public class UsersCustomCell extends ListCell<String> {
        
        public UsersCustomCell() {
            
        }
        
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.

            if (item != null) {
                setText(item);
                setFont(new Font("Gaegu", 25));
                setTextFill(Color.BLACK);
            }
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            responseManager = ResponseManager.getInstance();
            networkConnection = NetworkConnection.getInstance();
            dis = networkConnection.getDataInputStream();
            ps = networkConnection.getPrintStream();
            networkConnection.setPresenter(this);
            ps.println("GetOnlinePlayersList");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        centerList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                String name = centerList.getSelectionModel().getSelectedItem();
                int index = centerList.getSelectionModel().getSelectedIndex();
                int id = onlinePlayers.get(index).getValue();
                // need  my name 
                ps.println("ChallengeRequest," + "1," + id);
            }
        });
    }

//    private void configureUI() {
//        Image image = new Image(getClass().getResource("listAssets/BG.jpg").toExternalForm());
//        BGImage.setImage(image);
//    }
    private void configureListView() {
        
        for (int i = 0; i < onlinePlayers.size(); i++) {
            if (!onlinePlayers.get(i).getKey().equals(currentUsername)) {
                centerList.getItems().add(onlinePlayers.get(i).getKey());
            }
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
    
    public String[] parseServerResponse(String msg) {
        String[] parts = msg.split(",");
        return parts;
    }
    
    public void setCurrentUsername(String _username) {
        currentUsername = _username;
    }
    
    @Override
    public void performSuccessAction() {
        String serverResponse = networkConnection.getResponse();
        String[] parsedResponse = serverResponse.split(",");
        String[] allUsers = parsedResponse[1].split(" ");
        for (int i = 0; i < allUsers.length; i++) {
            
            String[] parsedPlayer = allUsers[i].split(":");
            String currentPlayerUsername = parsedPlayer[0];
            Integer currentPlayerID = Integer.parseInt(parsedPlayer[1]);
            // currentUsername sahat null
            if (!currentUsername.equals(currentPlayerUsername)) {
                onlinePlayers.add(new Pair<String, Integer>(currentPlayerUsername, currentPlayerID));
            }
        }
        
        configureListView();
    }
    
    @Override
    public void showAleart(String id1, String id2, String name1, String name2, String score1, String score2, String first) {
        Platform.runLater(() -> {
            Confirem(id1, id2, name1, name2, score1, score2);
        });
    }
    
    @Override
    public void performFailureAction() {

    }
    
    public void Confirem(String id1, String id2, String name1, String name2, String score1, String score2) {
        System.out.println("data of two playerrs is: " + id1 + id2 + name1 + name2);
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("CONFIRMATION");
        alert.setContentText(" Would you Play with " + name1);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ps.println("ChallengeResponse,accept," + id1 + "," + id2);
            navigateToGameScreen(id1, id2, name1, name2, score1, score2, "true");
        } else {
            ps.println("ChallengeResponse,notAccept," + id1 + "," + id2);
        }
    }
    
    public void navigateToGameScreen(String id1, String id2, String name1, String name2, String score1, String score2, String first) {
        
        try {
            Stage stage = (Stage) ((Node) rightImg).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MultiplayerGameBoard.fxml"));
            Parent gameScene = loader.load();
            MultiplayerGameBoardController controller = (MultiplayerGameBoardController) loader.getController();
            
            MultiplayerGameBoardPresenter boardPresenter = new MultiplayerGameBoardPresenter(name1, name2, Integer.parseInt(id1), Integer.parseInt(id2), Integer.parseInt(score1), Integer.parseInt(id2), Boolean.getBoolean(first));
            controller.setButtonsDisabled(Boolean.getBoolean(first));//false if you are the game initiative
            controller.presenter = boardPresenter;
            boardPresenter.multiPlayerGameController = controller;
            NetworkConnection.getInstance().setPresenter(boardPresenter);
            Scene scene = new Scene(gameScene);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("PlayGame");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OnlineUsersListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
