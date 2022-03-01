/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import static sun.audio.AudioPlayer.player;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class AIGameboardController implements Initializable {
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
    private final Image imageX     = new Image(getClass().getResourceAsStream("homeAssets/X.jpg"));
    private final Image imageO     = new Image(getClass().getResourceAsStream("homeAssets/O.jpg"));
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
       }
        
    }
    @FXML
    private void cell1Pressed() {
        if(charForBoard[0][1] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[0][1] = res ;
          cellPressed(cell1);
       }
    }
    @FXML
    private void cell2Pressed() {
        if(charForBoard[0][2] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[0][2] = res ;
          cellPressed(cell2);

       }
    }
    @FXML
    private void cell3Pressed() {
        if(charForBoard[1][0] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[1][0] = res ;
          cellPressed(cell3);
       }
    }
    @FXML
    private void cell4Pressed() {
        if(charForBoard[1][1] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[1][1] = res ;
          cellPressed(cell4);
       }
    }
    @FXML
    private void cell5Pressed() {
        if(charForBoard[1][2] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[1][2] = res ;
          cellPressed(cell5);

       }
    }
    @FXML
    private void cell6Pressed() {
        if(charForBoard[2][0] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[2][0] = res ;
          cellPressed(cell6);

       }
    }
    @FXML
    private void cell7Pressed() {
        if(charForBoard[2][1] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[2][1] = res ;
          cellPressed(cell7);

       }
    }
    @FXML
    private void cell8Pressed() {
       if(charForBoard[2][2] == ' '){
          char res = (turn == true) ? 'X':'O';
          charForBoard[2][2] = res ;
          cellPressed(cell8);

       }
    }
    
    
    
    private void cellPressed(Button cell) {
        addXorO(cell, turn);
        if (check()) return ;
        turn = !turn;
        int[] move = NewMiniMax.getBestMove(charForBoard, 0);
        System.out.println("X= " + move[0] + ", Y= " + move[1]);
        if (turn) addAIMove(move[0], move[1]);
    }
    private void addAIMove(int row, int col)
    {
        int indx = row * 3 + col;
        switch(indx)
        {
            case 0:
                cell0Pressed();
                break;
            case 1:
                cell1Pressed();
                break;
            case 2:
                cell2Pressed();
                break;
            case 3:
                cell3Pressed();
                break;
            case 4:
                cell4Pressed();
                break;
            case 5:
                cell5Pressed();
                break;
            case 6:
                cell6Pressed();
                break;
            case 7:
                cell7Pressed();
                break;
            case 8:
                cell8Pressed();
                break;
        }
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
             Logger.getLogger(AIGameboardController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
       if(randam.nextInt(2)==0){
           playerTurn = true;
           textx.setText("X Turn");
           
       }else{
            playerTurn = false;
           texto.setText("O Turn");
       }
   } 
   
   public boolean check(){
        // x win
       if(charForBoard[0][0]=='X' && charForBoard[0][1]=='X' && charForBoard[0][2]=='X'){
           xWins(cell0,cell1,cell2);
           return true;
       }
       if(charForBoard[1][0]=='X' && charForBoard[1][1]=='X' && charForBoard[1][2]=='X'){
           xWins(cell3,cell4,cell5);
           return true;
       }
       if(charForBoard[2][0]=='X' && charForBoard[2][1]=='X' && charForBoard[2][2]=='X'){
           xWins(cell6,cell7,cell8);
           return true;
       }
       if(charForBoard[0][0]=='X' && charForBoard[1][0]=='X' && charForBoard[2][0]=='X'){
           xWins(cell0,cell3,cell6);
           return true;
       }
       if(charForBoard[0][1]=='X' && charForBoard[1][1]=='X' && charForBoard[2][1]=='X'){
           xWins(cell1,cell4,cell7);
           return true;
       }
       if(charForBoard[0][2]=='X' && charForBoard[1][2]=='X' && charForBoard[2][2]=='X'){
           xWins(cell2,cell5,cell8);
           return true;
       }
       if(charForBoard[0][0]=='X' && charForBoard[1][1]=='X' && charForBoard[2][2]=='X'){
           xWins(cell0,cell4,cell8);
           return true;
       }
       if(charForBoard[0][2]=='X' && charForBoard[1][1]=='X' && charForBoard[2][0]=='X'){
           xWins(cell2,cell4,cell6);
           return true;
       }
       // o win
       
       if(charForBoard[0][0]=='O' && charForBoard[0][1]=='O' && charForBoard[0][2]=='O'){
           oWins(cell0,cell1,cell2);
           return true;
       }
       if(charForBoard[1][0]=='O' && charForBoard[1][1]=='O' && charForBoard[1][2]=='O'){
           oWins(cell3,cell4,cell5);
           return true;
       }
       if(charForBoard[2][0]=='O' && charForBoard[2][1]=='O' && charForBoard[2][2]=='O'){
           oWins(cell6,cell7,cell8);
           return true;
       }
       if(charForBoard[0][0]=='O' && charForBoard[1][0]=='O' && charForBoard[2][0]=='O'){
           oWins(cell0,cell3,cell6);
           return true;
       }
       if(charForBoard[0][1]=='O' && charForBoard[1][1]=='O' && charForBoard[2][1]=='O'){
           oWins(cell1,cell4,cell7);
           return true;
       }
       if(charForBoard[0][2]=='O' && charForBoard[1][2]=='O' && charForBoard[2][2]=='O'){
           oWins(cell2,cell5,cell8);
           return true;
       }
       if(charForBoard[0][0]=='O' && charForBoard[1][1]=='O' && charForBoard[2][2]=='O'){
           oWins(cell0,cell4,cell8);
           return true;
       }
       if(charForBoard[0][2]=='O' && charForBoard[1][1]=='O' && charForBoard[2][0]=='O'){
           oWins(cell2,cell4,cell6);
           return true;
       }
       return false;
   }
   public void xWins(Button a,Button b, Button c){
       a.setStyle("-fx-background-color: #ff0000;");
       b.setStyle("-fx-background-color: #ff0000;");
       c.setStyle("-fx-background-color: #ff0000;");
       buttonDisabel();
       alert(textx.getText());
      
   }
    public void oWins(Button a,Button b, Button c){
       a.setStyle("-fx-background-color: #ff0000;");
       b.setStyle("-fx-background-color: #ff0000;");
       c.setStyle("-fx-background-color: #ff0000;");
       buttonDisabel();
       alert(texto.getText());
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
      public void setUesers(String p1,String p2){
          System.out.println("p1");
                  
          textx.setText(p1);
          texto.setText(p2);
      }
      
      private void alert(String p1) {
          MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("viedo/p2.mp4").toExternalForm()));
          MediaView mediaView = new MediaView(player);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("You Win");
        alert.setHeaderText("");

        Label label = new Label("Congatrions " +p1);
        VBox content = new VBox(10, label, mediaView);
        content.setAlignment(Pos.CENTER);
        alert.getDialogPane().setContent(content);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        alert.setOnShowing(e -> player.play());
        alert.getDialogPane().setPrefSize(600, 600);
        alert.showAndWait();
    }
}