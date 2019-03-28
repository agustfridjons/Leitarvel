/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

import functionality.Hotel;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Notandi
 */
public class SearchController implements Initializable {

    @FXML
    private ListView<?> listV;
    @FXML
    private TextField hotelTF;
    @FXML
    private TextField locationTF;
    @FXML
    private TextField priceTF;
    
    private HotelObj mock = new HotelObj();
    private ArrayList<Hotel> hotels = mock.getList();;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
    }    

    @FXML
    private void searchButtonHandler(ActionEvent event) {
        
    }
    
    public ArrayList search(String name, String location, int maxPrice){
        ArrayList<Hotel> hotelResults = new ArrayList<>();
        if(name.length() != 0)
        {
            hotelResults = filterName(name, hotels);
            if(hotelResults == null) return null;
            
            hotelResults = filterLocation(location, hotelResults);
            if(hotelResults.isEmpty()) return null;
            
            hotelResults = filterPrice(maxPrice, hotelResults);
            
            if(hotelResults.isEmpty()) return null;

        }
        
        else if(location.length() != 0){
            hotelResults = filterLocation(location, hotels);
            
            if(hotelResults.isEmpty()) return null;

            hotelResults = filterPrice(maxPrice, hotelResults);
            
            if(hotelResults.isEmpty()) return null;

        }
        
        else if (maxPrice <= 0)
        {
            
            hotelResults = filterPrice(maxPrice, hotels);
            if(hotelResults.isEmpty()) return null;

        }
        else return null;
        
        return hotelResults;
        
        
    }
    
    public ArrayList filterName(String name, ArrayList<Hotel> hotelsRes){
        System.out.println(name);
        ArrayList<Hotel> nameFilter = new ArrayList<>();
        hotelsRes.get(0).getName();
        if(hotelsRes == null) return null;
        for(int i = 0; i < hotelsRes.size(); i++){
            if(hotelsRes.get(i).getName() == name){
                nameFilter.add(hotelsRes.get(i));
                
                System.out.println(hotelsRes.get(i));
            }
        }
        return nameFilter;
    } 
    
    public ArrayList filterLocation(String loc, ArrayList<Hotel> hotelsRes){
        ArrayList<Hotel> locFilter = new ArrayList<>();
        
        for(int i = 0; i < hotelsRes.size(); i++){
            if(hotelsRes.get(i).getLocation().equals(loc)){
                locFilter.add(hotelsRes.get(i));
                
                System.out.println(hotelsRes.get(i));
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
                System.out.println(hotelsRes.get(i));
            }
        }
        return priceFilter;
    }
    

    public static void main(String[] args) {
        SearchController instance = new SearchController();

        ArrayList<Hotel> result2 = instance.search("Hotel Hilton", "Reykjavík", 1000000);

        if(result2 == null){
            
            System.out.println("tomur");
            
        }
        else{
            System.out.println(result2.get(0).getName());
            for(int i = 0; i < result2.size(); i++)
            {

                System.out.println(result2.get(i).getName());
                System.out.println(result2.get(i).getLocation());

                System.out.println(result2.get(i).getPrice());

                System.out.println("fann hotel");

            }
        }
    }
}
