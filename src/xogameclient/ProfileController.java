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
    private int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ImageView imageView = new ImageView(getClass().getResource("homeAssets/button.png").toExternalForm());
         imageView.setFitHeight(55);
         imageView.setFitWidth(250);
         
        // TODO
    } 
    
    public void updateData(int id, String username, int score) {
        nameLabel.setText(username);
        emailLabel.setText(Integer.toString(score));
        this.id = id;
        this.username = username;
        this.score = score;
    }
    
    @FXML
    private void didPressBack(MouseEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("OnlineUsersList.fxml"));
            Parent root = Loader.load();
            Stage stage = (Stage)((Node)nameLabel).getScene().getWindow();
            Scene scene = new Scene(root);
            OnlineUsersListController vc = Loader.getController();
            vc.setCurrentUsername(username);
            vc.setCurrentID(id);
            vc.setCurrentScore(score);
            System.out.println("MY profile USERNAME IS: "+username);
            //vc.init(id, username, score);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("List Of Online Users");
            stage.show();
        } catch (IOException ex) {
            XOGameClient.showAlertForError("Can't get back to online users screen!");
            //Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void didPressRecords(MouseEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("OnlineRecords.fxml"));
            Parent root = Loader.load();
            Stage stage = (Stage)((Node)nameLabel).getScene().getWindow();
            Scene scene = new Scene(root);
            OnlineRecordsController vc = Loader.getController();
            vc.init(id, score, username);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("List Of Online Users");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

