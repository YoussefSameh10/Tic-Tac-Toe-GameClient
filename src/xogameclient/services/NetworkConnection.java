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
import xogameclient.OnlineUsersListController;
import xogameclient.Presenters;
import xogameclient.RegisterPresenter;
import static xogameclient.services.AvailableActions.ChallengeRequest;
import static xogameclient.services.AvailableActions.ChallengeResponse;
import xogameclient.services.responsemodels.ChallengeRequest;
import xogameclient.services.responsemodels.ChallengeResponse;
import xogameclient.services.responsemodels.GetOnlinePlayersListResponse;
import xogameclient.XOGameClient;
import xogameclient.MultiplayerGameBoardPresenter;
import xogameclient.Presenters;
import xogameclient.RegisterPresenter;
import xogameclient.services.responsemodels.BoardStatus;
import xogameclient.services.responsemodels.GameStatusResponse;
import xogameclient.services.responsemodels.LoginResponse;
import xogameclient.services.responsemodels.Move;
import xogameclient.services.responsemodels.RegisterResponse;
import xogameclient.services.responsemodels.ServerClose;

/**
 *
 * @author Youssef
 */
public class NetworkConnection {

    private static NetworkConnection instance;
    private Socket server;
    private DataInputStream dis;
    private PrintStream ps;
    //private Thread t1;
    private String response;
    ResponseManager responseManager;
    private Presenters presenter;
    private XOGameClient presenter2;
    ClientActions action;

    public void setPresenter(Presenters presenter) {
        this.presenter = presenter;
    }
    
    public void setPresenter2(XOGameClient presenter2){
        this.presenter2 = presenter2;
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

    public String getResponse(){
        return response;
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
                    }try {
                        server.close();
                        dis.close();
                        ps.close();
                        stop();
                    } catch (IOException ex1) {
                        Logger.getLogger(NetworkConnection.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                } catch (IOException ex) {
                    try {
                        server.close();
                        dis.close();
                        ps.close();
                        stop();
                        Logger.getLogger(NetworkConnection.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex1) {
                        Logger.getLogger(NetworkConnection.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            }
        }.start();
    }

    private void manage() {

        action = responseManager.parse(response);
        if (action instanceof LoginResponse) {
            manageLogin(action);
        } else if (action instanceof RegisterResponse) {
            manageRegister(action);
        }
        else if(action instanceof GetOnlinePlayersListResponse) {
            manageGettingOnlinePlayersList(action);
        }
        else if(action instanceof ServerClose) {
            manageServerClose(action);
        }
         else if (action instanceof Move) {

            manageMove(action);
        } else if (action instanceof GameStatusResponse) {
            manageGameResponse(action);
        } else if(action instanceof ChallengeRequest) {
        
            manageChallengeRequest( action);
        }else if(action instanceof ChallengeResponse) {
        
            manageChallengeRsponse( action);
        }
    }

    private void manageLogin(ClientActions action) {
        if (((LoginResponse) action).loginSuccess == true) {
            Platform.runLater(() -> {
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
    
    private void manageGettingOnlinePlayersList(ClientActions action) {
        if (((GetOnlinePlayersListResponse) action).isSuccess == true) {
            ((OnlineUsersListController) presenter).performSuccessAction();
        } else {
           //((OnlineUsersListController) presenter).performFailureAction();
        }
    }
    

    private void manageServerClose(ClientActions action) {
        if (((ServerClose) action).isClose == true) {
            
            (presenter2).performSuccessAction();
        } else {
            (presenter2).performFailureAction();
        }
    }

    private void manageMove(ClientActions action) {
       
       MultiplayerGameBoardPresenter pres = (MultiplayerGameBoardPresenter)  presenter ;
           Platform.runLater(() -> {
                   pres.readMoveFromOpponent( ((Move) action).getCellNumber());
            });
   
    }
    
    private void manageGameResponse(ClientActions action){
        
       
         
       MultiplayerGameBoardPresenter pres = (MultiplayerGameBoardPresenter)  presenter ;
           Platform.runLater(() -> {
               pres.manageGameResult(((GameStatusResponse) action).getStatus(), ((GameStatusResponse) action).getPosition());
           });
    }
    
    

     private void manageChallengeRequest(ClientActions action) {
         String id1 = ((ChallengeRequest)action).getId1();
         String id2 = ((ChallengeRequest)action).getId2();
         String name1 = ((ChallengeRequest)action).getName1();
         String name2 = ((ChallengeRequest)action).getName2();
         String score1 = ((ChallengeRequest)action).getScore1();
         String score2 = ((ChallengeRequest)action).getScore2();
         String first  = ((ChallengeRequest)action).getFirst();
         ((OnlineUsersListController) presenter).showAleart( id1,  id2,  name1,  name2,score1,score2,first);
        //edit
    }
     
      private void manageChallengeRsponse(ClientActions action) {
         String d = ((ChallengeResponse)action).getRespons();
         String name2 = ((ChallengeResponse)action).getName2();

         if(d.equals("accept")){
              String id1 = ((ChallengeResponse)action).getId1();
         String id2 = ((ChallengeResponse)action).getId2();
         String name1 = ((ChallengeResponse)action).getName1();
         String score1 = ((ChallengeResponse)action).getScore1();
         String score2 = ((ChallengeResponse)action).getScore2();
         String first =  ((ChallengeResponse)action).getFirst();
         ((OnlineUsersListController) presenter). gotoGamme(id1,  id2,  name1,  name2,score1,score2,first);}
         else{
              ((OnlineUsersListController) presenter).showrefuseAleart(name2);
              
         }
        
    }
}
