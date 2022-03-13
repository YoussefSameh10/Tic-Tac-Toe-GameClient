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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import xogameclient.services.NetworkConnection;

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

    int scoreX, scoreO, depth;

    Media media;
    @FXML
    private ImageView cancelBtn;

    String playerX, playerO;
    boolean isOnlineMode = false;
    
    int onlineId;
    String onlineUserName;
    int onlineScore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setWinnerVideo(boolean isReplayAllowed) {
        reloadImg.setVisible(isReplayAllowed);
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource("viedo/winner.mp4").toExternalForm()));
        mediaViewer.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    }

    public void setLoserVideo(boolean isReplayAllowed) {
        reloadImg.setVisible(isReplayAllowed);
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource("viedo/loser.mp4").toExternalForm()));
        mediaViewer.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    }

    public void setWinnerName(String pName) {
        winnerPlayer.setText(pName);
    }

    public void setScores(int scoreX, int scoreO) {
        this.scoreX = scoreX;
        this.scoreO = scoreO;
    }

    public void sendScoresBack(String scoreX, String scoreY) {

    }
    String fxmlName;

    public void setFXml(String fxmlName) {
        this.fxmlName = fxmlName;
    }

    @FXML
    private void didPressReload(MouseEvent event) throws IOException {
        if (fxmlName.equalsIgnoreCase("AIGameboard.fxml")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AIGameboard.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                XOGameClient.showAlertForError("Can't Load AI GameBoard Screen!!");
                //Logger.getLogger(VideoAlertController.class.getName()).log(Level.SEVERE, null, ex);
            }
            mediaPlayer.stop();
            Scene scene = new Scene(root);
            AIGameboardController vc = loader.getController();
            vc.scoreO = scoreO;
            vc.scoreX = scoreX;
            vc.setDepth(depth);
            vc.texto.setText(playerO);
            vc.initializeScores(scoreX, scoreO);
            vc.setUesers(playerO);
            Stage stage = (Stage) reloadImg.getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        }
        if (fxmlName.equalsIgnoreCase("gameboard.fxml")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gameboard.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                XOGameClient.showAlertForError("Can't Load GameBoard Screen!!");
                //Logger.getLogger(VideoAlertController.class.getName()).log(Level.SEVERE, null, ex);
            }
            mediaPlayer.stop();
            Scene scene = new Scene(root);
            GameboardController vc = loader.getController();
            vc.scoreO = scoreO;
            vc.scoreX = scoreX;
            vc.playerX = playerX;
            vc.playerO = playerO;
            vc.initializeScores(scoreX, scoreO);
            vc.setUesers(playerX, playerO);
            Stage stage = (Stage) reloadImg.getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void didPressCancel(MouseEvent event) throws IOException {
        if (isOnlineMode) {
            try {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("OnlineUsersList.fxml"));
                Parent root = Loader.load();
                Stage stage = (Stage) ((Node) cancelBtn).getScene().getWindow();
                Scene scene = new Scene(root);
                OnlineUsersListController vc = Loader.getController();
                vc.setCurrentUsername(onlineUserName);
                vc.setCurrentID(onlineId);
                vc.setCurrentScore(onlineScore);
                NetworkConnection.getInstance().setPresenter(vc);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("List Of Online Users");
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            Parent prevScreen = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            mediaPlayer.stop();
            Scene scene = new Scene(prevScreen);
            stage.setScene(scene);
            stage.show();
        }
    }

}
