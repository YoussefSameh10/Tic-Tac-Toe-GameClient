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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ProfileController implements Initializable {

    @FXML
    private ImageView profileImage;
    @FXML
    private Label nameLabel;
    @FXML
    private Label emailLabel;
    
    private String username;
    private int score;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ImageView imageView = new ImageView(getClass().getResource("homeAssets/buttn.png").toExternalForm());
         imageView.setFitHeight(55);
         imageView.setFitWidth(250);
         
        // TODO
    } 
    
    public void updateData(String username, int score) {
        nameLabel.setText(username);
        emailLabel.setText(Integer.toString(score));
    }
    
    @FXML
    private void didPressBack(MouseEvent event) {
        try {
            System.out.println("Name in Profile when back clicked: "+username);
            System.out.println("Score in Profile when back clicked: "+score);
            Stage stage = (Stage) nameLabel.getScene().getWindow();
            Parent mainScene = FXMLLoader.load(getClass().getResource("OnlineUsersList.fxml"));
            Scene scene = new Scene(mainScene);
            //scene.getStylesheets().add("onlineuserslist.css");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Main");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void didPressLogout(MouseEvent event) {
    }
}

