/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.services.responsemodels;

import java.util.logging.Logger;
import xogameclient.services.ClientActions;

/**
 *
 * @author amin
 */


public class GameStatusResponse   implements ClientActions{

    private BoardStatus Position;
    private String status;

    public GameStatusResponse(BoardStatus Position, String status) {
        this.Position = Position;
        this.status = status;
    }

    public BoardStatus getPosition() {
        return Position;
    }

    public String getStatus() {
        return status;
        
    }

}
