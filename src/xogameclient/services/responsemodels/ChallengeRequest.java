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
public class ChallengeRequest implements ClientActions {
    String id1,id2,name1,name2,score1,score2,first;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }
    public ChallengeRequest(String id1,String id2,String name1,String name2,String score1,String score2,String first){
        this.id1 = id1;
        this.id2 = id2;
        this.name1 = name1;
        this.name2= name2;
        this.score1 = score1;
        this.score2 = score2;
        this.first = first;
     
    }
    
}
