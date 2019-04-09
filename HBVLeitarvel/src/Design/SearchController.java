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
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
    
    private Package pack = new Package();
    private int flightCount = 0;
    
    //Booking
    
    //Tour Obj
    private TourCatalog tc;
    
    //Flight Obj
    private DatabaseController fDB;
    private FlightController flightController;

    //Hotel mock
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
    private void searchButtonHandler(ActionEvent event) throws SQLException {
        messageField.setText("");
        String tf1 = TF1.getText();
        String tf2 = TF2.getText();
        LocalDate d1 = fromDp.getValue();
        LocalDate d2 = toDp.getValue();
        
        if(searchOp == 1){
            SearchQuery searchQuery = new SearchQuery(LocalDate.of(2019,Month.APRIL,10),LocalDate.of(2019,Month.APRIL,25), "Reykjavík",2,true,3);
            SearchHotel sh = new SearchHotel();
            ArrayList<Hotel> hotelsFound = sh.search(searchQuery);
            System.out.println(hotelsFound.get(0));
            showList(hotelsFound);
        }else if(searchOp == 0){
            try{
                if(d1 == null){
                    messageField.setText("Select a date.");
                    return;
                }else if(TF1.getText().equals("") || TF2.getText().equals("")){
                    messageField.setText("Fill in both, departure and destination fields");
                    return;
                }
                ArrayList<Flight> resultFrom = searchFlights(tf1,tf2,d1);
                if(d2 != null){
                    ArrayList<Flight> resultTo = searchFlights(tf2,tf1,d2);
                    for(int i = 0; i < resultTo.size(); i++){
                        resultFrom.add(resultTo.get(i));
                    }
                }
                showList(resultFrom);
            }catch(Exception e){
                messageField.setText("Set");
            }
        }else if(searchOp == 4){
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

            //ArrayList<Hotel> result = searchHotel(tf1,tf2,p);
            //showList(result);
        }else{
            if(d1 == null){
                    messageField.setText("Select a date.");
                    return;
            }
            listV.setItems(searchTours(tf1,d1,d2));
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
    /*
    public static boolean isSubstring(String a, String b){
        for(int i  = 0; i < a.length()-b.length(); i++)
            if(a.substring(i, b.length()+i).equalsIgnoreCase(b)){
                return true;
        }
        return false;
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
    */
    public void resetDisplay(){
        toDp.setVisible(true);
        label4.setVisible(true);
        intLabel.setVisible(true);
        intTF.setVisible(true);
        label2.setVisible(true);
        TF2.setVisible(true);
        fromDp.setValue(null);
        toDp.setValue(null);
        TF1.setText("");
        TF2.setText("");
        intTF.setText("");
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
                searchOp = 1;
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
                searchOp = 0;
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
                searchOp = 2;
                searchLabel.setText(searchLabel.getText().substring(0, 13)+ "Tours");
                label1.setText("Activity name:");
                label3.setText("Activity date range, Date from:");
                label4.setText("Date to:");
                label2.setVisible(false);
                TF2.setVisible(false);
                intLabel.setText("Max price:");
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
        
        if(selectedObj.getClass() == Flight.class)
                flightCount++;
        
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
                        System.out.println(f);
                        System.out.println(rf);                        
                        if(f.getDate().compareTo(rf.getDate()) > 0){
                            System.out.println("switch fligths");
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
        System.out.println(pack);
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
}
