/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Youssef
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ImageView backgroundImageView;

    @FXML
    private Button singlePlayerButton;

    @FXML
    private Button localMultiplayerButton;

    @FXML
    private Button lanMultiplayerButton;

    @FXML
    private void didPressSingleModeButton() throws IOException {
        
        Stage stage = (Stage) lanMultiplayerButton.getScene().getWindow();
        Parent onlineUsersScene = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Scene scene = new Scene(onlineUsersScene);
        stage.setScene(scene);
        stage.setTitle("profile");
        stage.show();

    }

    @FXML
    private void didPressLocalMultiplayerModeButton() throws IOException {
        //  Stage stage = (Stage) lanMultiplayerButton.getScene().getWindow();
        // Parent onlineUsersScene = FXMLLoader.load(getClass().getResource("login.fxml"));
        // Scene scene = new Scene(onlineUsersScene);
        Parent root = FXMLLoader.load(getClass().getResource("gameboardscreen/gameboard.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Online Users");
        stage.show();
    }

    @FXML
    private void didPressLanMultiplayerModeButton() throws IOException {
        Stage stage = (Stage) lanMultiplayerButton.getScene().getWindow();
        Parent onlineUsersScene = FXMLLoader.load(getClass().getResource("OnlineUsersList.fxml"));
        Scene scene = new Scene(onlineUsersScene);
        scene.getStylesheets().add("onlineuserslist.css");
        stage.setScene(scene);
        stage.setTitle("Online Users");
        stage.show();
        System.out.println("SerVerrrrrrrrrrrrrrr");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configureUI();
    }

    private void configureUI() {
        Image image = new Image(getClass().getResource("homeAssets/01.jpg").toExternalForm());
        backgroundImageView.setImage(image);
        ImageView imageView = new ImageView(getClass().getResource("homeAssets/b1.png").toExternalForm());
        imageView.setFitHeight(55);
        imageView.setFitWidth(211);
        singlePlayerButton.setGraphic(imageView);
        ImageView imageView2 = new ImageView(getClass().getResource("homeAssets/b2.png").toExternalForm());
        imageView2.setFitHeight(55);
        imageView2.setFitWidth(211);
        localMultiplayerButton.setGraphic(imageView2);
        ImageView imageView3 = new ImageView(getClass().getResource("homeAssets/b3.png").toExternalForm());
        imageView3.setFitHeight(55);
        imageView3.setFitWidth(211);
        lanMultiplayerButton.setGraphic(imageView3);
    }

}

//Youssef
// Mohammad
//Sandraaaa



//sameh
