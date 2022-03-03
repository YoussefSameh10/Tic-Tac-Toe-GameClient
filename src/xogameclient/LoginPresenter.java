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

/**
 *
 * @author sandra
 */
public class LoginPresenter implements LoginPresenterInterface{

    Socket server;
    DataInputStream dis;
    PrintStream ps;
    private LoginControllerInterface loginController;

    public LoginPresenter(LoginControllerInterface loginController) {
        this.loginController = loginController;
    }
    
    
    
    @Override
    public int loginPlayer(String userName, String password) {
        try{
            server = new Socket("127.0.0.1", 8080);
            dis = new DataInputStream(server.getInputStream());
            ps = new PrintStream(server.getOutputStream());
            
            new Thread(){
                @Override
                public void run() {
                    super.run(); //To change body of generated methods, choose Tools | Templates.
                    ps.println("Login,"+userName+","+password);
                    while (true) {                        
                        try{
                            String response = dis.readLine();
                            System.out.println(response);
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
