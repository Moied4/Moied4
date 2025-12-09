package webHello;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("HiHi")  // Base path: /WebHelloRESTProject/api/HiHi
public class HelloResource {

    @GET
    @Path("plain")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHelloPlain() {
        return "Hello from REST (plain text)!";
    }

    @GET
    @Path("html")
    @Produces(MediaType.TEXT_HTML)
    public String sayHelloHtml() {
        return "<html><body><h1>Hello from REST (HTML)!</h1></body></html>";
    }

    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    public Message sayHelloJson() {
        return new Message("Hello from REST (JSON)!");
    }

    // Inner class for JSON serialization
    public static class Message {
        private String message;

        public Message() {}  // Default constructor needed

        public Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
