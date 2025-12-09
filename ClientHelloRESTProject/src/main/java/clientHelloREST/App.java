package clientHelloREST;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class App {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        // Local REST endpoint - plain text
        String plainUri = "http://localhost:8080/WebHelloRESTProject1/api/HiHi/plain";
        WebTarget plainTarget = client.target(plainUri);
        Response plainResponse = plainTarget.request().get();
        String plainOutput = plainResponse.readEntity(String.class);
        System.out.println("Plain Text Response: " + plainOutput);
        plainResponse.close();

        // Local REST endpoint - HTML
        String htmlUri = "http://localhost:8080/WebHelloRESTProject1/api/HiHi/html";
        WebTarget htmlTarget = client.target(htmlUri);
        Response htmlResponse = htmlTarget.request().get();
        String htmlOutput = htmlResponse.readEntity(String.class);
        System.out.println("HTML Response: " + htmlOutput);
        htmlResponse.close();

        // Local REST endpoint - JSON
        String jsonUri = "http://localhost:8080/WebHelloRESTProject1/api/HiHi/json";
        WebTarget jsonTarget = client.target(jsonUri);
        Response jsonResponse = jsonTarget.request().get();
        String jsonOutput = jsonResponse.readEntity(String.class);
        System.out.println("JSON Response: " + jsonOutput);
        jsonResponse.close();

        // Public REST API - Zippopotam.us
        String zipApiUri = "https://api.zippopotam.us/us/89121";
        WebTarget zipTarget = client.target(zipApiUri);
        Response zipResponse = zipTarget.request().get();
        String zipOutput = zipResponse.readEntity(String.class);
        System.out.println("Public API response (Zippopotam.us): " + zipOutput);
        zipResponse.close();

        client.close();
    }
}
