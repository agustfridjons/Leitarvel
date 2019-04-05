/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package functionality;

import java.util.Random;

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
    
    public BookingInfo(String n, String e, String a, String k){
        name = n;
        email = e;
        adults = a;
        kids = k;
        bookingNumber = getRandomNumber();
    }

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

}