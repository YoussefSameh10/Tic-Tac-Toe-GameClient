/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.services.responsemodels;

import xogameclient.services.ClientActions;

/**
 *
 * @author Youssef
 */
public class LoginResponse implements ClientActions {
    public int userId;
    public String userName;
    public int score;
    public boolean loginSuccess;

    public LoginResponse(int userId, String userName, int score, boolean loginSuccess) {
        this.userId = userId;
        this.userName = userName;
        this.score = score;
        this.loginSuccess = loginSuccess;
    }
}
