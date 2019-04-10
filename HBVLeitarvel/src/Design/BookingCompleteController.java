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
import javafx.scene.control.TextField;
import functionality.Package;
import java.util.ArrayList;

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
    @FXML
    private TextField hotelName;
    @FXML
    private TextField hotelLocation;
    @FXML
    private TextField roomNumber1;
    @FXML
    private TextField roomNumber11;
    @FXML
    private TextField hotelName1;
    @FXML
    private TextField hotelName11;
    @FXML
    private TextField hotelName12;
    @FXML
    private TextField hotelName121;
    
    private BookingController bc = new BookingController(); 
    private Package pack = new Package();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillInFields();
    }    

    public void fillInFields(){
        ArrayList l = bc.returnBooking(Long.parseLong(pack.getBookingInfo().getBookingNumber()));
        for(int i = 0; i<l.size(); i++){
            System.out.println(l.get(i)+"");
        }
    }
}
