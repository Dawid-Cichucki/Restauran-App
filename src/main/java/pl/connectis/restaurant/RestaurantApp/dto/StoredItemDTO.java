package pl.connectis.restaurant.RestaurantApp.dto;

import pl.connectis.restaurant.RestaurantApp.model.StoredItem;
import pl.connectis.restaurant.RestaurantApp.model.UnitsOfMeasure;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class StoredItemDTO {


    private Long id;
    private String name;
    private Long quantity;
    private UnitsOfMeasure unitOfMeasure;

    public StoredItemDTO() {
    }

    public StoredItemDTO(StoredItem storedItem) {
        this.id = storedItem.getId();
        this.name = storedItem.getName();
        this.quantity = storedItem.getQuantity();
        this.unitOfMeasure = storedItem.getUnitOfMeasure();
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public UnitsOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitsOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
