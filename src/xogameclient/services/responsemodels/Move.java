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

    int cellNumber;
    int senderId;
    int recieverId;

    public Move(int cellNumber, int senderId, int recieverId) {
        this.cellNumber = cellNumber;
        this.senderId = senderId;
        this.recieverId = recieverId;
    }
}
