package com.travelagency.rest.bookings;

import com.travelagency.models.Booking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {

    // Dummy in-memory bookings list for demo
    private static List<Booking> bookings = new ArrayList<>();

    @POST
    public Response bookTicket(Booking booking) {
        booking.setStatus("CONFIRMED");
        bookings.add(booking);
        return Response.status(Response.Status.CREATED).entity(booking).build();
    }

    @GET
    @Path("/{bookingId}")
    public Response viewBooking(@PathParam("bookingId") String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getRouteId().equals(bookingId)) {
                return Response.ok(booking).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Booking not found").build();
    }

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

    @DELETE
    @Path("/{bookingId}")
    public Response cancelBooking(@PathParam("bookingId") String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getRouteId().equals(bookingId)) {
                booking.setStatus("CANCELLED");
                return Response.ok("Booking cancelled successfully").build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Booking not found").build();
    }
}
