/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Data;

import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jón Þórir Sigurðarson
 * @date
 * Háskóli Íslands
 */
public class BookingInfo {
    
    private String name;
    private String email;
    private String adults;
    private String kids;
    private String bookingNumber;
    
    // Tekur inn bókunar upplýsingarnar og útbýr 6 stafa bókunar númer
    public BookingInfo(String n, String e, String a, String k, String b){
        name = n;
        email = e;
        adults = a;
        kids = k;
        if(b == null){
            bookingNumber = getRandomNumber();
        }else{
            bookingNumber = b;
        }
        //openBookingComplete();
    }

    // Býr til 6 random tölur í streng
    public static String getRandomNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        
        return String.format("%06d", number);
    }
    
        public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAdults() {
        return adults;
    }

    public String getKids() {
        return kids;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }
    

    /*private void openBookingComplete() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLBookingComplete.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception q) {
            q.printStackTrace();
        }
    }
    */

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdults(String adults) {
        this.adults = adults;
    }

    public void setKids(String kids) {
        this.kids = kids;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

}