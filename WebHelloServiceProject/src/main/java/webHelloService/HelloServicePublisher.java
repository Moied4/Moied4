package webHelloService;

import jakarta.xml.ws.Endpoint;

public class HelloServicePublisher {
    public static void main(String[] args) {
        // Publish the service at this URL
        Endpoint.publish(
            "http://localhost:8080/WebHelloServiceProject-1.0-SNAPSHOT/HelloService",
            new HelloService()
        );

        System.out.println("Service is published at http://localhost:8080/WebHelloServiceProject-1.0-SNAPSHOT/HelloService?wsdl");
    }
}
