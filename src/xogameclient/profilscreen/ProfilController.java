/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.profilscreen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ProfilController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private ImageView name;
    @FXML
    private ImageView email;
    @FXML
    private ImageView backgroungImage;
    @FXML
    private ImageView profile;
    @FXML
    private Button buttn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //emailLabel.setFont(new Font("Gaegu",30));
      ImageView imageView = new ImageView(getClass().getResource("buttn.png").toExternalForm());
        imageView.setFitHeight(55);
        imageView.setFitWidth(250);

       buttn.setGraphic(imageView);
       
       Image image = new Image(getClass().getResource("prof2.png").toExternalForm());
        profile.setImage(image);
      
        // TODO
    }  

    @FXML
    private void didPreesedButton(ActionEvent event) {
    }
    

    
}
