/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

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
    String playerOName = "", localPlayerOName = "";
    String playerXName = "", localPlayerXName = "";
    
    String loserMedia;
    String winnerMedia;
    String winnerPlayers;
    
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

    @FXML
    private void didPressReload(MouseEvent event) {
    }
    
}
