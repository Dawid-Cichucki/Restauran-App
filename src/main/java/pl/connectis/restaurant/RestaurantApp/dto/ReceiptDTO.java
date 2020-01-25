package pl.connectis.restaurant.RestaurantApp.dto;

import pl.connectis.restaurant.RestaurantApp.model.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ReceiptDTO {


    private Long id;
    private List<Dish> dishList = new ArrayList<>();
    private List<Drink> drinkList = new ArrayList<>();
    private Double totalPrice;
    private Employee employee;
    private Client client;
    private LocalDateTime localDateTime;
    public ReceiptDTO() {
    }

    public ReceiptDTO(Receipt receipt) {
        this.id = receipt.getId();
        this.dishList = receipt.getDishList();
        this.drinkList = receipt.getDrinkList();
        this.totalPrice = receipt.getTotalPrice();
        this.employee = receipt.getEmployee();
        this.client = receipt.getClient();
        this.localDateTime = receipt.getLocalDateTime();
    }



    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public List<Drink> getDrinkList() {
        return drinkList;
    }

    public void setDrinkList(List<Drink> drinkList) {
        this.drinkList = drinkList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
