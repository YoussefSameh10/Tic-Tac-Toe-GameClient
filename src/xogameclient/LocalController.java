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
import javafx.scene.paint.Stop;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class LocalController implements Initializable {
   
    @FXML
    private TextField player1;
    @FXML
    private TextField player2;
    @FXML
    private ImageView usernameImg;
    @FXML
    private ImageView usernameImg1;
    @FXML
    private Button next;
    @FXML
    private ImageView backBtn;
    @FXML
    private CheckBox recordCheckbox;
    
    //int scoreX = 0, scoreO =0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleNextAction(ActionEvent event) throws IOException {
        
        if(player1.getText().isEmpty() || player2.getText().isEmpty()){
            Stage stg = (Stage) next.getScene().getWindow();
        
            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type);

            alert.initModality(Modality.WINDOW_MODAL);
            alert.initOwner(stg);
            alert.setTitle("Players names not added!");
            alert.getDialogPane().setContentText("Please, Add a valid name for each player !");
            alert.setHeaderText("Players names not added!");
            alert.showAndWait();
        }else{   
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("gameboard.fxml"));
            Parent controller =  Loader.load();
            Scene scene = new Scene(controller);
            GameboardController vc = Loader.getController();
            vc.playerX = player1.getText();
            vc.playerO = player2.getText();
            vc.permitRecord = recordCheckbox.isSelected();
            vc.setUesers(player1.getText(),player2.getText());
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
