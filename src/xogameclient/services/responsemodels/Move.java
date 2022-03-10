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
public class Move implements ClientActions {

   private int cellNumber;
    private int senderId;
    private int recieverId;

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(int recieverId) {
        this.recieverId = recieverId;
    }

    public Move(int cellNumber, int senderId, int recieverId) {
        this.cellNumber = cellNumber;
        this.senderId = senderId;
        this.recieverId = recieverId;
        System.out.println("cell id  = "+ this.cellNumber);
        System.out.println("sender = " + this.senderId);
         System.out.println("reciever = " + this.recieverId);


        
    }
}
