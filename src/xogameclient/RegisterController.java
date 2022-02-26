/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class RegisterController implements Initializable {

    @FXML
    private ImageView leftImage;
    @FXML
    private ImageView rightImage;
    @FXML
    private ImageView logoImage;
    @FXML
    private Button registerBtn;
    @FXML
    private TextField usernameTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private PasswordField confirmTxt;
    @FXML
    private ImageView usernameImg;
    @FXML
    private ImageView passwordImg;
    @FXML
    private ImageView confirmImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //configureUI();
    }    
    public void configureUI()
    {
        configureButton(registerBtn, "assets/register.png");
        configureImage(logoImage, "assets/tic-tac-toe.png");
        configureImage(leftImage, "assets/Panel.PNG");
        configureImage(rightImage, "assets/Panel2.png");
        configureImage(usernameImg, "assets/user.png");
        configureImage(passwordImg, "assets/unlocked.png");
        configureImage(confirmImg, "assets/lock.png");
        
    }
    
    public void configureButton(Button b, String path)
    {
        ImageView imageView = new ImageView(getClass().getResource(path).toExternalForm());
        imageView.setFitHeight(70);
        imageView.setFitWidth(225);
        b.setGraphic(imageView);
    }
    
    public void configureImage(ImageView img, String path)
    {
        Image myImage = new Image (getClass().getResourceAsStream(path));
        img.setImage(myImage);
    }
    
    @FXML
    public void handleLoginButtonPress(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.show();
        
    }
}
