/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinnsla;

import java.util.ArrayList;

/**
 *
 * @author Bjartur
 */
public class Hotel {
    
    private ArrayList<Hotelroom> hotelrooms;
    private String selectedRoom;
    private String name;
    private String city;
    private String email;
    private String phoneNumber;
    private String checkin;
    private boolean breakfastBool;
    private int rating;
    
    /**
     * Smiður fyrir Hotel sem tekur inn String name.
     * @param name 
     */
    public Hotel(String name) {
        this.name = name;
        this.hotelrooms = new ArrayList<Hotelroom>();
    }

    public ArrayList<Hotelroom> getHotelrooms() {
        return hotelrooms;
    }

    public void setHotelroom(Hotelroom hotelroom) {
        this.hotelrooms.add(hotelroom);
    }
    
    public String getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(String selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isBreakfastBool() {
        return breakfastBool;
    }

    public void setBreakfastBoolean(boolean breakfastBool) {
        this.breakfastBool = breakfastBool;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }
    
    public String toString(){
        return this.name + ", Location: " + this.city + ", Rating: " + this.rating;
    }
    
}
