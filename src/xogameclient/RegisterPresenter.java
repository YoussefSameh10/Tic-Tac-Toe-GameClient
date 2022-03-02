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

/**
 *
 * @author Youssef
 */
public class RegisterPresenter implements RegisterPresenterInterface {
    
    Socket server;
    DataInputStream dis;
    PrintStream ps;
    private RegisterControllerInterface registerController;

    public RegisterPresenter(RegisterControllerInterface registerController) {
        this.registerController = registerController;
    }

    public void addNewPlayer(String username, String password) {
        try {
            server = new Socket("127.0.0.1", 8080);
            dis = new DataInputStream(server.getInputStream());
            ps = new PrintStream(server.getOutputStream());
            
            new Thread() {
                @Override
                public void run() {
                    ps.println("Register,"+username+","+password);
                    while(true) {
                        try {
                            String response = dis.readLine();
                            if(response.equals("Success")) {
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


