package clientMathOperationsREST;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.util.Scanner;

public class ClientMathOperationsREST {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Client client = ClientBuilder.newClient();

        // 1. Input x, y, z and call calculateHTMLOp
        System.out.print("Enter value for x: ");
        int x = scanner.nextInt();

        System.out.print("Enter value for y: ");
        int y = scanner.nextInt();

        System.out.print("Enter value for z: ");
        int z = scanner.nextInt();

        String calcUri = String.format("http://localhost:8080/WebMathOperationsRESTProject/api/MathOp/calculateHTMLOp?x=%d&y=%d&z=%d", x, y, z);
        WebTarget calcTarget = client.target(calcUri);
        Response calcResponse = calcTarget.request().get();
        String calcOutput = calcResponse.readEntity(String.class);
        System.out.println("\nHTML Response:");
        System.out.println(calcOutput);
        calcResponse.close();

        // 2. Call listArray (no input)
        String listUri = "http://localhost:8080/WebMathOperationsRESTProject/api/MathOp/listArray";
        WebTarget listTarget = client.target(listUri);
        Response listResponse = listTarget.request().get();
        String listOutput = listResponse.readEntity(String.class);
        System.out.println("\nList Response:");
        System.out.println(listOutput);
        listResponse.close();

        // 3. Input x for hashmap search
        System.out.print("\nEnter value for x to search HashMap: ");
        int searchX = scanner.nextInt();

        String hashMapUri = String.format("http://localhost:8080/WebMathOperationsRESTProject/api/MathOp/OpHashMap/%d", searchX);
        WebTarget hashMapTarget = client.target(hashMapUri);
        Response hashMapResponse = hashMapTarget.request().get();
        String hashMapOutput = hashMapResponse.readEntity(String.class);
        System.out.println("\nJSON Response from HashMap (x = " + searchX + "):");
        System.out.println(hashMapOutput);
        hashMapResponse.close();

        client.close();
        scanner.close();
    }
}
