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
    
    private Object bookedObj;
    
    //Flight Obj
    private DatabaseController fDB;
    private FlightController flightController;

    private HotelObj mock = new HotelObj();
    private ArrayList<Hotel> hotels = mock.getList();
    
    private int searchOp = 0; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
    }    

    @FXML
    private void searchButtonHandler(ActionEvent event) {
        messageField.setText("");
        String h = TF1.getText();
        String l = TF2.getText();
        LocalDate d1;
        LocalDate d2;
        
        if(searchOp == 1){
            try{
                d1 = fromDp.getValue();
                d2 = toDp.getValue();
                ArrayList<Flight> result = searchFlights(h,l,d1);
                showListF(result);
            }catch(Exception e){
                messageField.setText("Set");
            }
        }else if(searchOp == 0){
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
        if(listV.getSelectionModel().getSelectedItem() != null){
            bookedObj = listV.getSelectionModel().getSelectedItem();
            System.out.println(bookedObj);
        }
    }
    
    public void resetDisplay(){
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
        if(buttonId == 0){
            radioHotel.setSelected(true);
            radioFlight.setSelected(false);
            searchOp = 0;
            label1.setText("Hotel:");
            label2.setText("Location:");
            label3.setText("Check in date:");
            label4.setText("Check out date:");
            intLabel.setText("Max price:");
            resetDisplay();
        }else if(buttonId == 1){
            radioHotel.setSelected(false);
            radioFlight.setSelected(true);
            searchOp = 1;
            label1.setText("Departure location:");
            label2.setText("Destination location:");
            label3.setText("Departure date:");
            label4.setText("Return date:");
            intLabel.setText("Max price:");
            resetDisplay();
        }
    }
}
