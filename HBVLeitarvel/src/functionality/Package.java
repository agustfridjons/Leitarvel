/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionality;

import is.hi.Core.Flight;
import en.hi.dtsapp.model.Tour;
import vinnsla.Hotel;

/**
 *
 * @author Notandi
 */
public class Package {
    private Hotel hotel;
    private Flight flight;
    private Flight returnFlight;
    private Tour tour;
    
    public Package(){
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
