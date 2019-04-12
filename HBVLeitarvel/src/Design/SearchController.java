/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

import en.hi.dtsapp.controller.TourCatalog;
import en.hi.dtsapp.model.Tour;
import vinnsla.Hotel;
import functionality.Package;
import is.hi.Core.DatabaseController;
import is.hi.Core.Flight;
import is.hi.Core.FlightController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import vinnsla.Hotelroom;
import vinnsla.SearchQuery;
import vinnsla.SearchHotel;

/**
 * FXML Controller class
 *
 * @author Notandi
 */
public class SearchController implements Initializable {

    @FXML
    private ListView listV;
    @FXML
    private TextField TF1;
    @FXML
    private TextField TF2;
    @FXML
    private Label messageField;
    @FXML
    private DatePicker fromDp;
    @FXML
    private DatePicker toDp;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private RadioButton radioHotel;
    @FXML
    private RadioButton radioFlight;
    @FXML
    private RadioButton radioTour;
    @FXML
    private ListView listSelected;
    @FXML
    private Label searchLabel;
    @FXML
    private RadioButton reyk;
    @FXML
    private RadioButton egill;
    @FXML
    private RadioButton Kef;
    @FXML
    private RadioButton isa;
    @FXML
    private ComboBox<String> ratingBox;
    @FXML
    private Label label8;
    @FXML
    private Label label7;
    @FXML
    private ComboBox<Hotelroom> roomBox;
    @FXML
    private Label label6;
    @FXML
    private ComboBox<String> locationBox1;
    @FXML
    private ComboBox<String> locationBox2;
    
    
    private Package pack = new Package();

    private int searchOp = 0; 

    private int flightCount = 0;
    
    //Booking
    
    //Tour Obj
    private TourCatalog tc;
    
    //Flight Obj
    private DatabaseController fDB;
    private FlightController flightController;

    //Hotel mock
    private String hotelLocation;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO       
        initializeComboBox();
    }    
    
    public void initializeComboBox(){
        ratingBox.getItems().clear();
        for(int i = 1; i <= 5; i++){
            ratingBox.getItems().add(""+i);
        }
        locationBox1.getItems().addAll("Reykjavík","Ísafjörður","Akureyri","Vestmannaeyjar","Egilsstaðir" );
        locationBox2.getItems().addAll("Reykjavík","Ísafjörður","Akureyri","Vestmannaeyjar","Egilsstaðir" );
    }
 

    @FXML
    private void searchButtonHandler(ActionEvent event) throws SQLException {
        messageField.setText("");
        String tf1 = TF1.getText();
        LocalDate d1 = fromDp.getValue();
        LocalDate d2 = toDp.getValue();
        if(d1 == null){
                    messageField.setText("Select a date.");
                    return;
            }
        
        if(searchOp == 1){
            int numPers = Integer.parseInt(pack.getBookingInfo().getAdults());
            if (pack.getBookingInfo().getKids() != null)
                numPers = Integer.parseInt(pack.getBookingInfo().getAdults()) + Integer.parseInt(pack.getBookingInfo().getKids());
            int rating = Integer.parseInt(ratingBox.getSelectionModel().getSelectedItem());
            System.out.println(rating);
            SearchQuery searchQuery = new SearchQuery(d1,d2, hotelLocation, numPers, false, rating);
            SearchHotel sh = new SearchHotel();
            ArrayList<Hotel> hotelsFound = sh.search(searchQuery);
            try{
                if(null == hotelsFound.get(0))
                    messageField.setText("No hotel found.");
            }catch(IndexOutOfBoundsException e){
                messageField.setText("No hotel found.");
            }
            showList(hotelsFound);
        }else if(searchOp == 0){
            String lc1 = locationBox1.getSelectionModel().getSelectedItem();
            String lc2 = locationBox2.getSelectionModel().getSelectedItem();
            try{
                if(lc1 == null || lc2 == null){
                    messageField.setText("Fill in both, departure and destination fields");
                    return;
                }else if(lc1.equals(lc2)){
                    messageField.setText("You must select two separate locations.");
                    return;
                }
                ArrayList<Flight> resultFrom = searchFlights(lc1,lc2,d1);
                if(d2 != null){
                    ArrayList<Flight> resultTo = searchFlights(lc2,lc1,d2);
                    for(int i = 0; i < resultTo.size(); i++){
                        resultFrom.add(resultTo.get(i));
                    }
                }
                showList(resultFrom);
            }catch(Exception e){
                messageField.setText("No flights found.");
            }
        }else{
            ObservableList tourL = searchTours(tf1,d1,d2);
            if(tourL.isEmpty()){
                messageField.setText("No toures found.");
            }else{
                listV.setItems(tourL);
            }
        }
    }
    
    public void showList(ArrayList result){
        listV.getItems().clear();
        if(!result.isEmpty()){
            for(int i = 0;i < result.size(); i++){
                try{
                    listV.getItems().add(result.get(i));
                }catch(Exception e){
                    messageField.setText("Error in search.");
                }
            }
        }
    }
    
    public ArrayList searchFlights(String from, String to, LocalDate d1){
        ArrayList<Flight> flights = new ArrayList();
        try{
            fDB = new DatabaseController();
            flightController = new FlightController(fDB);
            flights = flightController.searchForFlight(from,to,d1);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            messageField.setText("No flights found.");
            e.printStackTrace();
	}
        // TODO Auto-generated catch block
        return flights;
    }
    
    public ObservableList searchTours(String kw, LocalDate d1, LocalDate d2){
        ObservableList tours;
        ArrayList<String> exceptions = new ArrayList();
        tours = tc.getToursBySearchParameters(kw,d1,d2,exceptions);
        return tours;
    }

    public void resetDisplay(){
        toDp.setVisible(true);
        label4.setVisible(true);
        label2.setVisible(true);
        TF2.setVisible(false);
        label1.setVisible(true);
        TF1.setVisible(true);
        reyk.setVisible(false);
        egill.setVisible(false);
        Kef.setVisible(false);
        isa.setVisible(false);
        ratingBox.setVisible(false);
        roomBox.setVisible(false);
        label8.setVisible(false);
        label6.setVisible(false);
        label7.setVisible(false);
        fromDp.setValue(null);
        toDp.setValue(null);
        TF1.setVisible(false);
        locationBox1.setVisible(false);
        locationBox2.setVisible(false);
        messageField.setText("");
        TF1.setText("");
        TF2.setText("");
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        listV.getItems().clear();
    }

    @FXML
    private void switchSearchHandler(ActionEvent event) {
        RadioButton buttonPress = (RadioButton)event.getSource();
        int buttonId = Integer.parseInt(buttonPress.getId());    
        switch (buttonId) {
            case 1:
                resetDisplay();
                radioHotel.setSelected(true);
                radioFlight.setSelected(false);
                radioTour.setSelected(false);
                searchOp = 1;
                reyk.setVisible(true);
                egill.setVisible(true);
                Kef.setVisible(true);
                isa.setVisible(true);
                reyk.setSelected(false);
                egill.setSelected(false);
                Kef.setSelected(false);
                isa.setSelected(false);
                ratingBox.setVisible(true);
                label8.setVisible(true);
                label7.setVisible(true);
                label6.setVisible(true);
                roomBox.setVisible(true);
                label2.setVisible(false);
                TF2.setVisible(false);
                TF1.setVisible(false);
                label1.setVisible(false);
                searchLabel.setText(searchLabel.getText().substring(0, 13)+ "Hotels");
                label1.setText("Hotel:");
                label2.setText("Location:");
                label3.setText("Check in date:");
                label4.setText("Check out date:");
                break;
            case 0:
                resetDisplay();
                radioHotel.setSelected(false);
                radioFlight.setSelected(true);
                radioTour.setSelected(false);
                locationBox1.setVisible(true);
                locationBox2.setVisible(true);
                searchOp = 0;
                searchLabel.setText(searchLabel.getText().substring(0, 13)+ "Flights");
                label1.setText("Departure location:");
                label2.setText("Destination location:");
                label3.setText("Departure date:");
                label4.setText("Return date:");
                break;
            case 2:
                resetDisplay();
                radioHotel.setSelected(false);
                radioFlight.setSelected(false);
                radioTour.setSelected(true);
                TF1.setVisible(true);
                searchOp = 2;
                searchLabel.setText(searchLabel.getText().substring(0, 13)+ "Tours");
                label1.setText("Activity:");
                label3.setText("Activity date range, Date from:");
                label4.setText("Date to:");
                label2.setVisible(false);
                TF2.setVisible(false);
                try{
                    tc = new TourCatalog();
                }catch(Exception e){
                    System.out.println("villa tours");
                }
                listV.setItems(tc.getDistinctNameTourList());
                break;
            default:
                break;
        }
    }
    
    @FXML
    private void orderButtonHandler(ActionEvent event) {
        Object selectedObj = listV.getSelectionModel().getSelectedItem();
        ObservableList list = listSelected.getItems();
        
        if(selectedObj == null){
            messageField.setText("Nothing selected");
            return;
        }
        
        if(selectedObj.getClass() == Flight.class){
            flightCount++;
        }else if(selectedObj.getClass() == Hotel.class){
            Hotelroom hr = roomBox.getSelectionModel().getSelectedItem();
            Hotel h = (Hotel) selectedObj;
            h.setSelectedRoom(""+hr.getHotelroomNumber());
        }
        
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getClass() == Flight.class){
                System.out.println(flightCount);
                if(flightCount > 2){
                   list.remove(i);
                   flightCount--;
                }
            }else if(selectedObj.getClass() == list.get(i).getClass()){
                list.remove(i);
            }
        }
        list.add(selectedObj);
    }

    @FXML
    private void makePackageHandler(ActionEvent event) {
        ObservableList list = listSelected.getItems();
        if(list.isEmpty()){
            messageField.setText("Nothing to book");
            return;
        }
        Flight f = null;
        Flight rf = null;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getClass() == Hotel.class){
                pack.setHotel((Hotel) list.get(i));
            }else if(list.get(i).getClass() == Flight.class){
                f = (Flight)list.get(i);
                for(int j = i+1; j < list.size(); j++){
                    if(list.get(j).getClass() == Flight.class){
                        rf = (Flight) list.get(j);               
                        if(f.getDate().compareTo(rf.getDate()) > 0){
                            Flight temp = f;
                            f = rf;
                            rf = temp;
                        }
                        break;
                    }
                }
                if(f == null || rf == null){
                    messageField.setText("No return flight ordered");
                }
                pack.setFlight(f);
                pack.setReturnFlight(rf);
                flightCount = 0;
            }else if(list.get(i).getClass() == Tour.class){
                pack.setTour((Tour) list.get(i));
            }
        }
        
        
        if(checkForNull()){
            //warning dialog
        }
        
        
        
        try {
            System.out.println("updating");
            book(pack);
        } catch (IOException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        System.out.println(pack); 
        try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLBookingComplete.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
    }
    
    public static void book (Package pck) throws IOException, ParseException {
        BookingController b = new BookingController();
        
        b.updateBooking(pck);
    }
    
    //returns true if there is null in package
    public boolean checkForNull(){
        if(pack.getFlight()==null || 
                pack.getReturnFlight()==null || 
                pack.getTour()==null || 
                pack.getHotel()==null)
            return true;
        return false;
    }

    @FXML
    private void cancelSelectionHandler(ActionEvent event) {
        Object selectedOb = listSelected.getSelectionModel().getSelectedItem();
        TF1.setText(pack.getBookingInfo().getName());
        if(selectedOb == null){
            messageField.setText("Highlight the object you whant to delete");
            return;
        }
        
        if(selectedOb.getClass() == Flight.class){
            listSelected.getItems().remove(listSelected.getSelectionModel().getSelectedIndex());
            flightCount--;
        }else{
            listSelected.getItems().remove(listSelected.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    private void setHotelLocation(ActionEvent event) {
        RadioButton buttonPress = (RadioButton)event.getSource();
        int buttonId = Integer.parseInt(buttonPress.getId());
        switch (buttonId) {
            case 0:
                hotelLocation = "Reykjavik";
                reyk.setSelected(true);
                egill.setSelected(false);
                Kef.setSelected(false);
                isa.setSelected(false);
                break;
            case 1:
                hotelLocation = "Egilsstaðir";
                reyk.setSelected(false);
                egill.setSelected(true);
                Kef.setSelected(false);
                isa.setSelected(false);
                break;
            case 2:
                hotelLocation = "Keflavik";
                reyk.setSelected(false);
                egill.setSelected(false);
                Kef.setSelected(true);
                isa.setSelected(false);
                break;
            case 3:
                hotelLocation = "Isafjörður";
                reyk.setSelected(false);
                egill.setSelected(false);
                Kef.setSelected(false);        
                isa.setSelected(true);
                break;
            default:
                break;
        }
        
    }

    @FXML
    private void checkHotelHandler(MouseEvent event) {
        if(listV.getSelectionModel().getSelectedItem().getClass() == Hotel.class){
            Hotel h = (Hotel)listV.getSelectionModel().getSelectedItem();
            ArrayList<Hotelroom> roomList = h.getHotelrooms();
            for(int i = 0; i < roomList.size(); i++){
                roomBox.getItems().add(roomList.get(i));
            }
            messageField.setText("Pick a room.");
        }
    }
}
