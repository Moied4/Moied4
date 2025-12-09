package webBillingService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@WebService
public class WebBilling {

    private static List<Billing> billingList = new ArrayList<>();

    @WebMethod
    public List<Billing> displayBillingInfo() {
        billingList.clear();

        // Load Billing.in from resources folder
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Billing.in")) {

            if (inputStream == null) {
                throw new RuntimeException("Billing.in not found in resources folder.");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 6) {
                        Billing b = new Billing();
                        b.setClient_ID(Integer.parseInt(parts[0].trim()));
                        b.setClient_LName(parts[1].trim());
                        b.setClient_FName(parts[2].trim());
                        b.setProduct_Name(parts[3].trim());
                        b.setPrd_Price(Double.parseDouble(parts[4].trim()));
                        b.setPrd_Qty(Integer.parseInt(parts[5].trim()));
                        billingList.add(b);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read Billing.in from resources.", e);
        }

        return billingList;
    }

    @WebMethod
    public Billing searchBillingInfo(int client_id) {
        for (Billing b : billingList) {
            if (b.getClient_ID() == client_id) {
                return b;
            }
        }
        return null;
    }
}
