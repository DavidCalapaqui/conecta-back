package com.example.conecta_test.entity;


import lombok.*;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;



@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "cars",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"plate", "vim"})}
)
public class Car {

    //PLACA, color, modelo, chasis, cualquier otra información referente al vehículo

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name="plate", nullable = false)
    private String plate;


    @Column(name="color", nullable = false)
    private String color;

    @Column(name = "automaker", nullable = false)
    private String automaker; //marca: ex: Toyota, Mazda, etc
    @Column(name = "model")
    private String model;

    //número de chasis de 17 caracteres
    @Column(name = "vim", nullable = false)
    private String vim;


    public Long getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public String getColor() {
        return color;
    }

    public String getAutomaker() {
        return automaker;
    }

    public String getModel() {
        return model;
    }

    public String getVim() {
        return vim;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAutomaker(String automaker) {
        this.automaker = automaker;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVim(String vim) {
        this.vim = vim;
    }
}
