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
    
    
    private Image background = new Image(getClass().getResourceAsStream("assets/background.jpg"));
    private Image imageX = new Image(getClass().getResourceAsStream("assets/X.png"));
    private Image imageO = new Image(getClass().getResourceAsStream("assets/O.png"));
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        turn = false;
        backgroundImage.setImage(background);
        player1Card.setImage(imageX);
        player2Card.setImage(imageO);
        
    }   
    
    @FXML
    private void cell0Pressed() {
        turn = !turn;
        addXorO(cell0, turn);
        System.out.println("Pressed 0");
    }
    @FXML
    private void cell1Pressed() {
        turn = !turn;
        addXorO(cell1, turn);
        System.out.println("Pressed 1");
    }
    @FXML
    private void cell2Pressed() {
        turn = !turn;
        addXorO(cell2, turn);
        System.out.println("Pressed 2");
    }
    @FXML
    private void cell3Pressed() {
        turn = !turn;
        addXorO(cell3, turn);
        System.out.println("Pressed 3");
    }
    @FXML
    private void cell4Pressed() {
        turn = !turn;
        addXorO(cell4, turn);
        System.out.println("Pressed 4");
    }
    @FXML
    private void cell5Pressed() {
        turn = !turn;
        addXorO(cell5, turn);
        System.out.println("Pressed 5");
    }
    @FXML
    private void cell6Pressed() {
        turn = !turn;
        addXorO(cell6, turn);
        System.out.println("Pressed 6");
    }
    @FXML
    private void cell7Pressed() {
        turn = !turn;
        addXorO(cell7, turn);
        System.out.println("Pressed 7");
    }
    @FXML
    private void cell8Pressed() {
        turn = !turn;
        addXorO(cell8, turn);
        System.out.println("Pressed 8");
    }

    private void addXorO(Button cell, boolean turn) {
        System.out.println(turn);
        System.out.println(cell);
        if(turn) {
            ImageView imageviewX = new ImageView(imageX);
            imageviewX.setFitWidth(75);
            imageviewX.setFitHeight(75);
            cell.setGraphic(imageviewX);
        }
        else {
            ImageView imageviewO = new ImageView(imageO);
            imageviewO.setFitWidth(75);
            imageviewO.setFitHeight(75);
            cell.setGraphic(imageviewO);
        }
    }
    
}
