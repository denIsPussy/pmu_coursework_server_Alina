package com.labwork01.app.flower.model;

import com.labwork01.app.flower.FlowerType;
import com.labwork01.app.provider.model.Provider;
import jakarta.persistence.*;

import java.util.*;

@Entity(name = "flowers")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private FlowerType type;
    @Lob
    private String image;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Provider> providers = new ArrayList<>();

    public Flower() {
    }

    public Flower(String name, int price, FlowerType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Flower(FlowerDTO flowerDTO) {
        this.name = flowerDTO.getName();
        this.price = flowerDTO.getPrice();
        this.type = FlowerType.fromString(flowerDTO.getType());
        this.image = flowerDTO.getImage();
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


    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public FlowerType getType() {
        return type;
    }
    public void setType(FlowerType type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public List<Provider> getProviders() {
        return providers;
    }

//    public List<OrderFlower> getOrders() {
//        return orderFlowers;
//    }

    public void addProvider(Provider  provider) {
        providers.add(provider);
    }
    public void removeProvider(Provider provider) {
        providers.remove(provider);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flower Flower = (Flower) o;
        return Objects.equals(id, Flower.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Flower. " + "Id: " + id + ", Name: " + name + ", Price" + price +  ".";
    }
}
