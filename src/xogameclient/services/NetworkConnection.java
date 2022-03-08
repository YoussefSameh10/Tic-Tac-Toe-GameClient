/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.services;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import xogameclient.LoginPresenter;
import xogameclient.MultiplayerGameBoardPresenter;
import xogameclient.Presenters;
import xogameclient.RegisterPresenter;
import xogameclient.services.responsemodels.LoginResponse;
import xogameclient.services.responsemodels.Move;
import xogameclient.services.responsemodels.RegisterResponse;

/**
 *
 * @author Youssef
 */
public class NetworkConnection {

    private static NetworkConnection instance;
    private Socket server;
    private DataInputStream dis;
    private PrintStream ps;
    private Thread t1;
    private String response;
    ResponseManager responseManager;
    private Presenters presenter;

    public void setPresenter(Presenters presenter) {
        System.out.println("presenter setted to " + presenter);
        this.presenter = presenter;
    }

    public Socket getServer() {
        return server;
    }

    public DataInputStream getDataInputStream() {
        return dis;
    }

    public PrintStream getPrintStream() {
        return ps;
    }

    private NetworkConnection() throws IOException {
        server = new Socket("127.0.0.1", 8080);
        dis = new DataInputStream(server.getInputStream());
        ps = new PrintStream(server.getOutputStream());
        responseManager = ResponseManager.getInstance();
        startThread();
    }

    public static NetworkConnection getInstance() throws IOException {
        if (instance == null) {
            instance = new NetworkConnection();
        }
        return instance;
    }

    private void startThread() {

        new Thread() {
            @Override
            public void run() {
                try {
                    while ((server.isConnected())) {
                        response = dis.readLine();
                        manage();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(NetworkConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }

    private void manage() {
        System.out.println("in manage " + presenter);
        ClientActions action = responseManager.parse(response);
        if (action instanceof LoginResponse) {
            manageLogin(action);
        } else if (action instanceof RegisterResponse) {
            manageRegister(action);
        } else if (action instanceof Move) {

            manageMove(action);
        } else if (true) {

        }
    }

    private void manageLogin(ClientActions action) {
        if (((LoginResponse) action).loginSuccess == true) {
            Platform.runLater(() -> {
                System.out.println("Going to online list");
                presenter.performSuccessAction();
            });
        } else {
            Platform.runLater(() -> {
                presenter.performFailureAction();
            });
        }
    }

    private void manageRegister(ClientActions action) {
        if (((RegisterResponse) action).isSuccess == true) {
            ((RegisterPresenter) presenter).performSuccessAction();
        } else {
            presenter.performFailureAction();
        }
    }

    private void manageMove(ClientActions action) {
        System.out.println("in manageMove" + presenter);
       
         System.out.println("Move Recieved from connection is" +        ((Move) action).getCellNumber());
       MultiplayerGameBoardPresenter pres = (MultiplayerGameBoardPresenter)  presenter ;
           Platform.runLater(() -> {
                   pres.readMoveFromOpponent( ((Move) action).getCellNumber());
            });
   
    }

}
