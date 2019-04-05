import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import is.hi.Core.*;

public class Demo {

	
	public static void main(String[] args) {
		
		DatabaseController db;
		try {
			//B� til n�jan DatabaseController sem s�r um tengingu vi� database.
			db = new DatabaseController();
			//FlightController nota�ur til a� leita af flugi.
			FlightController flightController = new FlightController(db);
			//BookingController s�r um allt sem tengist b�kunum.
			BookingController bookingController = new BookingController(db);
			
			//S�ki �ll flug sem fara fr� "Reykjav�k" til "Akureyrar" me� dagsetninguna 20/01/2019 (dag/man/�r).
			ArrayList<Flight> flights = flightController.searchForFlight("Reykjav�k", "Akureyri", LocalDate.of(2019,01,20));
			//Vel fyrsta flugi�.
			Flight myFlight = flights.get(0);
			//S�ki �ll s�ti sem eru laus me� �essu flugi.
			ArrayList<String> seats = bookingController.getAvailableSeats(myFlight);
			// System.out.println(seats);
			
			//Vel s�ti n�mer 2 sem er laust.
			String mySeat = seats.get(1);

			
			//S�ki b�kunarn�mber sem �g �arf a� nota �egar �g b� til mi�a.
			String bookingNumber = bookingController.getBookingNumber();
			
			//B� til far�ega me� nafni� "J�n J�nsson" og kt "1212882529".
			Passenger myPassenger = new Passenger("J�n J�nsson", "1212882529");
			//B� til mi�an me� �llum uppl�singum h�r fyrir ofan.
			Ticket myTicket = new Ticket(bookingNumber, mySeat, myPassenger, myFlight);
			//B�ti mi�anum vi� virku b�kununa � bookinController.
			bookingController.addTicketToBooking(myTicket);
			//Sta�festi b�kun, h�n fer �� � databaseinn og �a� myndast n� b�kun � bookingControllernum me� n�ju b�kunarn�meri.
			bookingController.confirmBooking();
			
			//Ey�i b�kun �t. 
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
