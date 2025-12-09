package webCarREST;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

@ApplicationPath("/api")  // Base URI for all REST endpoints
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        super();
        packages("webCarREST");       // Scan this package for resources
        register(JacksonFeature.class); // Enable Jackson JSON support
    }
}
