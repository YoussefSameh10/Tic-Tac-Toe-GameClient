/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
public class AIGameboardController implements Initializable {
     Random randam = new Random();
     boolean playerTurn; //O
     char [][] charForBoard = {{' ', ' ', ' '},{' ', ' ',' '},{' ', ' ',' '}};
     boolean flag = false;
     int scoreX = 0, scoreO = 0;
    String playerX, playerO;
    int depth;
    Stage stg;
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
    String s;

    private final int iconSize = 75;
    private final Image background = new Image(getClass().getResourceAsStream("homeAssets/background.jpg"));
    private final Image imageX = new Image(getClass().getResourceAsStream("homeAssets/X.png"));
    private final Image imageO = new Image(getClass().getResourceAsStream("homeAssets/O.png"));
    @FXML
    private Label textx;
    @FXML
    Label texto;
    @FXML
    private ImageView backBtn;
    @FXML
    private Label xScore;
    @FXML
    private Label oScore;

    String gameRecord = "";
    boolean permitRecord;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        turn = false;
        backgroundImage.setImage(background);
        player1Card.setImage(imageX);
        player2Card.setImage(imageO);
       // xScore.setText(Integer.toString(scoreX));
        initializeScores(scoreX, scoreO);
    }
    
    public void setDepth(int depth){
        this.depth = depth;
    }
    
     public void initializeScores(int scoreX, int scoreO) {
        xScore.setText(String.valueOf(scoreX));
        oScore.setText(String.valueOf(scoreO));
    }


    @FXML
    private void cell0Pressed() {
        if (charForBoard[0][0] == ' ') {
            char res = (turn == true) ? 'X' : 'O';
            charForBoard[0][0] = res;
            gameRecord += res + "0";
            cellPressed(cell0);
        }
    }

    @FXML
    private void cell1Pressed() {
        if (charForBoard[0][1] == ' ') {
            char res = (turn == true) ? 'X' : 'O';
            charForBoard[0][1] = res;
            gameRecord += res + "1";
            cellPressed(cell1);
        }
    }

    @FXML
    private void cell2Pressed() {
        if (charForBoard[0][2] == ' ') {
            char res = (turn == true) ? 'X' : 'O';
            charForBoard[0][2] = res;
            gameRecord += res + "2";
            cellPressed(cell2);

        }
    }

    @FXML
    private void cell3Pressed() {
        if (charForBoard[1][0] == ' ') {
            char res = (turn == true) ? 'X' : 'O';
            charForBoard[1][0] = res;
            gameRecord += res + "3";
            cellPressed(cell3);
        }
    }

    @FXML
    private void cell4Pressed() {
        if (charForBoard[1][1] == ' ') {
            char res = (turn == true) ? 'X' : 'O';
            charForBoard[1][1] = res;
            gameRecord += res + "4";
            cellPressed(cell4);
        }
    }

    @FXML
    private void cell5Pressed() {
        if (charForBoard[1][2] == ' ') {
            char res = (turn == true) ? 'X' : 'O';
            charForBoard[1][2] = res;
            gameRecord += res + "5";
            cellPressed(cell5);

        }
    }

    @FXML
    private void cell6Pressed() {
        if (charForBoard[2][0] == ' ') {
            char res = (turn == true) ? 'X' : 'O';
            charForBoard[2][0] = res;
            gameRecord += res + "6";
            cellPressed(cell6);

        }
    }

    @FXML
    private void cell7Pressed() {
        if (charForBoard[2][1] == ' ') {
            char res = (turn == true) ? 'X' : 'O';
            charForBoard[2][1] = res;
            gameRecord += res + "7";
            cellPressed(cell7);

        }
    }

    @FXML
    private void cell8Pressed() {
        if (charForBoard[2][2] == ' ') {
            char res = (turn == true) ? 'X' : 'O';
            charForBoard[2][2] = res;
            gameRecord += res + "8";
            cellPressed(cell8);

        }
    }

    private void cellPressed(Button cell) {
        addXorO(cell, turn);
        if (check()) {
            return;
        }
        turn = !turn;
        int[] move = NewMiniMax.getBestMove(charForBoard, depth);
        if (turn) {
            addAIMove(move[0], move[1]);
        }
    }

    private void addAIMove(int row, int col) {
        int indx = row * 3 + col;
        switch (indx) {
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

        if (turn) {
            ImageView imageviewX = new ImageView(imageX);
            resize(imageviewX);
            cell.setGraphic(imageviewX);
        } else {
            ImageView imageviewO = new ImageView(imageO);
            resize(imageviewO);
            cell.setGraphic(imageviewO);
        }
    }

    private void resize(ImageView imgView) {
        imgView.setFitWidth(iconSize);
        imgView.setFitHeight(iconSize);
    }

    boolean hasEmptyCell;

    public boolean tieGame() {
        boolean hasEmptyCell = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (charForBoard[i][j] == ' ') {
                    hasEmptyCell = true;
                    break;
                }
            }
        }
        return hasEmptyCell;
    }

    public boolean check() {
        // x win
       if(charForBoard[0][0]=='X' && charForBoard[0][1]=='X' && charForBoard[0][2]=='X'  ){
         
           xWins(cell0,cell1,cell2);
           return true;
       }
       else if(charForBoard[1][0]=='X' && charForBoard[1][1]=='X' && charForBoard[1][2]=='X'){
           hasEmptyCell =  tieGame();
          if(hasEmptyCell){
           xWins(cell3,cell4,cell5);
           return true;}
       }
       else if(charForBoard[2][0]=='X' && charForBoard[2][1]=='X' && charForBoard[2][2]=='X'){
           hasEmptyCell =  tieGame();
          if(hasEmptyCell){
           xWins(cell6,cell7,cell8);
           return true;}
       }
       else  if(charForBoard[0][0]=='X' && charForBoard[1][0]=='X' && charForBoard[2][0]=='X' ){
           xWins(cell0,cell3,cell6);
           return true;
       }
       else if(charForBoard[0][1]=='X' && charForBoard[1][1]=='X' && charForBoard[2][1]=='X'){
           
           xWins(cell1,cell4,cell7);
           return true;
       }
       else  if(charForBoard[0][2]=='X' && charForBoard[1][2]=='X' && charForBoard[2][2]=='X'){
           
           xWins(cell2,cell5,cell8);
           return true;
       }
       else if(charForBoard[0][0]=='X' && charForBoard[1][1]=='X' && charForBoard[2][2]=='X'){
           
           xWins(cell0,cell4,cell8);
           return true;
       }
       else if(charForBoard[0][2]=='X' && charForBoard[1][1]=='X' && charForBoard[2][0]=='X'){
          
           xWins(cell2,cell4,cell6);
           return true;
       }
       // o win
       
       else if(charForBoard[0][0]=='O' && charForBoard[0][1]=='O' && charForBoard[0][2]=='O' ){
          
           oWins(cell0,cell1,cell2);
           return true;
       }
       else if(charForBoard[1][0]=='O' && charForBoard[1][1]=='O' && charForBoard[1][2]=='O'){
       
           oWins(cell3,cell4,cell5);
           return true;
       }
       else if(charForBoard[2][0]=='O' && charForBoard[2][1]=='O' && charForBoard[2][2]=='O' ){
         
           oWins(cell6,cell7,cell8);
           return true;
       }
       else if(charForBoard[0][0]=='O' && charForBoard[1][0]=='O' && charForBoard[2][0]=='O'){
          
           oWins(cell0,cell3,cell6);
           return true;
       }
       else if(charForBoard[0][1]=='O' && charForBoard[1][1]=='O' && charForBoard[2][1]=='O'){
           oWins(cell1,cell4,cell7);
           return true;
       }
       else if(charForBoard[0][2]=='O' && charForBoard[1][2]=='O' && charForBoard[2][2]=='O' ){
          
           oWins(cell2,cell5,cell8);
           return true;
       }
       else if(charForBoard[0][0]=='O' && charForBoard[1][1]=='O' && charForBoard[2][2]=='O'){
          oWins(cell0,cell4,cell8);
           return true;
       }
       else if(charForBoard[0][2]=='O' && charForBoard[1][1]=='O' && charForBoard[2][0]=='O' ){
          
           oWins(cell2,cell4,cell6);
           return true;
       }else if(!tieGame() && !flag){
           showAlertforTie();
       }
       else{
          
           
       }
       return false;
    }
   public void xWins(Button a,Button b, Button c){
       a.setStyle("-fx-background-color: #ff0000;");
       b.setStyle("-fx-background-color: #ff0000;");
       c.setStyle("-fx-background-color: #ff0000;");
       buttonDisabel();
       scoreX++;
       flag = true;
       alertx(textx.getText());
       initializeScores(scoreX, scoreO);
      
    }
    public void oWins(Button a,Button b, Button c){
       a.setStyle("-fx-background-color: #ff0000;");
       b.setStyle("-fx-background-color: #ff0000;");
       c.setStyle("-fx-background-color: #ff0000;");
       buttonDisabel();
       scoreO++;
       flag = true;
       alert(texto.getText());
       initializeScores(scoreX, scoreO);
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
    public void setUesers(String p1){
       texto.setText(p1);
    }
    private void alertx(String p1) {
        writeGameRecordToFile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VideoAlert.fxml"));
        Parent root = null;
        try {
           root = loader.load();
              
        } catch (IOException ex) {
            XOGameClient.showAlertForError("Can't load the alert controller");
        }
        Scene scene = new Scene(root);
        VideoAlertController vc = loader.getController();
        vc.fxmlName = "AIGameboard.fxml";
        vc.setLoserVideo(true);
        vc.setWinnerName(p1);
        vc.scoreO = scoreO;
        vc.scoreX = scoreX;
        vc.setDepth(depth);
        vc.playerO = texto.getText();
        stg = (Stage) cell0.getScene().getWindow(); // exceptions
        stg.setScene(scene);
        stg.show();
    }
        
    private void alert(String p1) {
        writeGameRecordToFile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VideoAlert.fxml"));
        Parent root = null;
        try {
           root = loader.load();
              
        } catch (IOException ex) {
            XOGameClient.showAlertForError("Can't load the alert controller");
        }
        Scene scene = new Scene(root);
        VideoAlertController vc = loader.getController();
        vc.setWinnerVideo(true);
        vc.fxmlName = "AIGameboard.fxml";
        vc.setWinnerName(p1);
        vc.scoreO = scoreO;
        vc.scoreX = scoreX;
        vc.playerO = texto.getText();
        stg = (Stage) cell0.getScene().getWindow(); // exceptions
        stg.setScene(scene);
        stg.show();

    }

    public void showAlertforTie(){
        writeGameRecordToFile();
        stg = (Stage) cell0.getScene().getWindow();
        Alert.AlertType type = Alert.AlertType.WARNING;
        Alert alert = new Alert(type);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.initOwner(stg);
        alert.setTitle("Oopps");
        alert.getDialogPane().setContentText("No one Wins, Try play ageain later!");
        alert.setHeaderText("Tieee");
        alert.showAndWait();
        enabelCells();
        emptyArray();
        emptyCells();
    }
       
   
    
    public void enabelCells(){
        turn = !turn;
        cell0.setDisable(false);
        cell1.setDisable(false);
        cell2.setDisable(false);
        cell3.setDisable(false);
        cell4.setDisable(false);
        cell5.setDisable(false);
        cell6.setDisable(false);
        cell7.setDisable(false);
        cell8.setDisable(false);
    }
    public void emptyArray(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
             charForBoard[i][j] = ' ';
            }
        }
    }
    
    public void emptyCells(){
       cell0.setGraphic(null);
       cell1.setGraphic(null);
       cell2.setGraphic(null);
       cell3.setGraphic(null);
       cell4.setGraphic(null);
       cell5.setGraphic(null);
       cell6.setGraphic(null);
       cell7.setGraphic(null);
       cell8.setGraphic(null);
    }

    @FXML
    private void didPressedBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent prevScreen = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(prevScreen);
        stage.setScene(scene);
        stage.show();
    }

    private void writeGameRecordToFile() {
        if (permitRecord) {
            File records = new File("Records.txt");
            try {
                FileWriter writer = new FileWriter("Records.txt", true);
                writer.append(gameRecord + "," + "AI" + " vs " + texto.getText() + "\n");
                writer.close();
            } catch (IOException ex) {
                XOGameClient.showAlertForError("Can't record the game");
                //Logger.getLogger(GameboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
