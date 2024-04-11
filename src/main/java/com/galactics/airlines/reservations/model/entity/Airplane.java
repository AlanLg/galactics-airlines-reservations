package com.galactics.airlines.reservations.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long airplaneId;
    private String brand;
    private String model;
    private Integer manufacturingYear;

    public Long getAirplaneId() {
        return airplaneId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getManufacturingYear() {
        return manufacturingYear;
    }

    public void setAirplaneId(Long airplaneId) {
        this.airplaneId = airplaneId;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setManufacturingYear(Integer manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }
}