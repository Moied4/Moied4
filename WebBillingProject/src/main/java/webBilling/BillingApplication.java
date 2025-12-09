package webBilling;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class BillingApplication extends Application {
    // Registers all REST resources under /api/*
}
