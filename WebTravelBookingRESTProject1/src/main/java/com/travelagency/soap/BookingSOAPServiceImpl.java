package com.travelagency.soap;

import jakarta.jws.WebService;

@WebService(endpointInterface = "com.travelagency.soap.BookingSOAPService")
public class BookingSOAPServiceImpl implements BookingSOAPService {

    @Override
    public String bookFlight(String flightId, String customerName) {
        return "SOAP: Booking confirmed for " + customerName + " on flight " + flightId;
    }

    @Override
    public String cancelBooking(String bookingId) {
        return "SOAP: Booking " + bookingId + " cancelled successfully.";
    }
}
