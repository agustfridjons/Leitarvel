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
        this.makeObj();
    }
    
    private void makeObj(){
        Hotel hotelA = new Hotel();
        Hotel hotelB = new Hotel();
        Hotel hotelC = new Hotel();
        
        hotelA.setName("Hotel Hilton");
        hotelA.setPrice(10000);
        hotelA.setLocation("Reykjavík");
        
        hotelB.setName("Hotel Orkin");
        hotelB.setPrice(12000);
        hotelB.setLocation("Reykjavík");
        
        hotelC.setName("Hotel Kea");
        hotelC.setPrice(15000);
        hotelC.setLocation("Akureyri");
        
        hotels.add(hotelA);
        hotels.add(hotelB);
        hotels.add(hotelC);
    }
    
    public ArrayList getList(){
        return hotels;
    }
    
    }