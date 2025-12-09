package clientBillingREST;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Scanner;

public class ClientBillingREST {

    private static final String BASE_URI = "http://localhost:8080/WebBillingREST/api/WebBilling";

    private Client client;

    public ClientBillingREST() {
        client = ClientBuilder.newClient();
    }

    // GET billing info as plain text
    public String getBillingInfoPlainText() {
        WebTarget target = client.target(BASE_URI).path("displayTextBillingInfo");
        Response response = target.request(MediaType.TEXT_PLAIN).get();

        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        } else {
            return "Error fetching plain text billing info. HTTP code: " + response.getStatus();
        }
    }

    // GET billing info as HTML
    public String getBillingInfoHTML() {
        WebTarget target = client.target(BASE_URI).path("displayHTMLBillingInfo");
        Response response = target.request(MediaType.TEXT_HTML).get();

        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        } else {
            return "Error fetching HTML billing info. HTTP code: " + response.getStatus();
        }
    }

    // Search for a billing record by client ID (JSON response)
    public String searchBillingById(int clientId) {
        WebTarget target = client.target(BASE_URI).path("searchBilling").path(String.valueOf(clientId));
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(String.class); // raw JSON string
        } else if (response.getStatus() == 404) {
            return "Billing record with ID " + clientId + " not found.";
        } else {
            return "Error searching billing record. HTTP code: " + response.getStatus();
        }
    }

    public void close() {
        client.close();
    }

    public static void main(String[] args) {
        ClientBillingREST billingClient = new ClientBillingREST();

        System.out.println("=== Billing Info (Plain Text) ===");
        System.out.println(billingClient.getBillingInfoPlainText());

        System.out.println("\n=== Billing Info (HTML) ===");
        System.out.println(billingClient.getBillingInfoHTML());

        System.out.println("\n=== Search Billing by ID (Example: 1234) ===");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Client ID to search: ");
        int clientId = scanner.nextInt();

        System.out.println(billingClient.searchBillingById(clientId));

        billingClient.close();
        scanner.close();
    }
}
