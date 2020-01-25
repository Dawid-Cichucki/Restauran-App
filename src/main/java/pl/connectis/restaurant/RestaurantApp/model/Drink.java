package pl.connectis.restaurant.RestaurantApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    private Double serving;
    private Double price;
    private Boolean isAvailable;
    @JsonIgnore
    @ManyToMany(mappedBy = "drinkList")
    private List<Receipt> receiptList;

    public Drink() {
    }

    public Drink( String name, String description, Double serving, Double price, Boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.serving = serving;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Receipt> getReceiptList() {
        if (receiptList == null){
            receiptList = new ArrayList<>();
        }
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
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
