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
import xogameclient.services.ClientActions;
import xogameclient.services.NetworkConnection;
import xogameclient.services.ResponseManager;
import xogameclient.services.responsemodels.LoginResponse;
import xogameclient.services.responsemodels.RegisterResponse;

/**
 *
 * @author sandra
 */
public class LoginPresenter implements LoginPresenterInterface{

    private LoginControllerInterface loginController;
    private NetworkConnection networkConnection;
    private Socket server;
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
            server = networkConnection.getServer();
            dis = networkConnection.getDataInputStream();
            ps = networkConnection.getPrintStream();
            new Thread(){
                @Override
                public void run() {
                    super.run(); //To change body of generated methods, choose Tools | Templates.
                    ps.println("Login,"+userName+","+password);
                    while (true) {                        
                        try{
                            String response = dis.readLine();
                            System.out.println(response);
                            ClientActions action = responseManager.parse(response);
                            System.out.println(action);
                            if((action instanceof LoginResponse) && ((LoginResponse)action).loginSuccess == true){
                                Platform.runLater(() ->{    
                                    loginController.gotoListOfOnlineUsers();
                                });
                            }else{
                                Platform.runLater(() ->{    
                                    loginController.showLoginErrorAlert();
                                });
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(LoginPresenter.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
            }.start();
        }catch (IOException ex) {
            Logger.getLogger(RegisterPresenter.class.getName()).log(Level.SEVERE, null, ex);
            Platform.runLater(() ->{    
                loginController.showLoginErrorAlert();
            });
        }
        return 0;
    }
    
}
