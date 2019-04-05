/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

import functionality.BookingInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.lang.Object;
import java.util.regex.*;
import javafx.scene.control.Label;



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
    private BookingInfo x;
    @FXML
    private Label messageField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initializeComboBox();
     
    }    
    
    public void initializeComboBox(){
        
        for(int i = 0; i < 11; i++){
            box1.getItems().add(i, i + "");
        }
        
        for(int i = 0; i < 11; i++){
            box2.getItems().add(i, i + "");
        }
    }

    @FXML
    private void nextButtonHandler(ActionEvent event) {
        
        if(!validEmail(email.getText())){
            messageField.setText("Email address is not valid");
        }
        x = new BookingInfo(
                name.getText(), 
                email.getText(),
                box1.getSelectionModel().getSelectedItem(), 
                box2.getSelectionModel().getSelectedItem()
        );
        System.out.println("Name: " + x.getName());
        System.out.println("Email: " + x.getEmail());
        System.out.println("Adults: " + x.getAdults());
        System.out.println("Kids: " + x.getKids());
        System.out.println("Booking number: " + x.getBookingNumber());
    }

    private boolean validEmail(String email){
        if(email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+")){
                return true;
            }
            else{
                return false;
            }
        }
    }
