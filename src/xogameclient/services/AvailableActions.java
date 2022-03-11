/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.services;


/**
 *
 * @author Youssef
 */

public enum AvailableActions {
    RegisterResponse("RegisterResponse"), //DONE
    LoginResponse("LoginResponse"), //DONE
    GetOnlinePlayersListResponse("GetOnlinePlayersListResponse"),
    ChallengeRequest("ChallengeRequest"),
    ChallengeResponse("ChallengeResponse"),
    Move("Move"),
    GameResult("GameResult"),
    ServerClose("ServerClose");
    
    private String action;
 
    AvailableActions(String action) {
        this.action = action;
    }
 
    public String getString() {
        return action;
    }
}
