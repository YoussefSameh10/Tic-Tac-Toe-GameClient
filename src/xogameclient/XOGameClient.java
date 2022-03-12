/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import xogameclient.services.NetworkConnection;
import xogameclient.services.ResponseManager;

/**
 *
 * @author Youssef
 */
public class XOGameClient extends Application{

    Stage stg;
            
    @Override
    public void start(Stage stage) throws Exception {
        stg = stage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        //networkConnection = NetworkConnection.getInstance();
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void showAlertForError(String msg){
        //Stage stg = (Stage) refreshButton.getScene().getWindow();
        Alert.AlertType type = Alert.AlertType.INFORMATION;
        Alert alert = new Alert(type);

        alert.initModality(Modality.WINDOW_MODAL);
        //alert.initOwner(stg);
        alert.setTitle("Information !");
        alert.getDialogPane().setContentText(msg);
        alert.setHeaderText("Information");
        alert.showAndWait();
    }
}
