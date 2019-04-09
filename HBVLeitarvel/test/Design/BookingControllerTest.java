/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.json.simple.JSONArray;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jon
 */
public class BookingControllerTest {
    
    public BookingControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of initialize method, of class BookingController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        BookingController instance = new BookingController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializeComboBox method, of class BookingController.
     */
    @Test
    public void testInitializeComboBox() {
        System.out.println("initializeComboBox");
        BookingController instance = new BookingController();
        instance.initializeComboBox();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bookJson method, of class BookingController.
     */
    @Test
    public void testBookJson() throws Exception {
        System.out.println("bookJson");
        String name = "testname";
        String email = "test@mail.";
        int adults = 100;
        int children = 200;
        int bookID = 2019;
        BookingController.bookJson(name, email, adults, children, bookID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBooking method, of class BookingController.
     */
    @Test
    public void testGetBooking() throws Exception {
        System.out.println("getBooking");
        int BookingID = 0;
        int book = (int) BookingController.getLastBook();
        BookingController.getBooking(book);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateBooking method, of class BookingController.
     */
    @Test
    public void testUpdateBooking() throws Exception {
        System.out.println("updateBooking");
        long bookingID = 888;
        String name = "testUpdate";
        String email = "TestUpdate";
        int children = 300;
        int adults = 400;
        BookingController.updateBooking(bookingID, name, email, children, adults);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of jsonRead method, of class BookingController.
     */
    @Test
    public void testJsonRead() throws Exception {
        System.out.println("jsonRead");
        JSONArray expResult = null;
        JSONArray result = BookingController.jsonRead();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnBooking method, of class BookingController.
     */
    @Test
    public void testReturnBooking() {
        System.out.println("returnBooking");
        long bookingID = 0L;
        ArrayList expResult = null;
        ArrayList result = BookingController.returnBooking(bookingID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastBook method, of class BookingController.
     */
    @Test
    public void testGetLastBook() throws Exception {
        System.out.println("getLastBook");
        long expResult = 0L;
        long result = BookingController.getLastBook();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
}
