/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

import functionality.BookingInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Jón Þórir Sigurðarson
 */
public class BookingCompleteController implements Initializable {

    @FXML
    private AnchorPane airline;
    @FXML
    private TextField depLocation;
    @FXML
    private TextField destination;
    @FXML
    private TextField depDate;
    @FXML
    private TextField returnDate;
    @FXML
    private TextField flightNumber;
    @FXML
    private TextField depTime;
    @FXML
    private TextField returnFlightNumber;
    @FXML
    private TextField completeName;
    @FXML
    private TextField completePrice;
    @FXML
    private TextField completeEmail;
    @FXML
    private TextField completeAdults;
    @FXML
    private TextField completeKids;
    private BookingInfo x;
    @FXML
    private TextField bookingNumber;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Sendir bókunar upplýsingarnar í BookingInfo klasann
        /*completeName.setText(x.getName());
        completeEmail.setText(x.getEmail());
        completeAdults.setText(x.getAdults());
        completeKids.setText(x.getKids());
        bookingNumber.setText(x.getBookingNumber());
*/
    }    
    
}
