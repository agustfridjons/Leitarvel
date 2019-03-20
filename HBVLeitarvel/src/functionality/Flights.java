/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionality;

/**
 *
 * @author Notandi
 */
public class Flights {
    
    private double departureTime;
    private String departureDate;
    private String arrivalDate;
    private double arrivalTime;    
    private int availableSeats;
    private String flightID;
    private double price;
    private String from;
    private String destination;
    private boolean direct; 
    
    
    public Flights(){
        
    }
    
    public void setFlightDate(String dd,String ad){
        this.departureDate = dd;
        this.arrivalDate = ad;
    }
    
    public String[] getFlightDate(){
        String[] a = {this.arrivalDate, this.departureDate};
        return a;
    }
    
    public void setFlightTime(int dt, int at){
        this.departureTime = dt;
        this.arrivalTime = at;
    }
    
    public double[] getFlightTime(){
        double[] a = {this.arrivalTime, this.departureTime};
        return a;
    }
    
    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isDirect() {
        return direct;
    }

    public void setDirect(boolean direct) {
        this.direct = direct;
    }
  
}
