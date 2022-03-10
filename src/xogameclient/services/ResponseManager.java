/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.services;

import xogameclient.services.responsemodels.ChallengeRequest;
import xogameclient.services.responsemodels.ChallengeResponse;
import xogameclient.services.responsemodels.GetOnlinePlayersListResponse;
import xogameclient.services.responsemodels.LoginResponse;
import xogameclient.services.responsemodels.Move;
import xogameclient.services.responsemodels.RegisterResponse;
import xogameclient.services.responsemodels.UnsupportedAction;

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
            System.out.println("Did enter login response");
            String result = parts[1];
            boolean loginSuccess;
            if(result.equals("Success")) {
                loginSuccess = true;
            }
            else {
                
                loginSuccess = false;
            }
            return new LoginResponse(loginSuccess);
        }
        if(AvailableActions.Move.getString().equals(parts[0])) {
            System.out.println("Did recieve move ");
            int playerOneId = Integer.parseInt(parts[1]) ;
            int playerTwoId = Integer.parseInt(parts[2]) ;
            int  cell = Integer.parseInt(parts[3]) ;
            return new Move(playerOneId , playerTwoId, cell);
        }
        
        if(AvailableActions.GetOnlinePlayersListResponse.getString().equals(parts[0])) {
            System.out.println("Did enter get online players response");
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
        
        return new UnsupportedAction("unsupported action error");
    }
}
