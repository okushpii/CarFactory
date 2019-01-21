package com.training.carfactory.model.entity;

public class Wheels implements Part{

    private Long id;
    private String name;
    private Long size;
    private Long price;

    public Wheels(Long id, String name, Long size, Long price) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public Wheels() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
