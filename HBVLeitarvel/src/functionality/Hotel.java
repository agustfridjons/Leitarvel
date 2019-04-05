/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package functionality;

/**
 *
 * @author Jón Þórir Sigurðarson
 * @date
 * Háskóli Íslands
 */
public class Hotel {
    
    private String name;
    private int ratingStars;
    private String location;
    private int roomID;
    private int price;
    private boolean availability;
    
    public Hotel(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRatingStars() {
        return ratingStars;
    }

    public void setRatingStars(int ratingStars) {
        this.ratingStars = ratingStars;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    
    public String toString(){
        String s = this.name + ", " + this.location + ", Price per person: " + this.price;
        return s;
    }   

}
