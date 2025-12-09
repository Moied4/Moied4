package clientCarREST;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.GenericType;

import java.util.Collection;
import java.util.Scanner;

public class ClientCarREST {

    private static final String BASE_URI = "http://localhost:8080/WebCarRESTProject/api/webCar";

    private Client client;

    public ClientCarREST() {
        client = ClientBuilder.newClient();
    }

    // Get all cars as plain text
    public String getAllCarsPlainText() {
        WebTarget target = client.target(BASE_URI).path("displayTextCarInfo");
        Response response = target.request(MediaType.TEXT_PLAIN).get();

        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        } else {
            return "Error fetching plain text cars. HTTP code: " + response.getStatus();
        }
    }

    // Get all cars as HTML
    public String getAllCarsHTML() {
        WebTarget target = client.target(BASE_URI).path("displayHTMLCarInfo");
        Response response = target.request(MediaType.TEXT_HTML).get();

        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        } else {
            return "Error fetching HTML cars. HTTP code: " + response.getStatus();
        }
    }

    // Get all cars as JSON
    public Collection<Car> getAllCarsJSON() {
        WebTarget target = client.target(BASE_URI).path("displayJSONCarInfo");
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<Collection<Car>>() {});
        } else {
            System.out.println("Error fetching JSON cars. HTTP code: " + response.getStatus());
            return null;
        }
    }

    // Search car by VIN as JSON
    public Car searchCarByVin(String vin) {
        WebTarget target = client.target(BASE_URI).path("searchCar").path(vin);
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == 200) {
            return response.readEntity(Car.class);
        } else if (response.getStatus() == 404) {
            System.out.println("Car with VIN " + vin + " not found.");
            return null;
        } else {
            System.out.println("Error searching car. HTTP code: " + response.getStatus());
            return null;
        }
    }

    // Get total car price discount from REST endpoint "/TotalCarPriceDiscount"
    public double getTotalCarPriceDiscount() {
        WebTarget target = client.target(BASE_URI).path("TotalCarPriceDiscount");
        Response response = target.request(MediaType.TEXT_PLAIN).get();

        if (response.getStatus() == 200) {
            String discountStr = response.readEntity(String.class);
            try {
                return Double.parseDouble(discountStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid discount number format from server: " + discountStr);
                return 0.0;
            }
        } else {
            System.out.println("Error fetching total car price discount. HTTP code: " + response.getStatus());
            return 0.0;
        }
    }

    // Calculate total car benefit = (Total Operating Car Cost - Total Car Price Discount)
    public void calculateCarBenefit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter total operating car cost (double): ");
        double totalOperatingCost = scanner.nextDouble();

        double totalDiscount = getTotalCarPriceDiscount();
        double totalBenefit = totalOperatingCost - totalDiscount;

        System.out.printf("Total Operating Car Cost: %.2f%n", totalOperatingCost);
        System.out.printf("Total Car Price Discount: %.2f%n", totalDiscount);
        System.out.printf("Total Car Benefit: %.2f%n", totalBenefit);

        scanner.close();
    }

    public void close() {
        client.close();
    }

    public static void main(String[] args) {
        ClientCarREST clientCarREST = new ClientCarREST();

        System.out.println("=== Get All Cars Plain Text ===");
        System.out.println(clientCarREST.getAllCarsPlainText());

        System.out.println("\n=== Get All Cars HTML ===");
        System.out.println(clientCarREST.getAllCarsHTML());

        System.out.println("\n=== Get All Cars JSON ===");
        Collection<Car> cars = clientCarREST.getAllCarsJSON();
        if (cars != null) {
            for (Car c : cars) {
                System.out.println(c);
            }
        }

        System.out.println("\n=== Search Car by VIN (K1245) ===");
        Car car = clientCarREST.searchCarByVin("K1245");
        if (car != null) {
            System.out.println(car);
        }

        System.out.println("\n=== Calculate Car Benefit ===");
        clientCarREST.calculateCarBenefit();

        clientCarREST.close();
    }
}
