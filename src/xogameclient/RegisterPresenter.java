/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import xogameclient.services.NetworkConnection;
import xogameclient.services.ResponseManager;

/**
 *
 * @author Youssef
 */
public class RegisterPresenter implements RegisterPresenterInterface, Presenters {
    
    
    private RegisterControllerInterface registerController;
    private NetworkConnection networkConnection;
    private PrintStream ps;
    private ResponseManager responseManager;
    
    public RegisterPresenter(RegisterControllerInterface registerController) {
        this.registerController = registerController;
    }

    public void addNewPlayer(String username, String password) {
        try {
            responseManager = ResponseManager.getInstance();
            networkConnection = NetworkConnection.getInstance();
            ps = networkConnection.getPrintStream();
            networkConnection.setPresenter(this);
            ps.println("Register,"+username+","+password);
            
        } catch (IOException ex) {
            //Logger.getLogger(RegisterPresenter.class.getName()).log(Level.SEVERE, null, ex);
            performFailureAction();
        }
    }

    @Override
    public void performSuccessAction() {
        Platform.runLater(() ->{
            registerController.gotoLogin();
        });
    }

    @Override
    public void performFailureAction() {
        Platform.runLater(() ->{
            registerController.showRegisterErrorAlert();
        });
    }
}


