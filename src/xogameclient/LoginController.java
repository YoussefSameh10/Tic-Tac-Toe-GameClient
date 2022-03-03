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
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class LoginController implements Initializable , LoginControllerInterface{

    @FXML
    private Button loginBtn;
    @FXML
    private ImageView leftImage;
    @FXML
    private TextField usernameTxt;
    @FXML
    private TextField passwordTxt;
    @FXML
    private Button registerBtn;
    private ImageView registerImg;
    @FXML
    private ImageView usernameImg;
    @FXML
    private ImageView passwordImage;
    @FXML
    private ImageView logoImg;
    @FXML
    private ImageView backgroundImg;
    @FXML
    private ImageView rightImage;

    
    private LoginPresenterInterface loginPresenter;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginPresenter = new LoginPresenter(this);
        //configureUI();
    }    
    public void configureUI()
    {
        configureButton(loginBtn, "assets/login.png");
        configureButton(registerBtn, "assets/register.png");
        configureImage(logoImg, "assets/tic-tac-toe.png");
        configureImage(leftImage, "assets/Panel.PNG");
        configureImage(rightImage, "assets/Panel2.png");
        configureImage(usernameImg, "assets/user.png");
        configureImage(passwordImage, "assets/lock.png");
        
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
    public void handleRegisterButtonPress(ActionEvent event) throws IOException {
        
        
        /*Stage stage = (Stage)registerBtn.getScene().getWindow();
        Parent onlineUsersScene = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(onlineUsersScene);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Online Users");
        stage.show();*/
    }

    @Override
    public void gotoListOfOnlineUsers() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("OnlineUsersList.fxml"));
            Stage stage = (Stage)((Node)loginBtn).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("List Of Online Users");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            showLoginErrorAlert();
        }
    }

    @Override
    public void showLoginErrorAlert() {
        Stage stg = (Stage) loginBtn.getScene().getWindow();
        
        Alert.AlertType type = Alert.AlertType.ERROR;
        Alert alert = new Alert(type);

        alert.initModality(Modality.WINDOW_MODAL);
        alert.initOwner(stg);
        alert.setTitle("Login Error");
        alert.getDialogPane().setContentText("Can't Login Successfully !");
        alert.setHeaderText("Login Error!!");
        alert.showAndWait();
    }

    @FXML
    private void didPressLogin(ActionEvent event) {
        loginPresenter.loginPlayer(usernameTxt.getText(), passwordTxt.getText());
    }
    
}
