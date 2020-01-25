package pl.connectis.restaurant.RestaurantApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.connectis.restaurant.RestaurantApp.model.*;
import pl.connectis.restaurant.RestaurantApp.repositories.ReceiptRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static pl.connectis.restaurant.RestaurantApp.validation.ValidationClass.isNull;

@Service
public class ReceiptService {

    private ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public Long addReceipt(List<Dish> dishList, List<Drink> drinkList, Employee employee, Client client) {
        Double totalPrice = 0.0;

        totalPrice = calculatePrice(dishList, drinkList, totalPrice, client);

        Receipt receipt = new Receipt();
        receipt.setDishList(dishList);
        receipt.setDrinkList(drinkList);
        receipt.setTotalPrice(totalPrice);
        receipt.setClient(client);
        receipt.setEmployee(employee);
        receipt.setLocalDateTime(LocalDateTime.now());

        validateReceipt(receipt);

        receiptRepository.save(receipt);

        return receipt.getId();
    }

    public void updateReceipt(Long id, List<Dish> dishList, List<Drink> drinkList, Employee employee, Client client) {
        Optional<Receipt> receiptOptional = receiptRepository.findById(id);
        Double totalPrice = 0.0;

        totalPrice = calculatePrice(dishList, drinkList, totalPrice, client);

        if (receiptOptional.isPresent()) {
            Receipt receipt = receiptOptional.get();
            receipt.setDishList(dishList);
            receipt.setClient(client);
            receipt.setDrinkList(drinkList);
            receipt.setEmployee(employee);
            receipt.setTotalPrice(totalPrice);
            validateReceipt(receipt);

            receiptRepository.save(receipt);
            return;
        }
        throw new RuntimeException();
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
            throw new RuntimeException();
        }
    }

    private Double calculatePrice(List<Dish> dishList, List<Drink> drinkList, Double totalPrice, Client client) {
        for (Dish dish : dishList) {
            totalPrice = totalPrice + dish.getPrice();
        }

        for (Drink drink : drinkList) {
            totalPrice = totalPrice + drink.getPrice();
        }

        return totalPrice - (totalPrice * (client.getDiscount() / 100));
    }
}
