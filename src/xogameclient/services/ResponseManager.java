/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.services;

import xogameclient.services.responsemodels.LoginResponse;
import xogameclient.services.responsemodels.Move;
import xogameclient.services.responsemodels.RegisterResponse;
import xogameclient.services.responsemodels.ServerClose;
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
        System.out.println("FROM CLOSE PARSE"+msg);
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
            int userId = Integer.parseInt(parts[2]);
            String userName = parts[3];
            int score = Integer.parseInt(parts[4]);
            boolean loginSuccess;
            if(result.equals("Success")) {
                loginSuccess = true;
            }
            else {
                
                loginSuccess = false;
            }
            return new LoginResponse(userId , userName , score , loginSuccess);
        }
        if(AvailableActions.Move.getString().equals(parts[0])) {
            System.out.println("Did recieve move ");
            int playerOneId = Integer.parseInt(parts[1]) ;
            int playerTwoId = Integer.parseInt(parts[2]) ;
            int  cell = Integer.parseInt(parts[3]) ;
            return new Move(playerOneId , playerTwoId, cell);
        }
        if(AvailableActions.ServerClose.getString().equals(parts[0])) {
            System.out.println("Did close server");
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
        
        return new UnsupportedAction("unsupported action error");
    }
}
