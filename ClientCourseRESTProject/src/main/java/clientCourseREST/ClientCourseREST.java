package clientCourseREST;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientCourseREST {

    private static final String BASE_URI = "http://localhost:8080/WebCourseRESTProject/api/WebCourse";
    private final Client client;

    public ClientCourseREST() {
        client = ClientBuilder.newClient();
    }

    // Display courses in HTML format
    public String getCoursesHTML() {
        WebTarget target = client.target(BASE_URI).path("displayHTMLCourseInfo");
        Response response = target.request(MediaType.TEXT_HTML).get();
        if (response.getStatus() == 200) {
            return response.readEntity(String.class);
        }
        return "Error fetching courses HTML. HTTP code: " + response.getStatus();
    }

    // Search course by course_no (JSON)
    public String searchCourseByNo(String courseNo) {
        try {
            String encodedCourseNo = URLEncoder.encode(courseNo, StandardCharsets.UTF_8);
            WebTarget target = client.target(BASE_URI).path("searchCourse").path(encodedCourseNo);
            Response response = target.request(MediaType.APPLICATION_JSON).get();
            if (response.getStatus() == 200) {
                return response.readEntity(String.class);
            } else if (response.getStatus() == 404) {
                return "Course with number " + courseNo + " not found.";
            }
            return "Error searching course. HTTP code: " + response.getStatus();
        } catch (Exception e) {
            return "Error encoding course number: " + e.getMessage();
        }
    }

    // Get total course fees for a given course_no (double)
    public double getTotalCourseFees(String courseNo) {
        try {
            String encodedCourseNo = URLEncoder.encode(courseNo, StandardCharsets.UTF_8);
            WebTarget target = client.target(BASE_URI).path("totalCourseFees").path(encodedCourseNo);
            Response response = target.request(MediaType.TEXT_PLAIN).get();
            if (response.getStatus() == 200) {
                try {
                    return Double.parseDouble(response.readEntity(String.class));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid total fees format from server");
                }
            } else {
                System.out.println("Error fetching total course fees. HTTP code: " + response.getStatus());
            }
        } catch (Exception e) {
            System.out.println("Error encoding course number: " + e.getMessage());
        }
        return 0.0;
    }

    // Calculate course benefit = total fees - total operating cost (input)
    public void calculateCourseBenefit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter course number: ");
        String courseNo = scanner.nextLine().trim();

        System.out.print("Enter total operating course cost (double): ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a numeric value for operating cost: ");
            scanner.next();
        }
        double operatingCost = scanner.nextDouble();

        double totalFees = getTotalCourseFees(courseNo);
        double benefit = totalFees - operatingCost;

        System.out.printf("Total Course Fees: %.2f%n", totalFees);
        System.out.printf("Total Operating Course Cost: %.2f%n", operatingCost);
        System.out.printf("Course Benefit: %.2f%n", benefit);

   
    }

    public void close() {
        client.close();
    }

    public static void main(String[] args) {
        ClientCourseREST client = new ClientCourseREST();

        System.out.println("=== Course List (HTML) ===");
        System.out.println(client.getCoursesHTML());

        System.out.println("\n=== Search Course by Course No ===");
        // Use course number with space to match server data
        String searchResult = client.searchCourseByNo("MIS 101");
        System.out.println(searchResult);

        System.out.println("\n=== Calculate Course Benefit ===");
        client.calculateCourseBenefit();

        client.close();
    }
}
