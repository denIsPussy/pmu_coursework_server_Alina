package com.labwork01.app.flower.model;

import com.labwork01.app.provider.model.Provider;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;


public class FlowerDTO {
    private long id;
    private String name;
    private int price;
    private String type;
    private String image;
    private List<Long> providersId;
    public FlowerDTO(){

    }
    public FlowerDTO(Flower flower){
        this.id = flower.getId();
        this.name = flower.getName();
        this.price = flower.getPrice();
        this.type = flower.getType().toString();
        this.image = flower.getImage();
        List<Provider> providers = flower.getProviders();
        this.providersId = providers.size() != 0 ? providers.stream()
                .map(Provider::getId)
                .collect(Collectors.toList()) : Collections.emptyList();
    }
    public long getId() { return id; }
    public String getName(){
        return name;
    }
    public int getPrice(){ return price; }
    public String getType(){
        return type;
    }
    public String getImage(){
        return image;
    }
    public List<Long> getProvidersId(){
        return providersId;
    }
}
