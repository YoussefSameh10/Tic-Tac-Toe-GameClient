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
    public boolean loginSuccess;

    public LoginResponse(boolean loginSuccess, int userId) {
        this.loginSuccess = loginSuccess;
        this.userId = userId;
    }
}
