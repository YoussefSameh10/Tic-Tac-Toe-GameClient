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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class SingelPlayerController implements Initializable {

    @FXML
    private TextField player1;
    @FXML
    private Button next;
    @FXML
    private ImageView usernameImg;
    @FXML
    private ImageView backBtn;
    @FXML
    private CheckBox recordCheckbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleNextAction(ActionEvent event) throws IOException {
         if(player1.getText().isEmpty() ){
            Stage stg = (Stage) next.getScene().getWindow();
        
            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type);

            alert.initModality(Modality.WINDOW_MODAL);
            alert.initOwner(stg);
            alert.setTitle("Player name not added!");
            alert.getDialogPane().setContentText("Please, Add a valid name  !");
            alert.setHeaderText("Player name not added!");
            alert.showAndWait();
        }else{   
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("AIGameboard.fxml"));
            Parent controller =  Loader.load();
            Scene scene = new Scene(controller);
            AIGameboardController vc = Loader.getController();
            vc.setUesers(player1.getText());
           // vc.setUesers(player1);
           vc.permitRecord = recordCheckbox.isSelected();
            Stage windo =(Stage)((Node)event.getSource()).getScene().getWindow();
            windo.setResizable(false);
            windo.setScene(scene);
            windo.show();
        }
    }

    @FXML
    private void didPressedBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent prevScreen = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(prevScreen);
        stage.setScene(scene);
        stage.show();
    }
    
}
