package mathOperationsREST;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages("mathOperationsREST");
    }
}
