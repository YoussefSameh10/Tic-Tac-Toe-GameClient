/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import xogameclient.services.NetworkConnection;
import xogameclient.services.ResponseManager;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class LoginController implements Initializable, LoginControllerInterface {

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
    @FXML
    private ImageView backBtn;
    NetworkConnection networkConnection;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            networkConnection = NetworkConnection.getInstance();
            loginPresenter = new LoginPresenter(this);
            networkConnection.setPresenter2(this);
            configureUI();
            loginBtn.setDisable(true);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void configureUI() {
        configureButton(loginBtn, "homeAssets/login.png");
        configureButton(registerBtn, "homeAssets/register.png");
        configureImage(logoImg, "homeAssets/tic-tac-toe.png");
        configureImage(leftImage, "homeAssets/Panel.PNG");
        configureImage(rightImage, "homeAssets/Panel2.png");
        configureImage(usernameImg, "homeAssets/user.png");
        configureImage(passwordImage, "homeAssets/lock.png");
    }

    public void configureButton(Button b, String path) {
        ImageView imageView = new ImageView(getClass().getResource(path).toExternalForm());
        imageView.setFitHeight(70);
        imageView.setFitWidth(225);
        b.setGraphic(imageView);
    }

    public void configureImage(ImageView img, String path) {
        Image myImage = new Image(getClass().getResourceAsStream(path));
        img.setImage(myImage);
    }
    
    

    @FXML
    public void handleRegisterButtonPress(ActionEvent event) throws IOException {

        Stage stage = (Stage) registerBtn.getScene().getWindow();
        Parent onlineUsersScene = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(onlineUsersScene);          
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.show();
    }

    @Override
    public void gotoListOfOnlineUsers(String username, int id, int score) {
        try {
            
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("OnlineUsersList.fxml"));
            Parent root = Loader.load();
            Stage stage = (Stage)((Node)loginBtn).getScene().getWindow();
            Scene scene = new Scene(root);
            OnlineUsersListController vc = Loader.getController();
            vc.setCurrentUsername(username);
            vc.setCurrentID(id);
            vc.setCurrentScore(score);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("List Of Online Users");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
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
        Stage stage = (Stage) registerBtn.getScene().getWindow();
        stage.setOnCloseRequest((events) -> {
            System.out.println("HELLO FROM LOGIN INIT");
            networkConnection.closePlayerConnection();

        });
        if(!usernameTxt.getText().isEmpty() && !passwordTxt.getText().isEmpty()){
            loginPresenter.loginPlayer(usernameTxt.getText(), passwordTxt.getText());
        }else{
            showLoginErrorAlert();
            
        }
    }

    @FXML
    private void didPressBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent prevScreen = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(prevScreen);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void userNameChanged(KeyEvent event) {
        shouldEnableButton();
    }

    @FXML
    private void passChanged(KeyEvent event) {
        shouldEnableButton();
    }

    private void shouldEnableButton() {
        if(usernameTxt.getText().isEmpty() || passwordTxt.getText().isEmpty()){
            loginBtn.setDisable(true);
        }else{
            loginBtn.setDisable(false);
        }
    }
    
    

    public void performSuccessAction() {
        Stage stg = (Stage) registerBtn.getScene().getWindow();
        Platform.runLater(() ->{  
            serverClosedConnectionAlert(stg);
        });
    }

    public void performFailureAction() {
        System.out.println("SERVER CAN'T CLOSE CONNECTION");
    }
    
    
    public void serverClosedConnectionAlert(Stage stg) {
        Alert.AlertType type = Alert.AlertType.INFORMATION;
        Alert alert = new Alert(type);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.initOwner(stg);
        alert.setTitle("Connection Closed");
        alert.getDialogPane().setContentText("Server Closed Connection !!");
        alert.setHeaderText("Connection Closed!!");
        alert.showAndWait();
    }
}
