package com.travelagency.rest;

import com.travelagency.models.Booking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/bookings")  // Define the path for the resource
@Produces(MediaType.APPLICATION_JSON)  // Define the response format as JSON
@Consumes(MediaType.APPLICATION_JSON)  // Define that it consumes JSON data
public class BookingResource {

    // In-memory bookings list
    private static List<Booking> bookings = new ArrayList<>();

    // POST /api/bookings -> Create a new booking
    @POST
    public Response bookTicket(Booking booking) {
        // Generate unique ID for each booking if not provided
        if (booking.getId() == null || booking.getId().isEmpty()) {
            booking.setId(UUID.randomUUID().toString());
        }
        booking.setStatus("CONFIRMED");
        bookings.add(booking);
        return Response.status(Response.Status.CREATED).entity(booking).build();
    }

    // GET /api/bookings -> Return all bookings
    @GET
    public Response getAllBookings() {
        return Response.ok(bookings).build();
    }

    // GET /api/bookings/{bookingId} -> Return booking by ID
    @GET
    @Path("/{bookingId}")
    public Response viewBooking(@PathParam("bookingId") String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getId().equals(bookingId)) {
                return Response.ok(booking).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Booking not found with ID: " + bookingId)
                .build();
    }

    // GET /api/bookings/user/{userId} -> Return bookings by user
    @GET
    @Path("/user/{userId}")
    public Response viewUserBookings(@PathParam("userId") String userId) {
        List<Booking> userBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getUserId().equals(userId)) {
                userBookings.add(booking);
            }
        }
        return Response.ok(userBookings).build();
    }

    // DELETE /api/bookings/{bookingId} -> Cancel a booking
    @DELETE
    @Path("/{bookingId}")
    public Response cancelBooking(@PathParam("bookingId") String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getId().equals(bookingId)) {
                booking.setStatus("CANCELLED");
                return Response.ok("Booking cancelled successfully").build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Booking not found with ID: " + bookingId)
                .build();
    }
}
