/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

/**
 *
 * @author dell
 */
public interface OnlineUsersListControllerInterface extends Presenters {
    public void showAleart(String id1,String id2,String name1,String name2,String score1,String score2,String first);
    public void gotoGamme(String id1, String id2, String name1, String name2,String score1,String score2,String first);
    public void showrefuseAleart(String name);
    
}
