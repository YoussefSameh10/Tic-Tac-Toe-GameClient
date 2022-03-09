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
import xogameclient.services.responsemodels.BoardStatus;

/**
 *
 * @author amin
 */
interface MultiPlayerGameControllerInterface {

    void playOpponentMoveAt(int cell);

    void setViewButtonsDisabled(boolean isDisabled);
}

public class MultiplayerGameBoardPresenter implements Presenters {

    MultiPlayerGameControllerInterface multiPlayerGameController;
    private String playerOneName, playerTwoName;
    private int playerOneScore, playerTwoScore;
    private int playerOneId, playerTwoId;
    private boolean isMyTurn = true;
    private boolean isUiDisabled;

    private NetworkConnection networkConnection;
    private Socket server;
    private DataInputStream dis;
    private PrintStream ps;
    private ResponseManager responseManager;

    public MultiplayerGameBoardPresenter(String playerOneName, String playerTwoName, int playerOneId, int playerTwoId, int playerOneScore, int playerTwoScore, boolean isUIdiabled) {
        try {
            this.playerOneName = playerOneName;
            this.playerTwoName = playerTwoName;
            this.playerOneScore = playerOneScore;
            this.playerTwoScore = playerTwoScore;
            this.playerOneId = playerOneId;
            this.playerTwoId = playerTwoId;
            this.isUiDisabled = isUIdiabled;

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
        isMyTurn = !isMyTurn;
        isUiDisabled = !isUiDisabled;
        multiPlayerGameController.setViewButtonsDisabled(isUiDisabled);
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
        ps.println("Move," + playerOneId + "," + playerTwoId + "," + cell);
        isUiDisabled = !isUiDisabled;
        multiPlayerGameController.setViewButtonsDisabled(isUiDisabled);
        isMyTurn = !isMyTurn;
        return true;
    }

    public void manageGameResult(String status, BoardStatus position) {
        switch (status) {
            case "Win":
                System.out.println("Controller will show celebration at" + position );
                break;
            case "Lose":
                System.out.println("Controller will show saddness at" + position );
                break;
            default:
                System.out.println("Controller will show n2deha mofawdat ya msel7y" + position);
                break;
        }
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
