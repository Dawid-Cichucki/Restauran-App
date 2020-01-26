package pl.connectis.restaurant.RestaurantApp.dto;

import pl.connectis.restaurant.RestaurantApp.model.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ReceiptDTO {


    private Long id;
    private List<DishDTO> dishDTOList = new ArrayList<>();
    private List<DrinkDTO> drinkDTOList = new ArrayList<>();
    private Double totalPrice;
    private EmployeeDTO employeeDTO;
    private ClientDTO clientDTO;
    private LocalDateTime localDateTime;
    private Double tip;
    public ReceiptDTO() {
    }

    public ReceiptDTO(Long id,
                      List<DishDTO> dishDTOList,
                      List<DrinkDTO> drinkDTOList,
                      EmployeeDTO employeeDTO,
                      ClientDTO clientDTO) {
        this.id = id;
        this.dishDTOList = dishDTOList;
        this.drinkDTOList = drinkDTOList;
        this.employeeDTO = employeeDTO;
        this.clientDTO = clientDTO;
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

    public List<DishDTO> getDishDTOList() {
        return dishDTOList;
    }

    public void setDishDTOList(List<DishDTO> dishDTOList) {
        this.dishDTOList = dishDTOList;
    }

    public List<DrinkDTO> getDrinkDTOList() {
        return drinkDTOList;
    }

    public void setDrinkDTOList(List<DrinkDTO> drinkDTOList) {
        this.drinkDTOList = drinkDTOList;
    }

    public Double getTip() {
        return tip;
    }

    public void setTip(Double tip) {
        this.tip = tip;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }
}
