package webCarREST;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Path("webCar")  // 
public class WebCarResource {

    private Map<String, Car> carHashMap;

    @PostConstruct
    public void init() {
        carHashMap = readCarsFromFile();
    }

    private Map<String, Car> readCarsFromFile() {
        Map<String, Car> map = new HashMap<>();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("Car.in");
            if (is == null) {
                System.err.println("Car.in file not found!");
                return map; // Return empty map to avoid null pointer
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\t");
                    if (parts.length == 3) {
                        String vin = parts[0].trim();
                        String desc = parts[1].trim();
                        double price = Double.parseDouble(parts[2].trim());
                        map.put(vin, new Car(vin, desc, price));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @GET
    @Path("displayHTMLCarInfo")
    @Produces(MediaType.TEXT_HTML)
    public String displayHTMLCarInfo() {
        StringBuilder html = new StringBuilder("<html><body>");
        html.append("<h2>Unsorted Cars</h2>");
        html.append(buildHtmlTable(carHashMap.values()));

        List<Car> sortedCars = new ArrayList<>(carHashMap.values());
        sortedCars.sort(Comparator.comparingDouble(Car::discountPrice));

        html.append("<h2>Sorted Cars by Discount Price</h2>");
        html.append(buildHtmlTable(sortedCars));

        html.append("</body></html>");
        return html.toString();
    }

    private String buildHtmlTable(Collection<Car> cars) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table border='1'><tr><th>VIN</th><th>Description</th><th>Price</th><th>Discount Price</th></tr>");
        for (Car c : cars) {
            sb.append("<tr>")
              .append("<td>").append(c.getVin()).append("</td>")
              .append("<td>").append(c.getDesc()).append("</td>")
              .append("<td>").append(c.getPrice()).append("</td>")
              .append("<td>").append(String.format("%.2f", c.discountPrice())).append("</td>")
              .append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    @GET
    @Path("displayTextCarInfo")
    @Produces(MediaType.TEXT_PLAIN)
    public String displayTextCarInfo() {
        StringBuilder sb = new StringBuilder();
        for (Car c : carHashMap.values()) {
            sb.append(String.format("VIN: %s, Desc: %s, Price: %.2f, Discount Price: %.2f\n",
                    c.getVin(), c.getDesc(), c.getPrice(), c.discountPrice()));
        }
        return sb.toString();
    }

    @GET
    @Path("displayJSONCarInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Car> displayJSONCarInfo() {
        return carHashMap.values();
    }

    @GET
    @Path("searchCar/{vin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchJSONCarInfo(@PathParam("vin") String vin) {
        Car car = carHashMap.get(vin);
        if (car == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Car with VIN " + vin + " not found")
                           .build();
        }
        return Response.ok(car).build();
    }@GET
    @Path("TotalCarPriceDiscount")
    @Produces(MediaType.TEXT_PLAIN)
    public String calculateTotalCarPriceDiscount() {
        double totalDiscount = 0.0;
        for (Car c : carHashMap.values()) {
            totalDiscount += c.discountPrice();
        }
        return String.format("%.2f", totalDiscount);
    }

}
