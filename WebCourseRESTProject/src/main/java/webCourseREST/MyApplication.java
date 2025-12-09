package webCourseREST;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

@ApplicationPath("/api") // Base URI for all REST endpoints
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages("webCourseREST"); // scan  package for REST resources
        register(JacksonFeature.class); // enable JSON support
    }
}
