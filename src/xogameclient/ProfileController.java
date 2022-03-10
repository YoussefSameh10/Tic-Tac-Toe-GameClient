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


    @FXML
    private void didPressRecords(MouseEvent event) throws IOException {
        Stage stage = (Stage) profileImage.getScene().getWindow();
        Parent recordsScene = FXMLLoader.load(getClass().getResource("Records.fxml"));
        Scene scene = new Scene(recordsScene);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Records");
        stage.show();
    }

    @FXML
    private void didPressBack(MouseEvent event) {
        try {
            Stage stage = (Stage) nameLabel.getScene().getWindow();
            Parent mainScene = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
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
    
}
