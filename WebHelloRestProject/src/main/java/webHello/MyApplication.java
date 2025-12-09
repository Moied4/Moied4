package webHello;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

@ApplicationPath("/api")  // Base URI for all REST endpoints
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        packages("webHello");  // REST resource package name
        register(JacksonFeature.class);  // Enable JSON support
    }
}
