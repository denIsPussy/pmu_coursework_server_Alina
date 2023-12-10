package com.labwork01.app.provider.model;

import com.labwork01.app.flower.model.Flower;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name="providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = true)
    private String patronymic;

    public Provider() {
    }
    public Provider(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    public Provider(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }
    public Provider(ProviderDTO providerDTO) {
        this.name = providerDTO.getName();
        this.surname = providerDTO.getSurname();
        this.patronymic = providerDTO.getPatronymic();
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

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Objects.equals(id, provider.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Provider." + "Id: " + id + ", Name: " + name + ", Surname: " + surname + ", Patronymic='" + patronymic + ".";
    }
}
