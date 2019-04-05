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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Notandi
 */
public class SearchController implements Initializable {

    @FXML
    private ListView<Hotel> listV;
    @FXML
    private TextField hotelTF;
    @FXML
    private TextField locationTF;
    @FXML
    private TextField priceTF;
    @FXML
    private Label messageField;

    private HotelObj mock = new HotelObj();
    private ArrayList<Hotel> hotels = mock.getList();
    
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
        String h = hotelTF.getText();
        String l = locationTF.getText();
        int p;
        if(!priceTF.getText().equals("")){
            try{
            p = Integer.parseInt(priceTF.getText());
            }catch(NumberFormatException e){
                messageField.setText("Price has to be a number.");
                return;
            }
        }else{
            p=0;
        }
        System.out.println(h +", " + l +", "+p);
        ArrayList<Hotel> result = search(h,l,p);
        
        while(!listV.getItems().isEmpty()){
            listV.getItems().remove(0);
        }
        
        if(!result.isEmpty()){
            for(int i = 0;i < result.size(); i++){
                listV.getItems().add(result.get(i));
            }
        }
    }
    
    public static boolean isSubstring(String a, String b){
        for(int i  = 0; i < a.length()-b.length(); i++)
            if(a.substring(i, b.length()+i).equals(b)){
                return true;
        }
        return false;
    }
    
    public ArrayList search(String name, String location, int maxPrice){
        ArrayList<Hotel> hotelResults = new ArrayList<>();
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
            if(hotelsRes.get(i).getName().equals(name)){
                nameFilter.add(hotelsRes.get(i));
            }
        }
        
        return nameFilter;
    } 
    
    public ArrayList filterLocation(String loc, ArrayList<Hotel> hotelsRes){
        ArrayList<Hotel> locFilter = new ArrayList<>();

        for(int i = 0; i < hotelsRes.size(); i++){
            if(hotelsRes.get(i).getLocation().equals(loc)){
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

    public static void main(String[] args) {
        SearchController instance = new SearchController();

        ArrayList<Hotel> result2 = instance.search("", "", 10000);

        if(result2 == null){
            
            System.out.println("tomur");
            
        }
        else{
            System.out.println(result2.get(0).getName());
            for(int i = 0; i < result2.size(); i++)
            {
                try{
                    System.out.println(result2.get(i).getName());
                    System.out.println(result2.get(i).getLocation());
                    System.out.println(result2.get(i).getPrice());
                }catch(NullPointerException e){
                    System.out.println("fann hotel");
                }
            }
        }
    }

    @FXML
    private void orderButtonHandler(ActionEvent event) {
    }
}
