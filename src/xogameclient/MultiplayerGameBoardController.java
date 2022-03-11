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
import xogameclient.services.responsemodels.BoardStatus;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class MultiplayerGameBoardController implements Initializable, MultiPlayerGameControllerInterface {

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

    MultiplayerGameBoardPresenter presenter;

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
        cell0.getProperties().put("TYPE", "U");
        cell1.getProperties().put("TYPE", "U");
        cell2.getProperties().put("TYPE", "U");
        cell3.getProperties().put("TYPE", "U");
        cell4.getProperties().put("TYPE", "U");
        cell5.getProperties().put("TYPE", "U");
        cell6.getProperties().put("TYPE", "U");
        cell7.getProperties().put("TYPE", "U");
        cell8.getProperties().put("TYPE", "U");

    }

    @FXML
    private void cell0Pressed() {
        addXorO(cell0);
        presenter.playMove(0);

    }

    @FXML
    private void cell1Pressed() {
        addXorO(cell1);
        presenter.playMove(1);

    }

    @FXML
    private void cell2Pressed() {
        addXorO(cell2);
        presenter.playMove(2);

    }

    @FXML
    private void cell3Pressed() {
        addXorO(cell3);
        presenter.playMove(3);

    }

    @FXML
    private void cell4Pressed() {
        addXorO(cell4);
        presenter.playMove(4);

    }

    @FXML
    private void cell5Pressed() {
        addXorO(cell5);
        presenter.playMove(5);

    }

    @FXML
    private void cell6Pressed() {
        addXorO(cell6);

        presenter.playMove(6);

    }

    @FXML
    private void cell7Pressed() {
        addXorO(cell7);
        presenter.playMove(7);

    }

    @FXML
    private void cell8Pressed() {
        addXorO(cell8);

        presenter.playMove(8);

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
        cell.getProperties().put("TYPE", "P");
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

    public void setButtonsDisabled(boolean isEnabled) {
        cell0.setDisable(isEnabled || cell0.getProperties().get("TYPE").equals("P"));
        cell1.setDisable(isEnabled || cell1.getProperties().get("TYPE").equals("P"));
        cell2.setDisable(isEnabled || cell2.getProperties().get("TYPE").equals("P"));
        cell3.setDisable(isEnabled || cell3.getProperties().get("TYPE").equals("P"));
        cell4.setDisable(isEnabled || cell4.getProperties().get("TYPE").equals("P"));
        cell5.setDisable(isEnabled || cell5.getProperties().get("TYPE").equals("P"));
        cell6.setDisable(isEnabled || cell6.getProperties().get("TYPE").equals("P"));
        cell7.setDisable(isEnabled || cell7.getProperties().get("TYPE").equals("P"));
        cell8.setDisable(isEnabled || cell8.getProperties().get("TYPE").equals("P"));
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

    @Override
    public void playOpponentMoveAt(int cell) {
        switch (cell) {
            case 0:
                addXorO(cell0);
                break;
            case 1:
                addXorO(cell1);
                break;
            case 2:
                addXorO(cell2);
                break;
            case 3:
                addXorO(cell3);
                break;
            case 4:
                addXorO(cell4);
                break;
            case 5:
                addXorO(cell5);
                break;
            case 6:
                addXorO(cell6);
                break;
            case 7:
                addXorO(cell7);
                break;
            case 8:
                addXorO(cell8);
                break;

        }

    }

    public void showResult(String status, BoardStatus position) {

        if (position == BoardStatus.Col0) {
            cell0.setStyle("-fx-background-color: #ff0000;");
            cell3.setStyle("-fx-background-color: #ff0000;");
            cell6.setStyle("-fx-background-color: #ff0000;");
        } else if (position == BoardStatus.Col1) {
            cell1.setStyle("-fx-background-color: #ff0000;");
            cell4.setStyle("-fx-background-color: #ff0000;");
            cell7.setStyle("-fx-background-color: #ff0000;");
        } else if (position == BoardStatus.Col2) {
            cell2.setStyle("-fx-background-color: #ff0000;");
            cell5.setStyle("-fx-background-color: #ff0000;");
            cell8.setStyle("-fx-background-color: #ff0000;");
        } else if (position == BoardStatus.Row0) {
            cell0.setStyle("-fx-background-color: #ff0000;");
            cell1.setStyle("-fx-background-color: #ff0000;");
            cell2.setStyle("-fx-background-color: #ff0000;");
        } else if (position == BoardStatus.Row1) {
            cell3.setStyle("-fx-background-color: #ff0000;");
            cell4.setStyle("-fx-background-color: #ff0000;");
            cell5.setStyle("-fx-background-color: #ff0000;");
        } else if (position == BoardStatus.Row2) {
            cell6.setStyle("-fx-background-color: #ff0000;");
            cell7.setStyle("-fx-background-color: #ff0000;");
            cell8.setStyle("-fx-background-color: #ff0000;");
        } else if (position == BoardStatus.DiagonalTopLeftToBottomRight) {
            cell0.setStyle("-fx-background-color: #ff0000;");
            cell4.setStyle("-fx-background-color: #ff0000;");
            cell8.setStyle("-fx-background-color: #ff0000;");
        } else if (position == BoardStatus.DiagonalTopRightToBottomLeft) {
            cell2.setStyle("-fx-background-color: #ff0000;");
            cell4.setStyle("-fx-background-color: #ff0000;");
            cell6.setStyle("-fx-background-color: #ff0000;");
        }

        switch (status) {
            case "Win":

                break;
            case "Lose":
                break;
            default:
                break;
        }

        setButtonsDisabled(true);
        gotToAlert(texto.getText());
        initializeScores(8, 8);
        //showAlert(scoreO);
        //alert(texto.getText());
    }

    private void gotToAlert(String player) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VideoAlert.fxml"));
        Parent root = null;
        try {
            root = loader.load();

        } catch (IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(GameboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);

        VideoAlertController vc = loader.getController();
        vc.setWinnerVideo();
        vc.setWinnerName(player);
//          vc.scoreO = scoreO;
//          vc.scoreX = scoreX;
//          vc.playerX = playerX;
//          vc.playerO = playerO;
        vc.fxmlName = "gameboard.fxml";
        stg = (Stage) cell0.getScene().getWindow(); // exceptions
        stg.setScene(scene);
        stg.show();
    }

    public void initializeScores(int scoreX, int scoreO) {
        xScore.setText(String.valueOf(scoreX));
        oScore.setText(String.valueOf(scoreO));
    }

    @Override
    public void setViewButtonsDisabled(boolean isDisabled) {
        setButtonsDisabled(isDisabled);
    }

}
