/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;
import functionality.Package;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import vinnsla.Hotelroom;

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
        
        for(int i = 1; i < 11; i++){
            box1.getItems().add(i + "");
        }
        
        for(int i = 0; i < 11; i++){
            box2.getItems().add(i + "");
        }
    }

    @FXML
    private void nextButtonHandler(ActionEvent event) {
        
        if(!validEmail(email.getText())){
            messageField.setText("Email address is not valid");
        } else{
   
            // Sendir bókunar upplýsingarnar í BookingInfo klasann
            x = new BookingInfo(
                    name.getText(), 
                    email.getText(),
                    box1.getSelectionModel().getSelectedItem(), 
                    box2.getSelectionModel().getSelectedItem(),
                    null
            );
            System.out.println("Name: " + x.getName());
            System.out.println("Email: " + x.getEmail());
            System.out.println("Adults: " + x.getAdults());
            System.out.println("Kids: " + x.getKids());
            System.out.println("Booking number: " + x.getBookingNumber());
        
            try {
                bookJson(x);
            } catch (IOException ex) {
                Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLSearch.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load(); 
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch(Exception e) {
                    e.printStackTrace();
                }
        }
    }

    // Athugar hvort netfang sé gilt 
    // Skilyrðin eru að það verður að vera '@' og '.' á milli strengja
    private boolean validEmail(String email){
        return email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+");
    }
    
    public void bookJson(BookingInfo b) throws IOException, ParseException{
        
        
        //get json file
        JSONArray bookList = jsonRead();
        
        JSONObject bookingDetail = new JSONObject();
        JSONObject userDetail = new JSONObject();
        JSONObject hotelDetail = new JSONObject();
        JSONObject flightDetail = new JSONObject();
        JSONObject flightFrom = new JSONObject();
        JSONObject flightTo = new JSONObject();
        JSONObject tourDetail = new JSONObject();
        JSONObject bookDetail = new JSONObject();
        //user booking details
        
        userDetail.put("bookingID", b.getBookingNumber());
        userDetail.put("name", b.getName());
        userDetail.put("email", b.getEmail());
        if(b.getKids() != null) {
            userDetail.put("children", b.getKids());
        } else
        {
            userDetail.put("children", "0");
        }
        userDetail.put("adults", b.getAdults());
        //hotel stuff
        hotelDetail.put("name","");
        hotelDetail.put("roomID","");
        //user flight details
        flightFrom.put("name", "");
        flightFrom.put("flightID", "");
        flightFrom.put("date", "");
        flightFrom.put("time", "");
        
        flightTo.put("name", "");
        flightTo.put("flightID", "");
        flightTo.put("date", "");
        flightTo.put("time", "");
        
        flightDetail.put("flightFrom",flightFrom);
        flightDetail.put("flightTo",flightTo);
        //user tour details
        tourDetail.put("name","");
        tourDetail.put("startTime","");
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
    
    public void getBooking(int BookingID) throws FileNotFoundException, IOException, ParseException {
        
        JSONArray bookingList = jsonRead();

        bookingList.forEach( book -> parseBookingObject( (JSONObject) book) );
    }
    
    public void updateBooking(Package p) throws IOException, FileNotFoundException, ParseException {
        
        JSONArray bookingList = jsonRead();
            
        for(int i = 0; i < bookingList.size(); i++){
            //get the booking object -> go into booking -> into user
            JSONObject json = (JSONObject) bookingList.get(i);
            JSONObject book = (JSONObject) json.get("booking");
            JSONObject bookUser = (JSONObject) book.get("user");
            String id = "" + p.getBookingInfo().getBookingNumber();
            System.out.println(i);
            String bID = (String) bookUser.get("bookingID");
            System.out.println("p.getBookingInfo().getBookingNumber() -> " + id);
            System.out.println("bookUser.get... -> " + bID);
            if(id.equals(bID)){
                JSONObject bookFlight = (JSONObject) book.get("flight");
                JSONObject flightTo = (JSONObject) bookFlight.get("flightTo");
                JSONObject flightFrom = (JSONObject) bookFlight.get("flightFrom");
                JSONObject bookHotel = (JSONObject) book.get("hotel");
                JSONObject bookTour = (JSONObject) book.get("tour");

                bookUser.put("bookingID", p.getBookingInfo().getBookingNumber());
                bookUser.put("name", p.getBookingInfo().getName());
                bookUser.put("email", p.getBookingInfo().getEmail());
                bookUser.put("children", p.getBookingInfo().getKids());
                bookUser.put("adults", p.getBookingInfo().getAdults());
                //hotel details
                if(p.getHotel() != null) { 
                    bookHotel.put("name",p.getHotel().getName());
                    bookHotel.put("roomID",p.getHotel().getSelectedRoom());
                }
                //user flight details
                if(p.getFlight() != null || p.getReturnFlight() != null) { 
                    flightTo.put("name", p.getFlight().getAirline());
                    flightTo.put("flightID", p.getFlight().getfNumber());
                    flightTo.put("date", ("" + p.getFlight().getDate()));
                    flightTo.put("time", ("" + p.getFlight().getTime()));
                    
                    flightFrom.put("name", p.getFlight().getAirline());
                    flightFrom.put("flightID", p.getFlight().getfNumber());
                    flightFrom.put("date", ("" + p.getFlight().getDate()));
                    flightFrom.put("time", ("" + p.getFlight().getTime()));
                }
                
                //user tour details
                if(p.getTour() != null) {
                    bookTour.put("name",p.getTour().getName());
                    bookTour.put("startTime",""+p.getTour().getStartTime());
                }
                
                writeJson(bookingList);
            }
        }
    }
    
    public ArrayList returnBooking (long bookingID) {
        
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
                String s = (String) bookUser.get("bookingID");
                long id = Long.valueOf(s);
                //check if the bookingID matches
                //if it does match we update with new information
                if(id == bookingID) {
                    String name = (String)bookUser.get("name");
                    String email = (String)bookUser.get("email");
                    String chil = (String)bookUser.get("children");
                    String adul = (String)bookUser.get("adults");
                    String bID = (String)bookUser.get("bookingID");
                    
                    arr.add(bID);
                    arr.add(name);
                    arr.add(email);
                    arr.add(chil);
                    arr.add(adul);
                }
            }
        }
        return arr;
    }
    public long getLastBook() throws IOException {
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
            String s = (String) bookUser.get("bookingID");
            id = Long.valueOf(s);
        }
        return id;
    }
    private static void writeJson(JSONArray arr) throws IOException {
        //false flag so it doesn't overwrite
        FileWriter file = new FileWriter("Bookings.json",false);
        file.write(arr.toJSONString());
        file.flush();
    }
    
    private static JSONArray jsonRead() throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("Bookings.json");
        Object obj = parser.parse(reader);
        JSONArray bookList = (JSONArray) obj;
        return bookList;
    }
    
    private void parseBookingObject(JSONObject book) {
        // send to package-ing to package to display on UI
        JSONObject bookObject = (JSONObject) book.get("booking");
        JSONObject userObject = (JSONObject) bookObject.get("user");
        String name = (String) userObject.get("name");
        System.out.println(name);
    }
    
    
    public static void main(String[] args) throws ParseException, IOException {
        /*
        try {
            bookJson("nyjasta", "nyjasta@hbv.is", 10, 2, 1111);
        } catch (IOException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //updateBooking(888,"nyttnafn4","nyttemail@email.is",9,1);
        
        try {
            getBooking(2202);
        } catch (IOException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        BookingController b = new BookingController();
        ArrayList<String> bList = new ArrayList();
        bList = b.returnBooking(b.getLastBook());
        for(int i = 0; i < bList.size()-1; i++) {
            System.out.println(bList.get(i));
        }
      
    }
    
              
}
