package webBilling;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Path("/WebBilling")
public class WebBillingResource {

    private List<Billing> billingList = new ArrayList<>();

    public WebBillingResource() {
        loadBillingData();
    }

    // Load billing data from Billing.in in resources folder
    private void loadBillingData() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("Billing.in");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            billingList.clear();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 6) {
                    int client_ID = Integer.parseInt(parts[0].trim());
                    String client_LName = parts[1].trim();
                    String client_FName = parts[2].trim();
                    String product_Name = parts[3].trim();
                    double prd_Price = Double.parseDouble(parts[4].trim());
                    int prd_Qty = Integer.parseInt(parts[5].trim());

                    Billing billing = new Billing(client_ID, client_LName, client_FName, product_Name, prd_Price, prd_Qty);
                    billingList.add(billing);
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading billing data: " + e.getMessage());
        }
    }

    // ------------------ DISPLAY ENDPOINTS ------------------

    @GET
    @Path("/displayHTMLBillingInfo")
    @Produces(MediaType.TEXT_HTML)
    public String displayHTMLBillingInfo() {
        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<h2>Billing Information</h2>");
        html.append("<table border='1'>");
        html.append("<tr><th>Client ID</th><th>Last Name</th><th>First Name</th><th>Product</th><th>Price</th><th>Qty</th><th>Total Billing</th></tr>");

        for (Billing b : billingList) {
            html.append("<tr>")
                .append("<td>").append(b.getClient_ID()).append("</td>")
                .append("<td>").append(b.getClient_LName()).append("</td>")
                .append("<td>").append(b.getClient_FName()).append("</td>")
                .append("<td>").append(b.getProduct_Name()).append("</td>")
                .append("<td>").append(String.format("%.2f", b.getPrd_Price())).append("</td>")
                .append("<td>").append(b.getPrd_Qty()).append("</td>")
                .append("<td>").append(String.format("%.2f", b.CalculateBilling())).append("</td>")
                .append("</tr>");
        }

        html.append("</table></body></html>");
        return html.toString();
    }

    @GET
    @Path("/displayTextBillingInfo")
    @Produces(MediaType.TEXT_PLAIN)
    public String displayTextBillingInfo() {
        StringBuilder text = new StringBuilder();
        for (Billing b : billingList) {
            text.append("Client ID: ").append(b.getClient_ID()).append(", ")
                .append("Name: ").append(b.getClient_FName()).append(" ").append(b.getClient_LName()).append(", ")
                .append("Product: ").append(b.getProduct_Name()).append(", ")
                .append("Price: ").append(String.format("%.2f", b.getPrd_Price())).append(", ")
                .append("Qty: ").append(b.getPrd_Qty()).append(", ")
                .append("Total Billing: ").append(String.format("%.2f", b.CalculateBilling()))
                .append("\n");
        }
        return text.toString();
    }

    // ------------------ SEARCH BILLING ------------------

    @GET
    @Path("/searchBilling")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchBilling(@QueryParam("client_id") int clientId) {
        for (Billing b : billingList) {
            if (b.getClient_ID() == clientId) {
                return Response.ok(b).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"error\":\"Client ID " + clientId + " not found.\"}")
                .build();
    }

    // ------------------ ADD NEW BILLING ------------------

    @POST
    @Path("/addNewBilling")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Billing> addNewBillingInfo(@FormParam("client_id") int client_ID,
                                           @FormParam("client_LName") String client_LName,
                                           @FormParam("client_FName") String client_FName,
                                           @FormParam("product_Name") String product_Name,
                                           @FormParam("prd_Price") double prd_Price,
                                           @FormParam("prd_Qty") int prd_Qty) {

        Billing newBilling = new Billing(client_ID, client_LName, client_FName,
                                         product_Name, prd_Price, prd_Qty);
        billingList.add(newBilling);
        return billingList;
    }
}
