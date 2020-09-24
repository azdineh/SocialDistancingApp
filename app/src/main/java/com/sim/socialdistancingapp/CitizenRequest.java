package com.sim.socialdistancingapp;

public class CitizenRequest {

    private Destination destination;
    private String destinationStartDate;
    private String destinationStartTime;
    private String destinationEndDate;
    private String destinationEndTime;

    public CitizenRequest(Destination destination, String destinationStartDate, String destinationStartTime, String destinationEndDate, String destinationEndTime) {
        this.destination = destination;
        this.destinationStartDate = destinationStartDate;
        this.destinationStartTime = destinationStartTime;
        this.destinationEndDate = destinationEndDate;
        this.destinationEndTime = destinationEndTime;
    }

    public CitizenRequest(Destination destination) {
        this.destination = destination;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getDestinationStartDate() {
        return destinationStartDate;
    }

    public void setDestinationStartDate(String destinationStartDate) {
        this.destinationStartDate = destinationStartDate;
    }

    public String getDestinationStartTime() {
        return destinationStartTime;
    }

    public void setDestinationStartTime(String destinationStartTime) {
        this.destinationStartTime = destinationStartTime;
    }

    public String getDestinationEndDate() {
        return destinationEndDate;
    }

    public void setDestinationEndDate(String destinationEndDate) {
        this.destinationEndDate = destinationEndDate;
    }

    public String getDestinationEndTime() {
        return destinationEndTime;
    }

    public void setDestinationEndTime(String destinationEndTime) {
        this.destinationEndTime = destinationEndTime;
    }
}
