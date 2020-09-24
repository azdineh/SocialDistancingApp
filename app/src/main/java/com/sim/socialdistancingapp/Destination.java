package com.sim.socialdistancingapp;

import androidx.annotation.NonNull;

public class Destination implements Cloneable {

    static public int lastOccurenceId=0;
    private int id;
    private String city;
    private String name;
    private int currentCitizensNumber;
    private String type;
    private String whenAvailable;

    public Destination(String city, String name,int number,String type) {
        lastOccurenceId++;
        this.id=lastOccurenceId;
        this.city = city;
        this.name = name;
        this.currentCitizensNumber=number;
        this.type=type;
        this.whenAvailable="Tomorrow From 16:00 To 18:00";
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Destination(String name) {
        this.name = name;
    }

    public Boolean isAvailable(){
            return  currentCitizensNumber<50;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentCitizensNumber() {
        return currentCitizensNumber;
    }

    public void setCurrentCitizensNumber(int currentCitizensNumber) {
        this.currentCitizensNumber = currentCitizensNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWhenAvailable() {
        return whenAvailable;
    }

    public void setWhenAvailable(String whenAvailable) {
        this.whenAvailable = whenAvailable;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
