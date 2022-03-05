/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import static xogameclient.services.AvailableActions.RegisterResponse;
import xogameclient.services.ClientActions;
import xogameclient.services.NetworkConnection;
import xogameclient.services.ResponseManager;
import xogameclient.services.responsemodels.RegisterResponse;

/**
 *
 * @author Youssef
 */
public class RegisterPresenter implements RegisterPresenterInterface {
    
    
    private RegisterControllerInterface registerController;
    private NetworkConnection networkConnection;
    private Socket server;
    private DataInputStream dis;
    private PrintStream ps;
    private ResponseManager responseManager;
    
    public RegisterPresenter(RegisterControllerInterface registerController) {
        this.registerController = registerController;
    }

    public void addNewPlayer(String username, String password) {
        try {
            responseManager = ResponseManager.getInstance();
            networkConnection = NetworkConnection.getInstance();
            server = networkConnection.getServer();
            dis = networkConnection.getDataInputStream();
            ps = networkConnection.getPrintStream();
            
            new Thread() {
                @Override
                public void run() {
                    ps.println("Register,"+username+","+password);
                    while(true) {
                        try {
                            String response = dis.readLine();
                            ClientActions action = responseManager.parse(response);
                            if((action instanceof RegisterResponse) && ((RegisterResponse)action).isSuccess == true) {
                                Platform.runLater(() ->{
                                    registerController.gotoLogin();
                                });                                
                            }
                            else {
                                Platform.runLater(() ->{
                                    registerController.showRegisterErrorAlert();
                                });
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(RegisterPresenter.class.getName()).log(Level.SEVERE, null, ex);
                            Platform.runLater(() ->{
                                registerController.showRegisterErrorAlert();
                            });
                        }
                    }
                }
            }.start();
            
        } catch (IOException ex) {
            Logger.getLogger(RegisterPresenter.class.getName()).log(Level.SEVERE, null, ex);
            Platform.runLater(() ->{    
                registerController.showRegisterErrorAlert();
            });
        }
    }
}


