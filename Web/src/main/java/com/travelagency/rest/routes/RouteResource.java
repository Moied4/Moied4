package com.travelagency.rest.routes;

import com.travelagency.models.Route;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/routes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RouteResource {

    // Dummy in-memory route list for demo
    private static List<Route> routes = new ArrayList<>();

    static {
        // Some sample routes
        routes.add(new Route("1", "NYC", "LA", "2025-10-01", "Flight"));
        routes.add(new Route("2", "NYC", "Chicago", "2025-10-02", "Train"));
    }

    @GET
    @Path("/search")
    public Response searchRoutes(@QueryParam("source") String source,
                                 @QueryParam("destination") String destination,
                                 @QueryParam("date") String date,
                                 @QueryParam("transportType") String transportType) {
        List<Route> result = routes.stream()
                .filter(r -> (source == null || r.getSource().equalsIgnoreCase(source)) &&
                             (destination == null || r.getDestination().equalsIgnoreCase(destination)) &&
                             (date == null || r.getDate().equals(date)) &&
                             (transportType == null || r.getTransportType().equalsIgnoreCase(transportType)))
                .collect(Collectors.toList());

        return Response.ok(result).build();
    }

    @POST
    @Path("/admin/routes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoute(Route route) {
        routes.add(route);
        return Response.status(Response.Status.CREATED).entity(route).build();
    }

    @PUT
    @Path("/admin/routes/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRoute(@PathParam("id") String id, Route updatedRoute) {
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).getId().equals(id)) {
                routes.set(i, updatedRoute);
                return Response.ok("Route updated successfully").build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Route not found").build();
    }
}
