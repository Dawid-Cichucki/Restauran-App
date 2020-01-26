package pl.connectis.restaurant.RestaurantApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.connectis.restaurant.RestaurantApp.dto.DishDTO;
import pl.connectis.restaurant.RestaurantApp.dto.DrinkDTO;
import pl.connectis.restaurant.RestaurantApp.dto.ReceiptDTO;
import pl.connectis.restaurant.RestaurantApp.exception.BadRequestException;
import pl.connectis.restaurant.RestaurantApp.model.*;
import pl.connectis.restaurant.RestaurantApp.repositories.DishRepository;
import pl.connectis.restaurant.RestaurantApp.repositories.DrinkRepository;
import pl.connectis.restaurant.RestaurantApp.repositories.ReceiptRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.connectis.restaurant.RestaurantApp.validation.ValidationClass.isNull;

@Service
public class ReceiptService {

    private ReceiptRepository receiptRepository;
    private DishRepository dishRepository;
    private DrinkRepository drinkRepository;
    private EmployeeService employeeService;
    private ClientService clientService;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository,
                          DishRepository dishRepository,
                          DrinkRepository drinkRepository,
                          EmployeeService employeeService,
                          ClientService clientService) {
        this.receiptRepository = receiptRepository;
        this.dishRepository = dishRepository;
        this.drinkRepository = drinkRepository;
        this.employeeService = employeeService;
        this.clientService = clientService;
    }

    public Long addReceipt(List<Dish> dishList, List<Drink> drinkList, Employee employee, Client client) {
        Double totalPrice;

        totalPrice = calculatePrice(dishList, drinkList, client);

        Receipt receipt = new Receipt();
        receipt.setDishList(dishList);
        receipt.setDrinkList(drinkList);
        receipt.setTotalPrice(totalPrice);
        receipt.setClient(client);
        receipt.setEmployee(employee);
        receipt.setLocalDateTime(LocalDateTime.now());
        receipt.setTip(receipt.getTotalPrice() / 100);

        validateReceipt(receipt);

        receiptRepository.save(receipt);

        return receipt.getId();
    }

    public Long openBill(Employee employee, Client client){
        Receipt receipt = new Receipt();
        receipt.setEmployee(employee);
        receipt.setClient(client);
        receipt.setLocalDateTime(LocalDateTime.now());
        receipt.setTotalPrice(0.0);

        receiptRepository.save(receipt);
        return receipt.getId();
    }

    public void updateReceipt(Long id, List<Dish> dishList, List<Drink> drinkList, Employee employee, Client client) {
        Optional<Receipt> receiptOptional = receiptRepository.findById(id);
        Double totalPrice;

        totalPrice = calculatePrice(dishList, drinkList, client);

        if (receiptOptional.isPresent()) {
            Receipt receipt = receiptOptional.get();
            receipt.setDishList(dishList);
            receipt.setClient(client);
            receipt.setDrinkList(drinkList);
            receipt.setEmployee(employee);
            receipt.setTotalPrice(totalPrice);
            receipt.setTip(receipt.getTotalPrice() / 100);
            validateReceipt(receipt);

            receiptRepository.save(receipt);
            return;
        }
    }

    public void addDish(Long id, Dish dish) {
        Optional<Receipt> receiptOptional = receiptRepository.findById(id);
        if (receiptOptional.isPresent()) {
            Receipt receipt = receiptOptional.get();
            receipt.getDishList().add(dish);
            receipt.setTotalPrice(calculatePrice(receipt.getDishList(), receipt.getDrinkList(), receipt.getClient()));
            receipt.setTip(receipt.getTotalPrice() / 100);
            validateReceipt(receipt);

            receiptRepository.save(receipt);
            return;
        }
    }

    public void addDrink(Long id, Drink drink){
        Optional<Receipt> receiptOptional = receiptRepository.findById(id);
        if(receiptOptional.isPresent()){
            Receipt receipt = receiptOptional.get();
            receipt.getDrinkList().add(drink);
            receipt.setTotalPrice(calculatePrice(receipt.getDishList(), receipt.getDrinkList(), receipt.getClient()));
            receipt.setTip(receipt.getTotalPrice() / 100);
            validateReceipt(receipt);

            receiptRepository.save(receipt);
            return;
        }
    }

    public void deleteReceipt(Long id) {
        receiptRepository.deleteById(id);
    }

    public Optional<Receipt> getReceipt(Long id) {
        return receiptRepository.findById(id);
    }

    private void validateReceipt(Receipt receipt) {
        if (isNull(receipt.getClient()) ||
                isNull(receipt.getLocalDateTime()) ||
                isNull(receipt.getEmployee()) ||
                receipt.getTotalPrice() < 0) {
            throw new BadRequestException();
        }
    }

    private Double calculatePrice(List<Dish> dishList, List<Drink> drinkList, Client client) {
        Double totalPrice = 0.0;
        for (Dish dish : dishList) {
            totalPrice = totalPrice + dish.getPrice();
        }

        for (Drink drink : drinkList) {
            totalPrice = totalPrice + drink.getPrice();
        }

        return totalPrice - (totalPrice * (client.getDiscount() / 100));
    }

    public Receipt toModel(ReceiptDTO receiptDTO) {
        Receipt receipt = new Receipt();
        receipt.setEmployee(employeeService.toModel(receiptDTO.getEmployeeDTO()));
        receipt.setClient(clientService.toModel(receiptDTO.getClientDTO()));

        List<DishDTO> dtos = receiptDTO.getDishDTOList();

        List<Dish> dishList = new ArrayList<>();
        List<Drink> drinkList = new ArrayList<>();

        for (DishDTO dishDTO : receiptDTO.getDishDTOList()) {
            dishList.add(dishRepository.findById(dishDTO.getId()).get());
        }

        for (DrinkDTO drinkDTO : receiptDTO.getDrinkDTOList()) {
            drinkList.add(drinkRepository.findById(drinkDTO.getId()).get());
        }

        receipt.setDishList(dishList);
        receipt.setDrinkList(drinkList);

        return receipt;
    }

    public ReceiptDTO toDTO(Receipt receipt) {
        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setEmployeeDTO(employeeService.toDTO(receipt.getEmployee()));
        receiptDTO.setClientDTO(clientService.toDTO(receipt.getClient()));

        List<DishDTO> dishDTOList = new ArrayList<>();
        List<DrinkDTO> drinkDTOList = new ArrayList<>();

        for (Dish dish : receipt.getDishList()) {
            dishDTOList.add(new DishDTO(dish));
        }

        for (Drink drink : receipt.getDrinkList()) {
            drinkDTOList.add(new DrinkDTO(drink));
        }

        receiptDTO.setDishDTOList(dishDTOList);
        receiptDTO.setDrinkDTOList(drinkDTOList);

        return receiptDTO;
    }
}
