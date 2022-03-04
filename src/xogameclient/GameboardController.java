/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import static sun.audio.AudioPlayer.player;

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
    @FXML
    private ImageView backBtn;
    @FXML
    private Label xScore;
    @FXML
    private Label oScore;
    
    int scoreX , scoreO;
    String playerX, playerO;
    Stage stg;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        turn = false;
        backgroundImage.setImage(background);
        player1Card.setImage(imageX);
        player2Card.setImage(imageO);
        
        initializeScores(scoreX, scoreO);
        setUesers(playerX, playerO);
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
       
   public void check(){
       if(tieGame()){ //el moo4kla hena
           System.out.println("tieeeeeeeee");
           showAlertforTie();
        }else{
           // x win
            checkXWin();
            System.out.println("x from check method "+ scoreX);
            // o win
            checkOWin();
            System.out.println("o from check method "+ scoreO);

       }
       
        // no one win
        
       
   }
   
   public boolean checkXWin(){
       
       if(charForBoard[0][0]=='X' && charForBoard[0][1]=='X' && charForBoard[0][2]=='X'){
           
           scoreX++;
           System.out.println("X player score is "+scoreX);
           xWins(cell0,cell1,cell2);
       }
       if(charForBoard[1][0]=='X' && charForBoard[1][1]=='X' && charForBoard[1][2]=='X'){
           
           scoreX++;
           System.out.println("X player score is "+scoreX);
           xWins(cell3,cell4,cell5);
       }
       if(charForBoard[2][0]=='X' && charForBoard[2][1]=='X' && charForBoard[2][2]=='X'){
           
           scoreX++;
           System.out.println("X player score is "+scoreX);
           xWins(cell6,cell7,cell8);
       }
       if(charForBoard[0][0]=='X' && charForBoard[1][0]=='X' && charForBoard[2][0]=='X'){
           
           scoreX++;
           System.out.println("X player score is "+scoreX);
           xWins(cell0,cell3,cell6);
       }
       if(charForBoard[0][1]=='X' && charForBoard[1][1]=='X' && charForBoard[2][1]=='X'){
           scoreX++;
           System.out.println("X player score is "+scoreX);
           xWins(cell1,cell4,cell7);
       }
       if(charForBoard[0][2]=='X' && charForBoard[1][2]=='X' && charForBoard[2][2]=='X'){
           
           scoreX++;
           xWins(cell2,cell5,cell8);
       }
       if(charForBoard[0][0]=='X' && charForBoard[1][1]=='X' && charForBoard[2][2]=='X'){
           
           scoreX++;
           System.out.println("X player score is "+scoreX);
           xWins(cell0,cell4,cell8);
       }
       if(charForBoard[0][2]=='X' && charForBoard[1][1]=='X' && charForBoard[2][0]=='X'){
           
           scoreX++;
           System.out.println("X player score is "+scoreX);
           xWins(cell2,cell4,cell6);
       }
       return true;
   }
   
   public boolean checkOWin(){
       
       if(charForBoard[0][0]=='O' && charForBoard[0][1]=='O' && charForBoard[0][2]=='O'){
           scoreO++;
           System.out.println("O player score is "+scoreO);
           oWins(cell0,cell1,cell2);
       }
       if(charForBoard[1][0]=='O' && charForBoard[1][1]=='O' && charForBoard[1][2]=='O'){
           
           scoreO++;
           System.out.println("O player score is "+scoreO);
           oWins(cell3,cell4,cell5);
       }
       if(charForBoard[2][0]=='O' && charForBoard[2][1]=='O' && charForBoard[2][2]=='O'){
           
           scoreO++;
           System.out.println("O player score is "+scoreO);
           oWins(cell6,cell7,cell8);
       }
       if(charForBoard[0][0]=='O' && charForBoard[1][0]=='O' && charForBoard[2][0]=='O'){
           
           scoreO++;
           System.out.println("O player score is "+scoreO);
           oWins(cell0,cell3,cell6);
       }
       if(charForBoard[0][1]=='O' && charForBoard[1][1]=='O' && charForBoard[2][1]=='O'){
           
           scoreO++;
           oWins(cell1,cell4,cell7);
       }
       if(charForBoard[0][2]=='O' && charForBoard[1][2]=='O' && charForBoard[2][2]=='O'){
           scoreO++;
           System.out.println("O player score is "+scoreO);
           oWins(cell2,cell5,cell8);
           
       }
       if(charForBoard[0][0]=='O' && charForBoard[1][1]=='O' && charForBoard[2][2]=='O'){
           scoreO++;
           System.out.println("O player score is "+scoreO);
           oWins(cell0,cell4,cell8);
           
       }
       if(charForBoard[0][2]=='O' && charForBoard[1][1]=='O' && charForBoard[2][0]=='O'){
           scoreO++;
           System.out.println("O player score is "+scoreO);
           oWins(cell2,cell4,cell6);
           
       }
       return true;
   }
   
   public boolean tieGame(){
       boolean hasEmptyCell = false ;
       for (int i =0 ;i<3;i++){
           for(int j =0; j<3; j++){
               if(charForBoard[i][j] == ' '){
                   hasEmptyCell =true ;
                   break;
               }
           }
       }
       
       System.out.println("hereeeeeeeee is the problem "+ !((checkOWin() && checkXWin()) && hasEmptyCell));
       return !((checkOWin() && checkXWin()) && hasEmptyCell);
        //return !(checkXWin() || checkOWin());
   }
   
   public void showAlertforTie(){
        stg = (Stage) cell0.getScene().getWindow();
        
        Alert.AlertType type = Alert.AlertType.WARNING;
        Alert alert = new Alert(type);

        alert.initModality(Modality.WINDOW_MODAL);
        alert.initOwner(stg);
        alert.setTitle("Oopps");
        alert.getDialogPane().setContentText("No one Wins, Try play ageain later!");
        alert.setHeaderText("Tieee");
        alert.showAndWait();
   }
   
   public void xWins(Button a,Button b, Button c){
       a.setStyle("-fx-background-color: #ff0000;");
       b.setStyle("-fx-background-color: #ff0000;");
       c.setStyle("-fx-background-color: #ff0000;");
       buttonDisabel();
       gotToAlert(textx.getText());
       initializeScores(scoreX, scoreO);
      //alert(textx.getText());
      
   }
    public void oWins(Button a,Button b, Button c){
       a.setStyle("-fx-background-color: #ff0000;");
       b.setStyle("-fx-background-color: #ff0000;");
       c.setStyle("-fx-background-color: #ff0000;");
       buttonDisabel();
       gotToAlert(texto.getText());
       initializeScores(scoreX, scoreO);
       //showAlert(scoreO);
      //alert(texto.getText());
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
      
       public void setScores(int scoreXplayer, int scoreOplayer){
          if(checkXWin()){
              scoreXplayer = scoreX;
              xScore.setText(String.valueOf(scoreXplayer));
          }else if(checkOWin()){
              scoreOplayer = scoreO;
              oScore.setText(String.valueOf(scoreOplayer));
          }
      }
       
      private void gotToAlert(String player){
          FXMLLoader loader = new FXMLLoader(getClass().getResource("VideoAlert.fxml"));
          Parent root = null;
          try {
              root = loader.load();
              
          } catch (IOException ex) {
              //Logger.getLogger(GameboardController.class.getName()).log(Level.SEVERE, null, ex);
          }
          Scene scene = new Scene(root);
          
          VideoAlertController vc = loader.getController();
          vc.setWinnerVideo();
          vc.setWinnerName(player);
          System.out.println("score x before is "+ scoreX);
          System.out.println("score o before is "+ scoreO);
          vc.scoreO = scoreO;
          vc.scoreX = scoreX;
          vc.playerX = playerX;
          vc.playerO = playerO;
          System.out.println("score x is "+ scoreX);
          System.out.println("score o is "+ scoreO);
          System.out.println("buguyguguy"+scene);
          stg = (Stage) cell0.getScene().getWindow(); // exceptions
          System.out.println("helooooooo"+stg);
          stg.setScene(scene);
          stg.show();
      }
      
      public void initializeScores(int scoreX, int scoreO){
          xScore.setText(String.valueOf(scoreX));
          oScore.setText(String.valueOf(scoreO));
      }
      
      /*private void alert(String p1) {
            MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("viedo/p2.mp4").toExternalForm()));
            MediaView mediaView = new MediaView(player);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You Win");
            alert.setHeaderText("");

            Label label = new Label("Congatrions " +p1);
            label.setStyle("-fx-font: 20px \"sans-serif\";-fx-font-weight: bold;");
            VBox content = new VBox(10, label, mediaView);
            content.setAlignment(Pos.CENTER);
            alert.getDialogPane().setContent(content);
            player.setCycleCount(MediaPlayer.INDEFINITE);
            alert.setOnShowing(e -> player.play());
            alert.getDialogPane().setPrefSize(600, 800);
            alert.showAndWait();
    }*/

    @FXML
    private void didPressedBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent prevScreen = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(prevScreen);
        stage.setScene(scene);
        stage.show();
    }
}
