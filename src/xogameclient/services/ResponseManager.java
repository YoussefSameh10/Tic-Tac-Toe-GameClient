/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.services;

import java.util.ArrayList;
import xogameclient.services.responsemodels.ChallengeRequest;
import xogameclient.services.responsemodels.ChallengeResponse;
import xogameclient.services.responsemodels.GetOnlinePlayersListResponse;
import xogameclient.services.responsemodels.LoginResponse;
import xogameclient.services.responsemodels.Move;
import xogameclient.services.responsemodels.RegisterResponse;
import xogameclient.services.responsemodels.ServerClose;
import xogameclient.services.responsemodels.UnsupportedAction;
import xogameclient.services.responsemodels.BoardStatus;
import xogameclient.services.responsemodels.GameResult;
import xogameclient.services.responsemodels.GameStatusResponse;
import xogameclient.services.responsemodels.GetMyGamesResponse;

/**
 *
 * @author Youssef
 */
public class ResponseManager {
    private static ResponseManager instance;
    
    private ResponseManager() {
        
    }
    
    public static ResponseManager getInstance() {
        if(instance == null){
           instance =  new ResponseManager(); 
        }
        return  instance;
    }
    
    public ClientActions parse(String msg) { 
        String[] parts;
        parts = msg.split(",");
        
        if(AvailableActions.RegisterResponse.getString().equals(parts[0])) {
            String result = parts[1];
            boolean isSuccess;
            if(result.equals("Success")) {
                isSuccess = true;
            }
            else {
                isSuccess = false;
            }
            return new RegisterResponse(isSuccess);
        }
        if(AvailableActions.LoginResponse.getString().equals(parts[0])) {
            String result = parts[1];
            
            boolean loginSuccess =false;
            if(result.equals("Success")) {
                int userId = Integer.parseInt(parts[2]);
                String userName = parts[3];
                int score = Integer.parseInt(parts[4]);
                loginSuccess = true;
                return new LoginResponse(userId , userName , score , loginSuccess);
            }
            else {
                loginSuccess = false;
                return new LoginResponse(-1 , "" , -1 , loginSuccess);
            }
            //System.out.println("Login Response is : "+ userId+userName+score+loginSuccess);
            
        }
        if(AvailableActions.Move.getString().equals(parts[0])) {
            int playerOneId = Integer.parseInt(parts[1]) ;
            int playerTwoId = Integer.parseInt(parts[2]) ;
            int  cell = Integer.parseInt(parts[3]) ;
            return new Move(cell , playerTwoId, playerOneId);
        }
        
            if(AvailableActions.GameResult.getString().equals(parts[0])) {
            String status = parts[1] ;
            BoardStatus position = BoardStatus.valueOf(parts[2]) ;
            return new GameStatusResponse(position, status);
        }
        if(AvailableActions.ServerClose.getString().equals(parts[0])) {
            String result = parts[1];
            boolean isClose;
            if(result.equals("Success")) {
                isClose = true;
            }
            else {
                isClose = false;
            }
            return new ServerClose(isClose);
        }
        
        if(AvailableActions.GetOnlinePlayersListResponse.getString().equals(parts[0])) {
            String result = parts[1];
            boolean gotOnlinePlayersList = false;
            if(!result.equals("")) {
                gotOnlinePlayersList = true;
            }
            return new GetOnlinePlayersListResponse(gotOnlinePlayersList);
        }
        
        if(AvailableActions.ChallengeRequest.getString().equals(parts[0])) {
            String id1 = parts[1];
            String id2 = parts[2];
            String name1 = parts[3];
            String name2 = parts[4];
            String score1 = parts[5];
            String score2 = parts[6];
            String first = parts[7];
                                System.out.println("@Parsing" +  name1 +"score" + score1 + "&& "+name2 + " score " + score2);

            return new ChallengeRequest(id1,id2,name1,name2,score1,score2,first);
        }
        
         if(AvailableActions.ChallengeResponse.getString().equals(parts[0])) {
             String respons = parts[1];
            String id1 = parts[2];
            String id2 = parts[3];
            String name1 = parts[4];
            String name2 = parts[5];
            String score1 = parts[6];
            String score2 = parts[7];
            String first = parts[8];
            return new ChallengeResponse(respons,id1,id2,name1,name2,score1,score2,first);
        }
         
        if(AvailableActions.GetMyGamesResponse.getString().equals(parts[0])) {
            String response = parts[1];
            String[] records = response.split("$");
            return new GetMyGamesResponse(records);
        }
        
        return new UnsupportedAction("unsupported action error");
    }
}
