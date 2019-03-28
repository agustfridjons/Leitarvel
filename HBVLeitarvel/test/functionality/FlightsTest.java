/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionality;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Notandi
 */
public class FlightsTest {
    
    public FlightsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setFlightDate method, of class Flights.
     */
    @Test
    public void testSetFlightDate() {
        System.out.println("setFlightDate");
        String dd = "";
        String ad = "";
        Flights instance = new Flights();
        instance.setFlightDate(dd, ad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFlightDate method, of class Flights.
     */
    @Test
    public void testGetFlightDate() {
        System.out.println("getFlightDate");
        Flights instance = new Flights();
        String[] expResult = null;
        String[] result = instance.getFlightDate();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFlightTime method, of class Flights.
     */
    @Test
    public void testSetFlightTime() {
        System.out.println("setFlightTime");
        int dt = 0;
        int at = 0;
        Flights instance = new Flights();
        instance.setFlightTime(dt, at);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFlightTime method, of class Flights.
     */
    @Test
    public void testGetFlightTime() {
        System.out.println("getFlightTime");
        Flights instance = new Flights();
        double[] expResult = null;
        double[] result = instance.getFlightTime();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAvailableSeats method, of class Flights.
     */
    @Test
    public void testGetAvailableSeats() {
        System.out.println("getAvailableSeats");
        Flights instance = new Flights();
        int expResult = 0;
        int result = instance.getAvailableSeats();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAvailableSeats method, of class Flights.
     */
    @Test
    public void testSetAvailableSeats() {
        System.out.println("setAvailableSeats");
        int availableSeats = 0;
        Flights instance = new Flights();
        instance.setAvailableSeats(availableSeats);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFlightID method, of class Flights.
     */
    @Test
    public void testGetFlightID() {
        System.out.println("getFlightID");
        Flights instance = new Flights();
        String expResult = "";
        String result = instance.getFlightID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFlightID method, of class Flights.
     */
    @Test
    public void testSetFlightID() {
        System.out.println("setFlightID");
        String flightID = "";
        Flights instance = new Flights();
        instance.setFlightID(flightID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrice method, of class Flights.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Flights instance = new Flights();
        double expResult = 0.0;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrice method, of class Flights.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        double price = 0.0;
        Flights instance = new Flights();
        instance.setPrice(price);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFrom method, of class Flights.
     */
    @Test
    public void testGetFrom() {
        System.out.println("getFrom");
        Flights instance = new Flights();
        String expResult = "";
        String result = instance.getFrom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFrom method, of class Flights.
     */
    @Test
    public void testSetFrom() {
        System.out.println("setFrom");
        String from = "";
        Flights instance = new Flights();
        instance.setFrom(from);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDestination method, of class Flights.
     */
    @Test
    public void testGetDestination() {
        System.out.println("getDestination");
        Flights instance = new Flights();
        String expResult = "";
        String result = instance.getDestination();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDestination method, of class Flights.
     */
    @Test
    public void testSetDestination() {
        System.out.println("setDestination");
        String destination = "";
        Flights instance = new Flights();
        instance.setDestination(destination);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDirect method, of class Flights.
     */
    @Test
    public void testIsDirect() {
        System.out.println("isDirect");
        Flights instance = new Flights();
        boolean expResult = false;
        boolean result = instance.isDirect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDirect method, of class Flights.
     */
    @Test
    public void testSetDirect() {
        System.out.println("setDirect");
        boolean direct = false;
        Flights instance = new Flights();
        instance.setDirect(direct);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
