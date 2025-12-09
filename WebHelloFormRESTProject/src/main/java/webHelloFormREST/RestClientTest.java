package webHelloFormREST;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClientTest {

    private static final String BASE_URL = "http://localhost:8080/WebHelloFormRESTProject-1.0-SNAPSHOT/api/hello";

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        // ----- GET -----
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?name=Moied"))
                .GET()
                .build();

        HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET Response: " + getResponse.body());

        // ----- POST -----
        String postData = "name=Moied";
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(postData))
                .build();

        HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST Response: " + postResponse.body());

        // ----- PUT -----
        String putData = "name=Moied";
        HttpRequest putRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .PUT(HttpRequest.BodyPublishers.ofString(putData))
                .build();

        HttpResponse<String> putResponse = client.send(putRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("PUT Response: " + putResponse.body());

        // ----- DELETE -----
        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?name=Moied"))
                .DELETE()
                .build();

        HttpResponse<String> deleteResponse = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("DELETE Response: " + deleteResponse.body());
    }
}
