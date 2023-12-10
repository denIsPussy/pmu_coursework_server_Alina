package com.labwork01.app.provider.model;

public class ProviderDTO {
    private long id;
    private String name;
    private String surname;
    private String patronymic = "";
    public ProviderDTO(){

    }
    public ProviderDTO(Provider provider){
        this.id = provider.getId();
        this.name = provider.getName();
        this.surname = provider.getSurname();
        this.patronymic = provider.getPatronymic();
    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getPatronymic(){
        return patronymic;
    }
}
