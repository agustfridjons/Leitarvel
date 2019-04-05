import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import is.hi.Core.*;

public class Demo {

	
	public static void main(String[] args) {
		
		DatabaseController db;
		try {
			//Bý til nýjan DatabaseController sem sér um tengingu við database.
			db = new DatabaseController();
			//FlightController notaður til að leita af flugi.
			FlightController flightController = new FlightController(db);
			//BookingController sér um allt sem tengist bókunum.
			BookingController bookingController = new BookingController(db);
			
			//Sæki öll flug sem fara frá "Reykjavík" til "Akureyrar" með dagsetninguna 20/01/2019 (dag/man/ár).
			ArrayList<Flight> flights = flightController.searchForFlight("Reykjavík", "Akureyri", LocalDate.of(2019,01,20));
			//Vel fyrsta flugið.
			Flight myFlight = flights.get(0);
			//Sæki öll sæti sem eru laus með þessu flugi.
			ArrayList<String> seats = bookingController.getAvailableSeats(myFlight);
			// System.out.println(seats);
			
			//Vel sæti númer 2 sem er laust.
			String mySeat = seats.get(1);

			
			//Sæki bókunarnúmber sem ég þarf að nota þegar ég bý til miða.
			String bookingNumber = bookingController.getBookingNumber();
			
			//Bý til farþega með nafnið "Jón Jónsson" og kt "1212882529".
			Passenger myPassenger = new Passenger("Jón Jónsson", "1212882529");
			//Bý til miðan með öllum upplýsingum hér fyrir ofan.
			Ticket myTicket = new Ticket(bookingNumber, mySeat, myPassenger, myFlight);
			//Bæti miðanum við virku bókununa í bookinController.
			bookingController.addTicketToBooking(myTicket);
			//Staðfesti bókun, hún fer þá í databaseinn og það myndast ný bókun í bookingControllernum með nýju bókunarnúmeri.
			bookingController.confirmBooking();
			
			//Eyði bókun út. 
			bookingController.cancelBooking(bookingNumber);
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
