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
 * @author amin
 */
interface MultiPlayerGamePresenterInterface {

}

interface MultiPlayerGameControllerInterface {

}

public class MultiplayerGameBoardPresenter implements MultiPlayerGamePresenterInterface {

    private MultiPlayerGameControllerInterface multiPlayerGameController;
    private String playerOneName, playerTwoName;
    private int playerOneScore, playerTwoScore;
    private int playerOneId, playerTwoId;
    private boolean isMyTurn;

    private NetworkConnection networkConnection;
    private Socket server;
    private DataInputStream dis;
    private PrintStream ps;
    private ResponseManager responseManager;

    public MultiplayerGameBoardPresenter(String playerOneName, String playerTwoName, int playerOneId, int playerTwoId, int playerOneScore, int playerTwoScore, boolean isMyTurn) {
        try {
            this.playerOneName = playerOneName;
            this.playerTwoName = playerTwoName;
            this.playerOneScore = playerOneScore;
            this.playerTwoScore = playerTwoScore;
            this.playerOneId = playerOneId;
            this.playerTwoId = playerTwoId;
            this.isMyTurn = isMyTurn;

            responseManager = ResponseManager.getInstance();
            networkConnection = NetworkConnection.getInstance();
            server = networkConnection.getServer();
            dis = networkConnection.getDataInputStream();
            ps = networkConnection.getPrintStream();

          /*  new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            System.out.println("WE RECIEVED RESPONSE");

                         String input =    dis.readUTF();
                            System.out.println(input);
                            //      ps.println("Move,"+2+","+1+","+cell);
                        } catch (IOException ex) {
                            Logger.getLogger(MultiplayerGameBoardPresenter.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }.start();*/

        } catch (IOException ex) {
            System.out.println("Cannot initialize presenter");
            Logger.getLogger(MultiplayerGameBoardPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String readMoveFromOpponent() {

        return "";
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

    public boolean isThatMyTurn() {
        return isMyTurn;
    }

    public boolean playMove(int cell) {//0-8
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //adjust IDs
                ps.println("Move," + 1 + "," + 2 + "," + cell);

            }
        });
        // ps.println("playMove"+0);
        return true;
    }
}
