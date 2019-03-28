/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

import functionality.Hotel;
import java.util.ArrayList;

/**
 *
 * @author Notandi
 */
public class HotelObj {
    
    private ArrayList<Hotel> hotels = new ArrayList<Hotel>(); 

    
    public HotelObj(){
        this.hotels = this.makeObj();
    }
    
    private ArrayList makeObj(){
        Hotel hotelA = new Hotel();
        Hotel hotelB = new Hotel();
        Hotel hotelC = new Hotel();
        
        hotelA.setName("Hotel Hilton");
        hotelA.setPrice(10000);
        hotelA.setLocation("Reykjavík");
    }

    
    
    
    
    
}