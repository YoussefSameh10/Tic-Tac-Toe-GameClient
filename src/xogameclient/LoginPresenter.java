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
import javafx.application.Platform;
import xogameclient.services.NetworkConnection;
import xogameclient.services.ResponseManager;

/**
 *
 * @author sandra
 */
public class LoginPresenter implements LoginPresenterInterface, Presenters{

    private LoginControllerInterface loginController;
    private NetworkConnection networkConnection;
    private DataInputStream dis;
    private PrintStream ps;
    private ResponseManager responseManager;

    public LoginPresenter(LoginControllerInterface loginController) {
        this.loginController = loginController;
    }
    
    @Override
    public int loginPlayer(String userName, String password) {
        try{
            responseManager = ResponseManager.getInstance();
            networkConnection = NetworkConnection.getInstance();
            dis = networkConnection.getDataInputStream();
            ps = networkConnection.getPrintStream();
            networkConnection.setPresenter(this);
            ps.println("Login,"+userName+","+password);
        }catch (IOException ex) {
            Logger.getLogger(RegisterPresenter.class.getName()).log(Level.SEVERE, null, ex);
            performFailureAction();
        }
        return 0;
    }
    
    public void performSuccessAction() {
        Platform.runLater(() ->{  
            loginController.gotoListOfOnlineUsers();
        });
    }
    
    public void performFailureAction() {
        Platform.runLater(() ->{  
            loginController.showLoginErrorAlert();
        });
    }
    
    
    
}
