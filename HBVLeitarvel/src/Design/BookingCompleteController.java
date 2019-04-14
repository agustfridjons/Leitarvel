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
import Data.Package;
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
    private TextField rFlightNumber;
    @FXML
    private TextField rAirline;
    @FXML
    private TextField rDepTime;
    @FXML
    private TextField hotelCheckin;
    @FXML
    private TextField hotelRoom;
    @FXML
    private TextField tourName;
    @FXML
    private TextField tourDate;
    @FXML
    private TextField tourLoc;
    @FXML
    private TextField tourTime;
    
    private BookingController bc = new BookingController();
    private SearchController sc = new SearchController();
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
        ArrayList<String> l = bc.returnBooking(Long.parseLong(pack.getBookingInfo().getBookingNumber()));

        bookingNumber.setText(l.get(0));
        name.setText(l.get(1));
        email.setText(l.get(2));
        adults.setText(l.get(3));
        kids.setText(l.get(4));
        depLocation.setText(l.get(5));
        destination.setText(l.get(9));
        depDate.setText(l.get(7));
        returnDate.setText(l.get(11));
        flightNumber.setText(l.get(6));
        depTime.setText(l.get(8));
        rFlightNumber.setText(l.get(10));
        rDepTime.setText(l.get(12));
        hotelName.setText(l.get(13));
        hotelLocation.setText(l.get(14));
        hotelCheckin.setText(l.get(15));
        hotelRoom.setText(l.get(16));
        tourName.setText(l.get(17));
        tourDate.setText(l.get(20));
        tourLoc.setText(l.get(18));
        tourTime.setText(l.get(19));     //---
        price.setText("1000");        //---
        
    }
}
