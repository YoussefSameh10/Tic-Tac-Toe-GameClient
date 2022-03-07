/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class MultiplayerGameBoardController implements Initializable {

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
    @FXML
    private ImageView player1Card;
    @FXML
    private ImageView player2Card;
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

    MultiplayerGameBoardPresenter presenter ;
    
    private final int iconSize = 75;
    private final Image background = new Image(getClass().getResourceAsStream("homeAssets/background.jpg"));
    private final Image imageX = new Image(getClass().getResourceAsStream("homeAssets/X.jpg"));
    private final Image imageO = new Image(getClass().getResourceAsStream("homeAssets/O.jpg"));
    Stage stg;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        backgroundImage.setImage(background);
        player1Card.setImage(imageX);
        player2Card.setImage(imageO);
        setButtonsEnabled(false);

    }

    @FXML
    private void cell0Pressed() {
        System.out.println("Pressed 0");
        presenter.playMove(0);
        addXorO(cell0);

    }

    @FXML
    private void cell1Pressed() {
        System.out.println("Pressed 1");
                presenter.playMove(1);
                        addXorO(cell1);


    }

    @FXML
    private void cell2Pressed() {
        System.out.println("Pressed 2");
                presenter.playMove(2);
                                        addXorO(cell2);


    }

    @FXML
    private void cell3Pressed() {
        System.out.println("Pressed 3");
                presenter.playMove(3);
                                        addXorO(cell3);


    }

    @FXML
    private void cell4Pressed() {
        System.out.println("Pressed 4");
                presenter.playMove(4);
                                        addXorO(cell4);


    }

    @FXML
    private void cell5Pressed() {
        System.out.println("Pressed 5");
                presenter.playMove(5);
                                        addXorO(cell5);


    }

    @FXML
    private void cell6Pressed() {
        System.out.println("Pressed 6");
                presenter.playMove(6);
                                        addXorO(cell6);


    }

    @FXML
    private void cell7Pressed() {
        System.out.println("Pressed 7");
                presenter.playMove(7);
                                        addXorO(cell7);


    }

    @FXML
    private void cell8Pressed() {
        System.out.println("Pressed 8");
                presenter.playMove(8);
                                        addXorO(cell8);


    }

    private void addXorO(Button cell) {
        if (presenter.isThatMyTurn()) {
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

    public void playerWins(String winnerName, Button a, Button b, Button c) {
        a.setStyle("-fx-background-color: #ff0000;");
        b.setStyle("-fx-background-color: #ff0000;");
        c.setStyle("-fx-background-color: #ff0000;");
        alertx(winnerName);
    }

    public void setButtonsEnabled(boolean isEnabled) {
        cell0.setDisable(isEnabled);
        cell1.setDisable(isEnabled);
        cell2.setDisable(isEnabled);
        cell3.setDisable(isEnabled);
        cell4.setDisable(isEnabled);
        cell5.setDisable(isEnabled);
        cell6.setDisable(isEnabled);
        cell7.setDisable(isEnabled);
        cell8.setDisable(isEnabled);
    }

    public void setUesers(String p1) {
        System.out.println("p1");
        texto.setText(p1);
    }

    private void alertx(String p1) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VideoAlert.fxml"));
        Parent root = null;
        try {
            root = loader.load();

        } catch (IOException ex) {
            //Logger.getLogger(GameboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);

        VideoAlertController vc = loader.getController();
        vc.fxmlName = "AIGameboard.fxml";
        vc.setLoserVideo();
        vc.setWinnerName(p1);
        vc.playerO = texto.getText();
        stg = (Stage) cell0.getScene().getWindow(); // exceptions
        System.out.println("helooooooo" + stg);
        stg.setScene(scene);
        stg.show();
    }

    private void alert(String p1) {
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
        vc.fxmlName = "AIGameboard.fxml";
        vc.setWinnerName(p1);
        // System.out.println("score x before is "+ scoreX);
        //System.out.println("score o before is "+ scoreO);
        // vc.scoreO = scoreO;
        // vc.scoreX = scoreX;
        // vc.playerX = playerX;
        vc.playerO = texto.getText();
        // System.out.println("score x is "+ scoreX);
        // System.out.println("score o is "+ scoreO);
        // System.out.println("buguyguguy"+scene);
        stg = (Stage) cell0.getScene().getWindow(); // exceptions
        System.out.println("helooooooo" + stg);
        stg.setScene(scene);
        stg.show();

    }

    public void showAlertforTie() {
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

    @FXML
    private void didPressedBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent prevScreen = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(prevScreen);
        stage.setScene(scene);
        stage.show();
    }
}
