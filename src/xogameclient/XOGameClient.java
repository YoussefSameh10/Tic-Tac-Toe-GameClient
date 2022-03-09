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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xogameclient.services.NetworkConnection;
import xogameclient.services.ResponseManager;

/**
 *
 * @author Youssef
 */
public class XOGameClient extends Application implements Presenters{
    NetworkConnection networkConnection;
    ResponseManager responseManager;
    DataInputStream dis;
    PrintStream ps;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        stage.setOnCloseRequest((event) -> {
            performSuccessAction();
            
        });
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        networkConnection = NetworkConnection.getInstance();
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void closePlayerConnection(){
        try {
            responseManager = ResponseManager.getInstance();
            networkConnection = NetworkConnection.getInstance();
            dis = networkConnection.getDataInputStream();
            ps = networkConnection.getPrintStream();
            networkConnection.setPresenter(this);
            ps.println("ClientClose");
            dis.close();
            ps.close();
            networkConnection.getServer().close();
        } catch (IOException ex) {
            Logger.getLogger(XOGameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void performSuccessAction() {
        System.out.println("SUCCESSFULY CLIENT CLOSED CONNECTION");
        closePlayerConnection();
    }

    @Override
    public void performFailureAction() {
        System.out.println("SERVER CAN'T CLOSE CONNECTION");
    }
    
}
