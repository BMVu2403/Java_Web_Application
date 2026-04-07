package com.exercise.model;

public class Event {
    private int id;
    private String name;
    private String eventDate;
    private double price;
    private int remainingTickets;

    public Event() {
    }

    public Event(int id, String name, String eventDate, double price, int remainingTickets) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
        this.price = price;
        this.remainingTickets = remainingTickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRemainingTickets() {
        return remainingTickets;
    }

    public void setRemainingTickets(int remainingTickets) {
        this.remainingTickets = remainingTickets;
    }
}