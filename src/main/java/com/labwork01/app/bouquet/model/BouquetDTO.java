package com.labwork01.app.bouquet.model;


import java.util.Base64;

public class BouquetDTO {
    private Long id;
    private String name;
    private int quantityOfFlowers;
    private int price;
    private String image;

    public BouquetDTO() {
    }

    public BouquetDTO(Bouquet bouquet) {
        this.id = bouquet.getId();
        this.name = bouquet.getName();
        this.quantityOfFlowers = bouquet.getQuantityOfFlowers();
        this.price = bouquet.getPrice();
        this.image = Base64.getEncoder().encodeToString(bouquet.getImage());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityOfFlowers() {
        return quantityOfFlowers;
    }

    public void setQuantityOfFlowers(int quantityOfFlowers) {
        this.quantityOfFlowers = quantityOfFlowers;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
