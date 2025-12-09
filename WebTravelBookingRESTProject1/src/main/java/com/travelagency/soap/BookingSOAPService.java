package com.travelagency.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface BookingSOAPService {

    @WebMethod
    String bookFlight(String flightId, String customerName);

    @WebMethod
    String cancelBooking(String bookingId);
}
