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
public class UnsupportedAction implements ClientActions {
    String error;

    public UnsupportedAction(String error) {
        this.error = error;
    }
}
