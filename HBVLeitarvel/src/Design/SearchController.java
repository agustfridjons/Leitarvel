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
    private ArrayList<Hotel> hotels;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hotels = mock.getList();
    }    

    @FXML
    private void searchButtonHandler(ActionEvent event) {
        
    }
    
    private Hotel search(String name, String location, int maxPrice){
        for(int i = 0; i < hotels.size(); i++){
            if(hotels.get(i).getName() == name){
                return hotels.get(i);
            }
        }
    }
    
    public ArrayList filter(String name, String location, int price){
        
    } 
    
    public ArrayList filter(String name, String loc){
        
    }
    
    public ArrayList filter(String name){
        
    }
    
    
    
}
