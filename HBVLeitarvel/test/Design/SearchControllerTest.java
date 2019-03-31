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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jon
 */
public class SearchControllerTest {
    
    public SearchControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    
    @Test
    public void testSearch() {
        // generic test that should return a specific hotel in a specific location
        System.out.println("search for a specific hotel with name and location within a price");
    
        SearchController instance = new SearchController();
        // TODO review the generated test code and remove the default call to fail.
        ArrayList<Hotel> result2 = instance.search("Hotel Hilton", "Reykjavík", 1000000);
        
        if(result2.isEmpty()){
            
            System.out.println("tomur");
        }
        //System.out.println(result2.get(0).getName());
        for(int i = 0; i < result2.size(); i++)
        {
               
            System.out.println(result2.get(i).getName());
            //System.out.println("fann hotel");

        }
    }
    
    
    @Test
    public void testSearchNoHotelName() {
        System.out.println("Searching for hotels in location/price - No hotel name");
    
        SearchController instance = new SearchController();
        // TODO review the generated test code and remove the default call to fail.
        ArrayList<Hotel> result2 = instance.search("", "Reykjavík", 1000000);
        
        if(result2.isEmpty()){
            
            System.out.println("tomur");
        }
        //System.out.println(result2.get(0).getName());
        for(int i = 0; i < result2.size(); i++)
        {
               
            System.out.println(result2.get(i).getName());
            //System.out.println("fann hotel");

        }
    }
    
    @Test
    public void testSearchNoLocation() {
        System.out.println("Searching for all Hotels with a certain name - No specific location");
    
        SearchController instance = new SearchController();
        // TODO review the generated test code and remove the default call to fail.
        ArrayList<Hotel> result2 = instance.search("Hotel Hilton", "", 1000000);
        
        if(result2.isEmpty()){
            
            System.out.println("tomur");
        }
        //System.out.println(result2.get(0).getName());
        for(int i = 0; i < result2.size(); i++)
        {
               
            System.out.println(result2.get(i).getName());
            //System.out.println("fann hotel");

        }
    }
    
    @Test
    public void testSearchNoPrice() {
        System.out.println("Searching for all Hotels with a certain name and location - no spcific price");
    
        SearchController instance = new SearchController();
        // TODO review the generated test code and remove the default call to fail.
        //0 to replace "null"
        ArrayList<Hotel> result2 = instance.search("Hotel Hilton", "Reykjavík", 0);
        
        if(result2.isEmpty()){
            
            System.out.println("tomur");
        }
        //System.out.println(result2.get(0).getName());
        for(int i = 0; i < result2.size(); i++)
        {
               
            System.out.println(result2.get(i).getName());
            //System.out.println("fann hotel");

        }
    }
    
    @Test
    public void testSearchNoParameters() {
        System.out.println("Searching for all Hotels - no specific name, location or price");
    
        SearchController instance = new SearchController();
        // TODO review the generated test code and remove the default call to fail.
        //0 to replace "null"
        ArrayList<Hotel> result2 = instance.search("", "", 0);
        
        if(result2.isEmpty()){
            
            System.out.println("tomur");
        }
        //System.out.println(result2.get(0).getName());
        for(int i = 0; i < result2.size(); i++)
        {
               
            System.out.println(result2.get(i).getName());
            //System.out.println("fann hotel");

        }
    }
}
