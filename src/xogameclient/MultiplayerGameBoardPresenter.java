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
interface MultiPlayerGameControllerInterface {
    void playOpponentMoveAt(int cell);
}

public class MultiplayerGameBoardPresenter implements Presenters {

     MultiPlayerGameControllerInterface multiPlayerGameController;
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

        } catch (IOException ex) {
            System.out.println("Cannot initialize presenter");
            Logger.getLogger(MultiplayerGameBoardPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void readMoveFromOpponent(int cell) {

        multiPlayerGameController.playOpponentMoveAt(cell);
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

    public void setPresenter() {
        System.out.println("PRESENTER SETTED TO MULTI");
        networkConnection.setPresenter(this);
    }

    public boolean playMove(int cell) {//0-8
        ps.println("Move," + 1 + "," + 2 + "," + cell);
        isMyTurn = !isMyTurn;
        return true;
    }

    @Override
    public void performSuccessAction() {
        System.out.println("YA LEEEEEEEEEEEEEEEEEEL");
    }

    @Override
    public void performFailureAction() {
        System.out.println("YA LAHWYYYYYY");
    }
}
