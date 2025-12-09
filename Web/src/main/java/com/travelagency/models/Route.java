package com.travelagency.models;

public class Route {
    private String id;
    private String source;
    private String destination;
    private String date;
    private String transportType;

    public Route() {}

    public Route(String id, String source, String destination, String date, String transportType) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.transportType = transportType;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTransportType() { return transportType; }
    public void setTransportType(String transportType) { this.transportType = transportType; }
}

