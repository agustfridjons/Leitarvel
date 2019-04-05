/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Design;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initializeComboBox();
     
    }    
    
    public void initializeComboBox(){
        box1.getItems().addAll(
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
);
        
    
        box2.getItems().addAll(
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
            
            
);
    }
    
    public static void bookJson(/*Package pck, */String name, String email, int adults, int children, String bookID) throws IOException{
        
        String hotel = "hotel nafn";
        
        
        JSONObject obj = new JSONObject();
        
        obj.put("BookingID", bookID);
        obj.put("Name", name);
        obj.put("Email", email);
        obj.put("Adults", adults);
        obj.put("Children", children);
        
        JSONArray listHotel = new JSONArray();
        listHotel.add("Hotel: " + hotel);
        listHotel.add("Location: " + hotel);
        listHotel.add("Price: " + hotel);
        
        obj.put("Hotel", listHotel);
        
        try{
            FileWriter file  = new FileWriter("Bookings.json",true);
            BufferedWriter bw = new BufferedWriter(file);
            bw.write(obj.toJSONString());
            bw.newLine();
            bw.close();
            
            //file.write(obj.toJSONString());
            //file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.print(obj);
        
        StringWriter out = new StringWriter();
        obj.writeJSONString(out);
      
        String jsonText = out.toString();
        System.out.print(jsonText);
        
    }
    
    public static void getBooking(int BookingID) throws FileNotFoundException, IOException {
        //Package pck = new Package();
        
        JSONParser parser = new JSONParser();
        //this should later be bookingID
        
        try {
            Object obj = parser.parse(new FileReader("Bookings.json"));
            
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);
            
            long bookID = (long) jsonObject.get("BookingID");
            System.out.println(bookID);
            
            String name = (String) jsonObject.get("Name");
            System.out.println(name);
            
            String email = (String) jsonObject.get("Email");
            System.out.println(email);
            
            int adults = (int) jsonObject.get("Adults");
            System.out.println(adults);
            
            long children = (long) jsonObject.get("Children");
            System.out.println(children);
            
            JSONArray msg = (JSONArray) jsonObject.get("messages");
            Iterator<String> iterator = msg.iterator();
            while(iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }catch(ParseException e){
            e.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) {

        try {
            bookJson("Agust", "Agust@agust.is", 1, 0, "110");
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            getBooking(100);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
            
}
