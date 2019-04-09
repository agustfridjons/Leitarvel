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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jón Þórir Sigurðarson
 */
public class BookingCompleteController implements Initializable {

    @FXML
    private TextField depLocation;
    @FXML
    private TextField destination;
    @FXML
    private TextField depDate;
    @FXML
    private TextField returnDate;
    @FXML
    private TextField depTime;
    @FXML
    private TextField airline;
    @FXML
    private TextField flightNumber;
    @FXML
    private TextField depLocation11;
    @FXML
    private TextField destination11;
    @FXML
    private TextField depDate11;
    @FXML
    private TextField kids;
    @FXML
    private TextField adults;
    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private TextField bookingNumber;
    @FXML
    private TextField price;
    private BookingInfo x;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    //name.setText(x.getName());
    }    

    
}
