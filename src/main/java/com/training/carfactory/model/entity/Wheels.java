package com.training.carfactory.model.entity;

public class Wheels implements Part{

    private Long id;
    private String name;
    private String size;
    private Long price;
    private String imageUrl;

    public Wheels(Long id, String name, String size, Long price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.imageUrl = imageUrl;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
