package pl.connectis.restaurant.RestaurantApp.dto;

import pl.connectis.restaurant.RestaurantApp.model.Drink;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class DrinkDTO {


    private Long id;
    private String name;
    private String description;
    private Double serving;
    private Double price;
    private Boolean isAvailable;

    public DrinkDTO() {
    }

    public DrinkDTO(Drink drink) {
        this.id = drink.getId();
        this.name = drink.getName();
        this.description = drink.getDescription();
        this.serving = drink.getServing();
        this.price = drink.getPrice();
        this.isAvailable = drink.getAvailable();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getServing() {
        return serving;
    }

    public void setServing(Double serving) {
        this.serving = serving;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
