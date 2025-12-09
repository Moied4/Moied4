package webHelloFormREST;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    // GET example: /api/hello?name=Moied
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(@QueryParam("name") String name) {
        if (name == null || name.isEmpty()) name = "Guest";
        return "GET: Hello " + name + "! Welcome to WebHelloFormRESTProject.";
    }

    // POST example: form submission
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHelloPost(@FormParam("name") String name) {
        return "POST: Hello " + name + "! Your data was received successfully.";
    }

    // PUT example
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateHello(@FormParam("name") String name) {
        return "PUT: Updated greeting record for " + name + ".";
    }

    // DELETE example
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteHello(@QueryParam("name") String name) {
        return "DELETE: Record for " + name + " has been deleted.";
    }
}
