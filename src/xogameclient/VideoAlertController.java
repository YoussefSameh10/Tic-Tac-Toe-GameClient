/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sandra
 */
public class VideoAlertController implements Initializable {

    @FXML
    private ImageView reloadImg;
    @FXML
    private Label congrats;
    @FXML
    private Label winnerPlayer;
    @FXML
    private MediaView mediaViewer;
    
    MediaPlayer mediaPlayer;
   
    int scoreX, scoreO;
    
    Media media;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    
    public void setWinnerVideo(){
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource("viedo/winner.mp4").toExternalForm()));
        mediaViewer.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    }
    public void setWinnerName(String pName){
        winnerPlayer.setText(pName);
    }
    
    public void setScores(int scoreX,int scoreO){
        this.scoreX = scoreX;
        this.scoreO = scoreO;
    }
    public void sendScoresBack(String scoreX, String scoreY){
        
    }

    @FXML
    private void didPressReload(MouseEvent event) throws IOException {
        System.out.println(scoreX);
        System.out.println(scoreO);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameboard.fxml"));
          Parent root = null;
          try {
              root = loader.load();
          } catch (IOException ex) {
              Logger.getLogger(VideoAlertController.class.getName()).log(Level.SEVERE, null, ex);
          }
        mediaPlayer.stop();
        Scene scene = new Scene(root);
        GameboardController vc = loader.getController();
        System.out.println("score x" + scoreX);
        System.out.println("score o" + scoreO);
        //vc.setScores(scoreX, scoreO);
        vc.scoreO = scoreO;
        vc.scoreX = scoreX;
        vc.initializeScores(scoreX, scoreO);
        Stage windo =(Stage)reloadImg.getScene().getWindow();
        windo.setScene(scene);
        windo.show();
    }
    
}
