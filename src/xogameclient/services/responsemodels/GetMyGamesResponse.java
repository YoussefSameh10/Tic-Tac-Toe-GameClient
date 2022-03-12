/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.services.responsemodels;

import java.util.ArrayList;
import xogameclient.services.ClientActions;

/**
 *
 * @author Youssef
 */
public class GetMyGamesResponse implements ClientActions{
    public String[] records;

    public GetMyGamesResponse(String[] records) {
        this.records = records;
    }
}
