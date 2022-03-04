/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient;

import com.sun.org.apache.xml.internal.utils.NameSpace;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
    Socket server;
    DataInputStream dis;
    PrintStream ps;
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
        
        System.out.println("QQQQQQQQ");
        getOnlineUserList();
        //configureUI();
        configureListView();
    }
    
    public void getOnlineUserList()
    {
        try {
            server = new Socket("127.0.0.1", 8080);
            dis = new DataInputStream(server.getInputStream());
            ps = new PrintStream(server.getOutputStream());
            
            new Thread() {
                @Override
                public void run() {
                    ps.println("GetOnlinePlayersList");
                    while(true) {
                        try {
                            String response = dis.readLine();
                            // tmp : parsed playersList
                            String[] tmp = parsePlayersList(response);
                            // names = getO
                        } catch (IOException ex) {
                            Logger.getLogger(RegisterPresenter.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }.start();
            
        } catch (IOException ex) {
            Logger.getLogger(RegisterPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String[] parsePlayersList(String usernames)
    {
        String[] parts = usernames.split(",");
        System.out.println("Player List: ");
        for (int i=0 ; i<parts.length ; i++)
        {
            System.out.println(parts[i]);
        }
        return parts;
    }
//    private void configureUI() {
//        Image image = new Image(getClass().getResource("listAssets/BG.jpg").toExternalForm());
//        BGImage.setImage(image);
//    }
    private void configureListView(){
        centerList.setItems(names);
        System.out.println("RRRRRRRRRRR");
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
