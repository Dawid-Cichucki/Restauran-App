package pl.connectis.restaurant.RestaurantApp.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToMany
    @JoinTable(name = "receipt_dish",
    joinColumns = @JoinColumn(name = "receipt_id"),
    inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<Dish> dishList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "drink_receipt",
    joinColumns = @JoinColumn(name = "receipt_id"),
    inverseJoinColumns = @JoinColumn(name = "drink_id"))
    private List<Drink> drinkList = new ArrayList<>();

    @Column
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column
    private LocalDateTime localDateTime;

    @Column
    private Double tip;

    public Receipt() {
    }

    public Receipt(List<Dish> dishList, List<Drink> drinkList, Double totalPrice, Employee employee, Client client, LocalDateTime localDateTime) {

        this.dishList = dishList;
        this.drinkList = drinkList;
        this.totalPrice = totalPrice;
        this.employee = employee;
        this.client = client;
        this.localDateTime = localDateTime;
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

    public Double getTip() {
        return tip;
    }

    public void setTip(Double tip) {
        this.tip = tip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(id, receipt.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
