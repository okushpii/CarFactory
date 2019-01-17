package com.training.carfactory.model.entity;

public class Car {

    private Long id;
    private Body body;
    private Engine engine;
    private Wheels wheels;
    private String customer;
    private Status status;
    private Long price;

    public Car() {

    }

    public enum Status{
        NEW,
        IN_PROGRESS,
        DONE
    }

    public Car(Long id, Body body, Engine engine, Wheels wheels, String customer, Status status, Long price) {
        this.id = id;
        this.body = body;
        this.engine = engine;
        this.wheels = wheels;
        this.customer = customer;
        this.status = status;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Wheels getWheels() {
        return wheels;
    }

    public void setWheels(Wheels wheels) {
        this.wheels = wheels;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
