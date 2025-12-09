package com.travelagency.rest;  // Correct package

import jakarta.ws.rs.GET;  // To define a GET method
import jakarta.ws.rs.Path;  // To define the path of the resource
import jakarta.ws.rs.Produces;  // To define the response type (Plain Text)
import jakarta.ws.rs.core.MediaType;  // To specify media type for response

@Path("/hello")  // The resource will be accessible under /api/hello
public class HelloResource {

    @GET  // Handles GET requests
    @Produces(MediaType.TEXT_PLAIN)  // Specifies the response media type as plain text
    public String hello() {
        return "Hello, Travel Agency!";  // Response message
    }
}
