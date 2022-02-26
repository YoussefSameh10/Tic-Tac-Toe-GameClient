/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.profilescreen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ProfileController implements Initializable {

    @FXML
    private Button buttn;
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
         ImageView imageView = new ImageView(getClass().getResource("buttn.png").toExternalForm());
        imageView.setFitHeight(55);
       imageView.setFitWidth(250);

       buttn.setGraphic(imageView);
        // TODO
    }    

    @FXML
    private void didPressedButton(ActionEvent event) {
    }
    
}
