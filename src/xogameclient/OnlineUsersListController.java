/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author sandra
 */
public class OnlineUsersListController implements Initializable {

//    private ImageView BGImage;
//    private ListView<String> onlineuserLV;

    /**
     * Initializes the controller class.
     */
    
    ObservableList<String> names = FXCollections.observableArrayList(
          "Sandra George", "Tasnim Hatem", "Sameh Reda", "Youssef Sameh", "Mohamed Amr", "Sarah Nassrat", "Eman Abo Bakr","Sameh Reda", "Youssef Sameh", "Mohamed Amr", "Sarah Nassrat", "Eman Abo Bakr","Sameh Reda", "Youssef Sameh", "Mohamed Amr", "Sarah Nassrat", "Eman Abo Bakr");
    @FXML
    private BorderPane OUBorderPane;
    @FXML
    private ImageView OUTopImg;
    @FXML
    private AnchorPane leftAnchor;
    @FXML
    private ImageView leftImg;
    @FXML
    private AnchorPane centerAnchor;
    @FXML
    private ListView<String> centerList;
    @FXML
    private AnchorPane rightAnchor;
    @FXML
    private ImageView rightImg;
    
    public class UsersCustomCell extends ListCell<String>{
        public UsersCustomCell(){
            
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
            
            if(item != null){
                setText(item);
                setFont(new Font("Gaegu", 25));
                setTextFill(Color.BLACK);
            }
        }
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //configureUI();
        configureListView();
    }    
    
//    private void configureUI() {
//        Image image = new Image(getClass().getResource("listAssets/BG.jpg").toExternalForm());
//        BGImage.setImage(image);
//    }
    private void configureListView(){
        centerList.setItems(names);
        centerList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //onlineuserLV.setStyle("-fx-background-color: transparent;");
        centerList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new UsersCustomCell();
            }
        });
    }
}
