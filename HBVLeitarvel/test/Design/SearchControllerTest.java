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
        System.out.println("search");
    
        SearchController instance = new SearchController();
        // TODO review the generated test code and remove the default call to fail.
        ArrayList<Hotel> result2 = instance.search("Hotel Hilton", "Reykjavík", 1000000);
        
        if(result2.isEmpty()){
            
            System.out.println("tomur");
        }
        System.out.println(result2.get(0).getName());
        for(int i = 0; i < result2.size(); i++)
        {
               
            System.out.println(result2.get(i).getName());
            System.out.println("fann hotel");

        }
    }
}
