/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

/**
 *
 * @author sandra
 */
public interface LoginControllerInterface {
    public void gotoListOfOnlineUsers(String username, int id, int score);
    public void showLoginErrorAlert();
}
