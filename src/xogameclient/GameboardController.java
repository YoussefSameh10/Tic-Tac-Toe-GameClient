/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class GameboardController implements Initializable {
     Random randam = new Random();
     boolean playerTurn; //O
     char [][] charForBoard = {{' ', ' ', ' '},{' ', ' ',' '},{' ', ' ',' '}};
    @FXML
    private ImageView backgroundImage;
    @FXML
    private Button cell0;
    @FXML
    private Button cell1;
    @FXML
    private Button cell2;
    @FXML
    private Button cell3;
    @FXML
    private Button cell4;
    @FXML
    private Button cell5;
    @FXML
    private Button cell6;
    @FXML
    private Button cell7;
    @FXML
    private Button cell8;
    
    private boolean turn;
    @FXML
    private ImageView player1Card;
    @FXML
    private ImageView player2Card;
    
    
    private final int iconSize = 75;
    private final Image background = new Image(getClass().getResourceAsStream("homeAssets/background.jpg"));
    private final Image imageX = new Image(getClass().getResourceAsStream("homeAssets/X.jpg"));
    private final Image imageO = new Image(getClass().getResourceAsStream("homeAssets/O.jpg"));
    @FXML
    private Label textx;
    @FXML
    private Label texto;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        turn = false;
        backgroundImage.setImage(background);
        player1Card.setImage(imageX);
        player2Card.setImage(imageO);
        
    }   
    
    @FXML
    private void cell0Pressed() {
       if(charForBoard[0][0] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[0][0] = res ;
          cellPressed(cell0);
          System.out.println(res);
          check();
       }
        
    }
    @FXML
    private void cell1Pressed() {
        if(charForBoard[0][1] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[0][1] = res ;
          cellPressed(cell1);
          check();
       }
       // cellPressed(cell1);
    }
    @FXML
    private void cell2Pressed() {
        if(charForBoard[0][2] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[0][2] = res ;
          cellPressed(cell2);
          check();

       }
       // cellPressed(cell2);
    }
    @FXML
    private void cell3Pressed() {
        if(charForBoard[1][0] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[1][0] = res ;
          cellPressed(cell3);
          check();
       }
       // cellPressed(cell3);
    }
    @FXML
    private void cell4Pressed() {
        if(charForBoard[1][1] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[1][1] = res ;
          cellPressed(cell4);
          check();
       }
        //cellPressed(cell4);
    }
    @FXML
    private void cell5Pressed() {
        if(charForBoard[1][2] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[1][2] = res ;
          cellPressed(cell5);
          check();

       }
        //cellPressed(cell5);
    }
    @FXML
    private void cell6Pressed() {
        if(charForBoard[2][0] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[2][0] = res ;
          cellPressed(cell6);
          check();

       }
       // cellPressed(cell6);
    }
    @FXML
    private void cell7Pressed() {
        if(charForBoard[2][1] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[2][1] = res ;
          cellPressed(cell7);
          check();

       }
        //cellPressed(cell7);
    }
    @FXML
    private void cell8Pressed() {
       // cellPressed(cell8);
       if(charForBoard[2][2] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[2][2] = res ;
          cellPressed(cell8);
          check();

       }
    }
    
    private void cellPressed(Button cell) {
        addXorO(cell, turn);
        turn = !turn;
        //if()
    }

    private void addXorO(Button cell, boolean turn) {
        
        if(turn) {
            ImageView imageviewX = new ImageView(imageX);
            resize(imageviewX);
            cell.setGraphic(imageviewX);
        }
        else {
            ImageView imageviewO = new ImageView(imageO);
            resize(imageviewO);
            cell.setGraphic(imageviewO);
        }
    }
    
    private void resize(ImageView imgView) {
        imgView.setFitWidth(iconSize);
        imgView.setFitHeight(iconSize);
    }
       public void firstTurn(){
        
         try {
             Thread.sleep(2000);
         } catch (InterruptedException ex) {
             Logger.getLogger(GameboardController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
       if(randam.nextInt(2)==0){
           playerTurn = true;
           textx.setText("X Turn");
           
       }else{
            playerTurn = false;
           texto.setText("O Turn");
       }
   } 
   
   public void check(){
        // x win
       if(charForBoard[0][0]=='X' && charForBoard[0][1]=='X' && charForBoard[0][2]=='X'){
           xWins(cell0,cell1,cell2);
       }
       if(charForBoard[1][0]=='X' && charForBoard[1][1]=='X' && charForBoard[1][2]=='X'){
           xWins(cell3,cell4,cell5);
       }
       if(charForBoard[2][0]=='X' && charForBoard[2][1]=='X' && charForBoard[2][2]=='X'){
           xWins(cell6,cell7,cell8);
       }
       if(charForBoard[0][0]=='X' && charForBoard[1][0]=='X' && charForBoard[2][0]=='X'){
           xWins(cell0,cell3,cell6);
       }
       if(charForBoard[0][1]=='X' && charForBoard[1][1]=='X' && charForBoard[2][1]=='X'){
           xWins(cell1,cell4,cell7);
       }
       if(charForBoard[0][2]=='X' && charForBoard[1][2]=='X' && charForBoard[2][2]=='X'){
           xWins(cell2,cell5,cell8);
       }
       if(charForBoard[0][0]=='X' && charForBoard[1][1]=='X' && charForBoard[2][2]=='X'){
           xWins(cell0,cell4,cell8);
       }
       if(charForBoard[0][2]=='X' && charForBoard[1][1]=='X' && charForBoard[2][0]=='X'){
           xWins(cell2,cell4,cell6);
       }
       // o win
       
       if(charForBoard[0][0]=='O' && charForBoard[0][1]=='O' && charForBoard[0][2]=='O'){
           oWins(cell0,cell1,cell2);
       }
       if(charForBoard[1][0]=='O' && charForBoard[1][1]=='O' && charForBoard[1][2]=='O'){
           oWins(cell3,cell4,cell5);
       }
       if(charForBoard[2][0]=='O' && charForBoard[2][1]=='O' && charForBoard[2][2]=='O'){
           oWins(cell6,cell7,cell8);
       }
       if(charForBoard[0][0]=='O' && charForBoard[1][0]=='O' && charForBoard[2][0]=='O'){
           oWins(cell0,cell3,cell6);
       }
       if(charForBoard[0][1]=='O' && charForBoard[1][1]=='O' && charForBoard[2][1]=='O'){
           oWins(cell1,cell4,cell7);
       }
       if(charForBoard[0][2]=='O' && charForBoard[1][2]=='O' && charForBoard[2][2]=='O'){
           oWins(cell2,cell5,cell8);
       }
       if(charForBoard[0][0]=='O' && charForBoard[1][1]=='O' && charForBoard[2][2]=='O'){
           oWins(cell0,cell4,cell8);
       }
       if(charForBoard[0][2]=='O' && charForBoard[1][1]=='O' && charForBoard[2][0]=='O'){
           oWins(cell2,cell4,cell6);
       }
   }
   public void xWins(Button a,Button b, Button c){
       a.setStyle("-fx-background-color: #ff0000;");
       b.setStyle("-fx-background-color: #ff0000;");
       c.setStyle("-fx-background-color: #ff0000;");
       buttonDisabel();
       textx.setText("x wins");
      
   }
    public void oWins(Button a,Button b, Button c){
       a.setStyle("-fx-background-color: #ff0000;");
       b.setStyle("-fx-background-color: #ff0000;");
       c.setStyle("-fx-background-color: #ff0000;");
       buttonDisabel();
       texto.setText("O wins");
   }
    public void buttonDisabel(){
        cell0.setDisable(true);
        cell1.setDisable(true);
        cell2.setDisable(true);
        cell3.setDisable(true);
        cell4.setDisable(true);
        cell5.setDisable(true);
        cell6.setDisable(true);
        cell7.setDisable(true);
        cell8.setDisable(true);
    }
        
}
