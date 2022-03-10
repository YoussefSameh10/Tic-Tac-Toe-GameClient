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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class DisplayRecordedGameController implements Initializable {

    Random randam = new Random();
    boolean playerTurn; //O
    char[][] charForBoard = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    String gameRecord = "";

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

    String playerX, playerO;
    Stage stg;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        turn = false;
        backgroundImage.setImage(background);
        player1Card.setImage(imageX);
        player2Card.setImage(imageO);

        parseGameRecord();

    }

    @FXML
    private void cell0Pressed() {
        if (charForBoard[0][0] == ' ') {
            char res = (turn == true) ? 'X' : 'O';
            charForBoard[0][0] = res;
            gameRecord += res + "0";
            cellPressed(cell0);
            System.out.println(res);
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
        // cellPressed(cell8);
        if (charForBoard[2][2] == ' ') {
            char res = (turn == true) ? 'X' : 'O';
            charForBoard[2][2] = res;
            gameRecord += res + "8";
            cellPressed(cell8);

        }
    }

    private void parseGameRecord() {
        System.out.println("Here: " + gameRecord);

        Thread th = new Thread() {
            @Override
            public void run() {
                super.run(); //To change body of generated methods, choose Tools | Templates.
                String playerNames = "";
                while (gameRecord.equals("")) {

                }
                System.out.println("After: " + gameRecord);
                playerNames = gameRecord.split(",")[1];
                playerX = playerNames.split("vs")[0];
                playerO = playerNames.split("vs")[1];
                setUesers(playerX, playerO);

                for (int i = 1; i < gameRecord.length(); i += 2) {
                    try {
                        sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DisplayRecordedGameController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (gameRecord.charAt(i) == '0') {
                        Platform.runLater(() -> {
                            cell0Pressed();
                        });
                    } else if (gameRecord.charAt(i) == '1') {
                        Platform.runLater(() -> {
                            cell1Pressed();
                        });
                    } else if (gameRecord.charAt(i) == '2') {
                        Platform.runLater(() -> {
                            cell2Pressed();
                        });
                    } else if (gameRecord.charAt(i) == '3') {
                        Platform.runLater(() -> {
                            cell3Pressed();
                        });
                    } else if (gameRecord.charAt(i) == '4') {
                        Platform.runLater(() -> {
                            cell4Pressed();
                        });
                    } else if (gameRecord.charAt(i) == '5') {
                        Platform.runLater(() -> {
                            cell5Pressed();
                        });
                    } else if (gameRecord.charAt(i) == '6') {
                        Platform.runLater(() -> {
                            cell6Pressed();
                        });
                    } else if (gameRecord.charAt(i) == '7') {
                        Platform.runLater(() -> {
                            cell7Pressed();
                        });
                    } else if (gameRecord.charAt(i) == '8') {
                        Platform.runLater(() -> {
                            cell8Pressed();
                        });
                    }
                }
            }
        };
        th.start();

    }

    private void cellPressed(Button cell) {
        addXorO(cell, turn);
        turn = !turn;
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

    public void setUesers(String p1, String p2) {
        System.out.println("p1");

        textx.setText(p1);
        texto.setText(p2);
    }

    @FXML
    private void didPressedBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        Parent prevScreen = FXMLLoader.load(getClass().getResource("Records.fxml"));
        Scene scene = new Scene(prevScreen);
        stage.setScene(scene);
        stage.show();
    }

}
