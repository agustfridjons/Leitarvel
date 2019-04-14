/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Design.BookingController;
import is.hi.Core.Flight;
import en.hi.dtsapp.model.Tour;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import vinnsla.Hotel;

/**
 *
 * @author Notandi
 */
public final class Package {
    private Hotel hotel;
    private Flight flight;
    private Flight returnFlight;
    private Tour tour;
    private BookingInfo bookingInfo;
    
    public Package(){
        BookingController b = new BookingController();
        
        try {
            long i = b.getLastBook();
            
            ArrayList l = b.returnBooking(i);
            makeBookingInfo(l);
            
        } catch (IOException ex) {
            Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void makeBookingInfo(ArrayList<String> l) {
        bookingInfo = new BookingInfo(l.get(1),l.get(2),l.get(3),l.get(4),l.get(0));
        bookingInfo.setBookingNumber(l.get(0));
        bookingInfo.setName(l.get(1));
        bookingInfo.setEmail(l.get(2));
        bookingInfo.setKids(l.get(3));
        bookingInfo.setAdults(l.get(4));
        
    }
    
    public BookingInfo getBookingInfo() {
        return bookingInfo;
    }

    public Flight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Flight returnflight) {
        this.returnFlight = returnflight;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
    
    public String toString(){
        String s = this.flight + ", " 
                + this.returnFlight + ", " 
                + this.hotel + ", " 
                + this.tour;
        return s;
    }
   
}
