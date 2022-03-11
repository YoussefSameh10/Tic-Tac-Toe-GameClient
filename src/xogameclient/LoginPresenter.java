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
            ex.printStackTrace();
            performFailureAction();
        }
        return 0;
    }
    
    public void performSuccessActionWithParams(String username, int id, int score) {
        Platform.runLater(() ->{  
            loginController.gotoListOfOnlineUsers(username, id, score);
        });
    }
    
    @Override
    public void performSuccessAction() {
        
    }
    
    @Override
    public void performFailureAction() {
        Platform.runLater(() ->{  
            loginController.showLoginErrorAlert();
        });
    }
    
    
}
