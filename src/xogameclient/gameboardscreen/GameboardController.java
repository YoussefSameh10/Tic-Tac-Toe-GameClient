/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.gameboardscreen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class GameboardController implements Initializable {

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
    private final Image background = new Image(getClass().getResourceAsStream("assets/background.jpg"));
    private final Image imageX = new Image(getClass().getResourceAsStream("assets/X.jpg"));
    private final Image imageO = new Image(getClass().getResourceAsStream("assets/O.jpg"));
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        turn = false;
        backgroundImage.setImage(background);
        player1Card.setImage(imageX);
        player2Card.setImage(imageO);
        
    }   
    
    @FXML
    private void cell0Pressed() {
        cellPressed(cell0);
    }
    @FXML
    private void cell1Pressed() {
        cellPressed(cell1);
    }
    @FXML
    private void cell2Pressed() {
        cellPressed(cell2);
    }
    @FXML
    private void cell3Pressed() {
        cellPressed(cell3);
    }
    @FXML
    private void cell4Pressed() {
        cellPressed(cell4);
    }
    @FXML
    private void cell5Pressed() {
        cellPressed(cell5);
    }
    @FXML
    private void cell6Pressed() {
        cellPressed(cell6);
    }
    @FXML
    private void cell7Pressed() {
        cellPressed(cell7);
    }
    @FXML
    private void cell8Pressed() {
        cellPressed(cell8);
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
    
}
