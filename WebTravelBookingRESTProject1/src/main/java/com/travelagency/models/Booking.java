package com.travelagency.models;

public class Booking {
    private String id;            // Unique booking ID
    private String userId;
    private String routeId;
    private String passengerName;
    private String paymentMode;
    private String status;

    // Default constructor
    public Booking() {}

    // Parameterized constructor
    public Booking(String id, String userId, String routeId, String passengerName, String paymentMode, String status) {
        this.id = id;
        this.userId = userId;
        this.routeId = routeId;
        this.passengerName = passengerName;
        this.paymentMode = paymentMode;
        this.status = status;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking [id=" + id + ", userId=" + userId + ", routeId=" + routeId + ", passengerName=" + passengerName
                + ", paymentMode=" + paymentMode + ", status=" + status + "]";
    }
}
