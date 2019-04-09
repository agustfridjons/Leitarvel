/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

import functionality.BookingInfo;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.lang.Object;
import java.util.ArrayList;
import java.util.regex.*;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


/**
 * FXML Controller class
 *
 * @author Notandi
 */
public class BookingController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> box1;
    @FXML
    private ComboBox<String> box2;
    private BookingInfo x;
    @FXML
    private Label messageField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initializeComboBox();
    }    
    
    public void initializeComboBox(){
        
        for(int i = 0; i < 11; i++){
            box1.getItems().add(i, i + "");
        }
        
        for(int i = 0; i < 11; i++){
            box2.getItems().add(i, i + "");
        }
    }

    @FXML
    private void nextButtonHandler(ActionEvent event) {
        
        if(!validEmail(email.getText())){
            messageField.setText("Email address is not valid");
        }
        // Sendir bókunar upplýsingarnar í BookingInfo klasann
        x = new BookingInfo(
                name.getText(), 
                email.getText(),
                box1.getSelectionModel().getSelectedItem(), 
                box2.getSelectionModel().getSelectedItem()
        );
        System.out.println("Name: " + x.getName());
        System.out.println("Email: " + x.getEmail());
        System.out.println("Adults: " + x.getAdults());
        System.out.println("Kids: " + x.getKids());
        System.out.println("Booking number: " + x.getBookingNumber());
    }

    // Athugar hvort netfang sé gilt 
    // Skilyrðin eru að það verður að vera '@' og '.' á milli strengja
    private boolean validEmail(String email){
        return email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+");
    }
    
    public static void bookJson(/*String flightFrom, String flightTo*/String name, String email, int adults, int children, int bookID) throws IOException, ParseException{
        
        //get json file
        JSONArray bookList = jsonRead();
        
        JSONObject bookingDetail = new JSONObject();
        JSONObject userDetail = new JSONObject();
        JSONObject hotelDetail = new JSONObject();
        JSONObject flightDetail = new JSONObject();
        JSONObject tourDetail = new JSONObject();
        JSONObject bookDetail = new JSONObject();
        //user booking details
        userDetail.put("bookingID", bookID);
        userDetail.put("name", name);
        userDetail.put("email", email);
        userDetail.put("children", children);
        userDetail.put("adults", adults);
        //hotel details
        hotelDetail.put("name",name);
        hotelDetail.put("location",name);
        hotelDetail.put("pricePerNight",name);
        hotelDetail.put("dateStart",name);
        hotelDetail.put("dateEnd",name);
        //user flight details
        flightDetail.put("from", name);
        flightDetail.put("to", name);
        flightDetail.put("price", name);
        flightDetail.put("dateStart", name);
        flightDetail.put("dateEnd", name);
        //user tour details
        tourDetail.put("activityName",name);
        tourDetail.put("activityLocation",name);
        tourDetail.put("activityPrice",name);
        //put the hotel/flight/tour into a booking object
        bookDetail.put("user",userDetail);
        bookDetail.put("hotel",hotelDetail);
        bookDetail.put("flight",flightDetail);
        bookDetail.put("tour", tourDetail);
        //finally put the object into the container holdin all containers that contain booking information
        bookingDetail.put("booking",bookDetail);
        
        bookList.add(bookingDetail);
        //write into json file
        writeJson(bookList);

    }
    
    public static void getBooking(int BookingID) throws FileNotFoundException, IOException, ParseException {
        
        JSONArray bookingList = jsonRead();

        bookingList.forEach( book -> parseBookingObject( (JSONObject) book) );
    }
    
    public static void updateBooking(long bookingID, String name, String email, int children, int adults) throws IOException, FileNotFoundException, ParseException {
        
        JSONArray bookingList = jsonRead();
            
        for(int i = 0; i < bookingList.size(); i++){
            //get the booking object -> go into booking -> into user
            JSONObject json = (JSONObject) bookingList.get(i);
            JSONObject book = (JSONObject) json.get("booking");
            JSONObject bookUser = (JSONObject) book.get("user");
            long id = (long) bookUser.get("bookingID");
            //check if the bookingID matches
            //if it does match we update with new information
            if(id == bookingID) {
                bookUser.put("name", name); 
                bookUser.put("email", email);
                bookUser.put("children", children);
                bookUser.put("adults", adults);

                writeJson(bookingList);
            }
        }
    }
    
    public static ArrayList returnBooking (long bookingID) {
        
        ArrayList<String> arr = new ArrayList();
        JSONArray bookingList = null;
        try {
            bookingList = jsonRead();
        } catch (IOException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(bookingList != null) {
            for(int i = 0; i < bookingList.size(); i++){
                //get the booking object -> go into booking -> into user
                JSONObject json = (JSONObject) bookingList.get(i);
                JSONObject book = (JSONObject) json.get("booking");
                JSONObject bookUser = (JSONObject) book.get("user");
                long id = (long) bookUser.get("bookingID");
                //check if the bookingID matches
                //if it does match we update with new information
                if(id == bookingID) {
                    String name = (String)bookUser.get("name");
                    String email = (String)bookUser.get("email");
                    long chil = (long)bookUser.get("children");
                    long adul = (long)bookUser.get("adults");
                    long bID = (long)bookUser.get("bookingID");

                    arr.add(name);
                    arr.add(""+bID);
                    arr.add(""+chil);
                    arr.add(""+adul);
                    arr.add(email);
                }
            }
        }
        return arr;
    }
    public static long getLastBook() throws IOException {
        JSONArray bookingList = null;
        try {
            bookingList = jsonRead();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        long id = 0;
        if(bookingList != null) {
            JSONObject json = (JSONObject) bookingList.get(bookingList.size()-1);
            JSONObject book = (JSONObject) json.get("booking");
            JSONObject bookUser = (JSONObject) book.get("user");
            id = (long) bookUser.get("bookingID");
        }
        return id;
    }
    private static void writeJson(JSONArray arr) throws IOException {
        //false flag so it doesn't overwrite
        FileWriter file = new FileWriter("Bookings.json",false);
        file.write(arr.toJSONString());
        file.flush();
    }
    
    public static JSONArray jsonRead() throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("Bookings.json");
        Object obj = parser.parse(reader);
        JSONArray bookList = (JSONArray) obj;
        return bookList;
    }
    
    private static void parseBookingObject(JSONObject book) {
        // send to package-ing to package to display on UI
        JSONObject bookObject = (JSONObject) book.get("booking");
        JSONObject userObject = (JSONObject) bookObject.get("user");
        String name = (String) userObject.get("name");
        System.out.println(name);
    }
    
    
    public static void main(String[] args) throws ParseException, IOException {
        
        try {
            bookJson("nyjasta", "nyjasta@hbv.is", 10, 2, 1111);
        } catch (IOException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //updateBooking(888,"nyttnafn4","nyttemail@email.is",9,1);
        /*
        try {
            getBooking(2202);
        } catch (IOException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        ArrayList<String> bList = new ArrayList();
        bList = returnBooking(getLastBook());
        for(int i = 0; i < bList.size()-1; i++) {
            System.out.println(bList.get(i));
        }
      
    }
    
              
}
