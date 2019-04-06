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
public class MainSceneController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private TextField number;
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
        if(email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+")){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static boolean checkUniqueID(int id) {
        
        JSONParser parser = new JSONParser();
            
        try {
            Object obj = parser.parse("Bookings.json");
            
            JSONObject jsonObject = (JSONObject)obj;
            //JSONArray arr = jsonObject.getJSONArray("bookings");
            
            System.out.println("ChecUniqueID -> jsonObject");
            System.out.println(jsonObject);
            
            int bookID = (int) jsonObject.get("bookid");
            System.out.println(bookID);
            
            }catch(ParseException e){
                e.printStackTrace();
            }
        return false;
    }
    
    
    public static void bookJson(/*Package pck, */String name, String email, int adults, int children, int bookID) throws IOException, ParseException{
        
        //get the json object before adding to it
        
        
        //checkUniqueID(bookID);
        
        //checkUniqueID(bookID);
        
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
        
        bookingDetail.put("booking",bookDetail);
        //add booking to a list
        //bookingDetail.add(bookDetail);
        JSONParser parser = new JSONParser();
        
        try(FileReader reader = new FileReader("Bookings.json")) {
            Object obj = parser.parse(reader);
            
            JSONArray bookList = (JSONArray) obj;
            System.out.println(bookList);
            
            //add the new booking details to the booking.json 
            bookList.add(bookingDetail);
            
            try(FileWriter file = new FileWriter("Bookings.json",false)){
            
            file.write(bookList.toJSONString());
            //BufferedWriter br = new BufferedWriter(file);
            //br.newLine();
            
            file.flush();
            //br.close();
            }catch(IOException e) {
                e.printStackTrace();
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        //write JSON file
        
    }
    
    public static void getBooking(int BookingID) throws FileNotFoundException, IOException {
        //Package pck = new Package();
        
        JSONParser parser = new JSONParser();
        
        try(FileReader reader = new FileReader("Bookings.json") ){
            Object obj = parser.parse(reader);
            
            JSONArray bookingList = (JSONArray) obj;
            System.out.println(bookingList);
            
            bookingList.forEach( book -> parseBookingObject( (JSONObject) book) );
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
    }
    
    private static void parseBookingObject(JSONObject book) {
        
        JSONObject bookObject = (JSONObject) book.get("booking");
        JSONObject userObject = (JSONObject) bookObject.get("user");
        
        String name = (String) userObject.get("name");
        System.out.println(name);
    }
    
    public static void main(String[] args) throws ParseException {

        try {
            bookJson("new", "jonsson@hbv.is", 10, 2, 888);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            getBooking(1000);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
                
}
