package com.labwork01.app.bouquet.model;

import com.labwork01.app.order.model.Order;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "bouquets")
public class Bouquet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantityOfFlowers;
    private int price;
    @Lob
    private byte[] image;

    public Bouquet() {
    }

    @ManyToMany(mappedBy = "bouquets", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Bouquet(String name, int quantityOfFlowers, int price, byte[] image) {
        this.name = name;
        this.quantityOfFlowers = quantityOfFlowers;
        this.price = price;
        this.image = image;
    }

    public Bouquet(BouquetDTO bouquetDTO) {
        this.id = bouquetDTO.getId();
        this.name = bouquetDTO.getName();
        this.quantityOfFlowers = bouquetDTO.getQuantityOfFlowers();
        this.price = bouquetDTO.getPrice();
        this.image = Base64.getDecoder().decode(bouquetDTO.getImage());
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

    public Integer getQuantityOfFlowers() {
        return quantityOfFlowers;
    }

    public void setQuantityOfFlowers(Integer quantityOfFlowers) {
        this.quantityOfFlowers = quantityOfFlowers;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    public void addOrder(Order order) {
        orders.add(order);
        order.getBouquets().add(this);
    }
    public void removeOrder(Order order) {
        orders.remove(order);
        order.getBouquets().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bouquet bouquet = (Bouquet) o;
        return quantityOfFlowers == bouquet.quantityOfFlowers && price == bouquet.price && Objects.equals(id, bouquet.id) && Objects.equals(name, bouquet.name) && Arrays.equals(image, bouquet.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, quantityOfFlowers, price);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "Bouquet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantityOfFlowers=" + quantityOfFlowers +
                ", price=" + price +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}