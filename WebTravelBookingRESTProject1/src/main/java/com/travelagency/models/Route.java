package com.travelagency.models;

public class Route {
    private String id;            // Unique route ID
    private String source;        // Source location
    private String destination;   // Destination location
    private String date;          // Travel date
    private String transportType; // Type of transport (e.g., Flight, Train)

    // Default constructor
    public Route() {}

    // Parameterized constructor
    public Route(String id, String source, String destination, String date, String transportType) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.transportType = transportType;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    @Override
    public String toString() {
        return "Route [id=" + id + ", source=" + source + ", destination=" + destination + ", date=" + date
                + ", transportType=" + transportType + "]";
    }
}
