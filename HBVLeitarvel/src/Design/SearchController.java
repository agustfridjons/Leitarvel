/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

import functionality.Hotel;
import is.hi.Core.DatabaseController;
import is.hi.Core.Flight;
import is.hi.Core.FlightController;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

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
    private TextField intTF;
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
    private Label intLabel;
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
    
    //Flight Obj
    private DatabaseController fDB;
    private FlightController flightController;

    private HotelObj mock = new HotelObj();
    private ArrayList<Hotel> hotels = mock.getList();
    
    private int searchOp = 0; 
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
    private Label label41;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO       
        initializeComboBox();
    }    
    
    public void initializeComboBox(){
        
        for(int i = 1; i < 6; i++){
            ratingBox.getItems().add(i + "");
        }
    }
 

    @FXML
    private void searchButtonHandler(ActionEvent event) {
        messageField.setText("");
        String h = TF1.getText();
        String l = TF2.getText();
        LocalDate d1;
        LocalDate d2;
        
        if(searchOp == 0){
            try{
                d1 = fromDp.getValue();
                d2 = toDp.getValue();
                if(d1 == null){
                    messageField.setText("Select a date.");
                    return;
                }else if(TF1.getText().equals("") || TF2.getText().equals("")){
                    messageField.setText("Fill in both, departure and destination fields");
                    return;
                }
                ArrayList<Flight> result = searchFlights(h,l,d1);
                showListF(result);
            }catch(Exception e){
                messageField.setText("Set");
            }
        }else if(searchOp == 1){
            int p;
            if(!intTF.getText().equals("")){
                try{
                p = Integer.parseInt(intTF.getText());
                }catch(NumberFormatException e){
                    messageField.setText("Price has to be a number.");
                    return;
                }
            }else{
                p = 0;
            }

            ArrayList<Hotel> result = searchHotel(h,l,p);
            showListH(result);
        }
    }
    
    public void showListF(ArrayList<Flight> result){
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
    
    public void showListH(ArrayList<Hotel> result){
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
    
    public static boolean isSubstring(String a, String b){
        for(int i  = 0; i < a.length()-b.length(); i++)
            if(a.substring(i, b.length()+i).equalsIgnoreCase(b)){
                return true;
        }
        return false;
    }
    
    public ArrayList searchFlights(String from, String to, LocalDate d1){
        ArrayList<Flight> flights = new ArrayList();
        try{
            fDB = new DatabaseController();
            flightController = new FlightController(fDB);
            flights = flightController.searchForFlight(from,to,d1);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
        // TODO Auto-generated catch block
        return flights;
    }
    
    public ArrayList searchHotel(String name, String location, int maxPrice){
        ArrayList<Hotel> hotelResults;
        //if name = null or w/e
        if(name.length() != 0)
        {
            hotelResults = filterName(name, hotels);
            if(hotelResults == null) return null;
            
            if(location.length() != 0 && !hotelResults.isEmpty()) //or null w/e
            {
                hotelResults = filterLocation(location, hotelResults);
                if(hotelResults.isEmpty()) return null;
                
                if(maxPrice != 0)
                {
                    hotelResults = filterPrice(maxPrice, hotelResults);
                }else{
                    return hotelResults;
                }
                return hotelResults;
            } else if(maxPrice != 0 && !hotelResults.isEmpty()) 
            {
                //location isn't a parameters to search for
                //so just check for prices
                 hotelResults = filterPrice(maxPrice, hotelResults);
                 if(hotelResults.isEmpty()) return null;
            }else
            {
                //nothing?
            }
        // if name is null/empty string w/e
        //then check if location is null/empty etc
        }else if(location.length() != 0 && !hotels.isEmpty())
        {
            hotelResults = filterLocation(location, hotels);
                        
            if(maxPrice != 0 && !hotelResults.isEmpty())
            {
                hotelResults = filterPrice(maxPrice, hotelResults);
            }else{
                return hotelResults;
            }   
            
        //if no hotel name and no location
        //filter by price (if it isnt 0/null etc
        }else if (maxPrice > 0 && !hotels.isEmpty())
        {
            hotelResults = filterPrice(maxPrice, hotels);
            return hotelResults;
        }else
        {
            return hotels;
        }
        
        return hotelResults;
    }
    
    public ArrayList filterName(String name, ArrayList<Hotel> hotelsRes){
        //System.out.println(name);
        ArrayList<Hotel> nameFilter = new ArrayList<>();
        hotelsRes.get(0).getName();
        for(int i = 0; i < hotelsRes.size(); i++){
            if(isSubstring(hotelsRes.get(i).getName(),name)){
                nameFilter.add(hotelsRes.get(i));
            }
        }
        
        return nameFilter;
    } 
    
    public ArrayList filterLocation(String loc, ArrayList<Hotel> hotelsRes){
        ArrayList<Hotel> locFilter = new ArrayList<>();

        for(int i = 0; i < hotelsRes.size(); i++){
            if(isSubstring(hotelsRes.get(i).getLocation(),loc)){
                locFilter.add(hotelsRes.get(i));
            }
        }
        return locFilter;
    }
    
    public ArrayList filterPrice(int price, ArrayList<Hotel> hotelsRes){
        ArrayList<Hotel> priceFilter = new ArrayList<>();
        
        if(hotelsRes == null) return null;
        
        for(int i = 0; i < hotelsRes.size(); i++){
            if(hotelsRes.get(i).getPrice() <= price ){
                priceFilter.add(hotelsRes.get(i));
            }
        }
        return priceFilter;
    }

    @FXML
    private void orderButtonHandler(ActionEvent event) {
        Object selectedObj;
        if(listV.getSelectionModel().getSelectedItem() != null){
            selectedObj = listV.getSelectionModel().getSelectedItem();
            listSelected.getItems().add(selectedObj);
        }
    }
    
    public void resetDisplay(){
        toDp.setVisible(true);
        label4.setVisible(true);
        intLabel.setVisible(true);
        intTF.setVisible(true);
        fromDp.setValue(null);
        toDp.setValue(null);
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        intLabel.setText("");
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
                searchOp = 0;
                searchLabel.setText(searchLabel.getText().substring(0, 13)+ "Hotels");
                label1.setText("Hotel:");
                label2.setText("Location:");
                label3.setText("Check in date:");
                label4.setText("Check out date:");
                intLabel.setText("Max price:");
                break;
            case 0:
                resetDisplay();
                radioHotel.setSelected(false);
                radioFlight.setSelected(true);
                radioTour.setSelected(false);
                searchOp = 1;
                searchLabel.setText(searchLabel.getText().substring(0, 13)+ "Flights");
                label1.setText("Departure location:");
                label2.setText("Destination location:");
                label3.setText("Departure date:");
                label4.setText("Return date:");
                intLabel.setText("Max price:");
                break;
            case 2:
                resetDisplay();
                radioHotel.setSelected(false);
                radioFlight.setSelected(false);
                radioTour.setSelected(true);
                searchOp = 1;
                searchLabel.setText(searchLabel.getText().substring(0, 13)+ "Tours");
                label1.setText("Activity name:");
                label2.setText("Activity location:");
                label3.setText("Activity date:");
                label4.setVisible(false);
                toDp.setVisible(false);
                intLabel.setText("Max price:");
                break;
            default:
                break;
        }
    }

    @FXML
    private void makePackageHandler(ActionEvent event) {
        if(listSelected.getItems().isEmpty()){
            messageField.setText("Nothing selected");
            return;
        }
    }

    @FXML
    private void cancelSelectionHandler(ActionEvent event) {
    }
}
