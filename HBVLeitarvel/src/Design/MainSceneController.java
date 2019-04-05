/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Notandi
 */
public class MainSceneController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private TextField number;
    @FXML
    private ComboBox<String> box1;
    @FXML
    private ComboBox<String> box2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initializeComboBox();
     
    }    
    
    public void initializeComboBox(){
        box1.getItems().addAll(
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
);
        
    
        box2.getItems().addAll(
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
            
            
);
    }
    
            
    
}
