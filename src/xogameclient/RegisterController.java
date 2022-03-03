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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class RegisterController implements Initializable, RegisterControllerInterface {

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
    @FXML
    private Button loginBtn;
    
    private RegisterPresenterInterface registerPresenter;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registerPresenter = new RegisterPresenter(this);
        configureUI();
        registerBtn.setDisable(true);
    }    
    public void configureUI()
    {
        configureButton(registerBtn, "homeAssets/register.png");
        configureButton(loginBtn, "homeAssets/login.png");
        configureImage(logoImage, "homeAssets/tic-tac-toe.png");
        configureImage(leftImage, "homeAssets/Panel.PNG");
        configureImage(rightImage, "homeAssets/Panel2.png");
        configureImage(usernameImg, "homeAssets/user.png");
        configureImage(passwordImg, "homeAssets/unlocked.png");
        configureImage(confirmImg, "homeAssets/lock.png");
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
    private void handleRegisterButtonPress(ActionEvent event) {
        registerPresenter.addNewPlayer(usernameTxt.getText(), passwordTxt.getText());
    }

    
    @FXML
    public void handleLoginButtonPress(ActionEvent event) throws IOException {
        gotoLogin();
    }
    
    public void gotoLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = (Stage)((Node)registerBtn).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            showRegisterErrorAlert();
        }
    }
    
    public void showRegisterErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Can't Register! Try Again.");
        alert.show();
    }

    @FXML
    private void usernameTextChanged(KeyEvent event) {
        shouldEnableButton();
    }

    @FXML
    private void passwordTextChanged(KeyEvent event) {
        shouldEnableButton();
    }

    @FXML
    private void confirmPasswordTextChanged(KeyEvent event) {
        shouldEnableButton();
    }
    
    private void shouldEnableButton() {
        if(usernameTxt.getText().isEmpty() ||
            passwordTxt.getText().isEmpty() ||
            confirmTxt.getText().isEmpty() ||
            !(passwordTxt.getText().equals(confirmTxt.getText()))
        ) {
            registerBtn.setDisable(true);
        }
        else{
            registerBtn.setDisable(false);
        }
    }

    
    

    
    
}
