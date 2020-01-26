package pl.connectis.restaurant.RestaurantApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.RestaurantApp.dto.ReceiptDTO;
import pl.connectis.restaurant.RestaurantApp.exception.NotFoundException;
import pl.connectis.restaurant.RestaurantApp.model.*;
import pl.connectis.restaurant.RestaurantApp.service.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    ReceiptService receiptService;
    DishService dishService;
    DrinkService drinkService;
    EmployeeService employeeService;
    ClientService clientService;

    @Autowired
    public ReceiptController(ReceiptService receiptService, DishService dishService, DrinkService drinkService, EmployeeService employeeService, ClientService clientService) {
        this.receiptService = receiptService;
        this.dishService = dishService;
        this.drinkService = drinkService;
        this.employeeService = employeeService;
        this.clientService = clientService;
    }


    @PostMapping("/add")
    public Long addReceipt(@RequestBody ReceiptDTO receiptDTO) {

        Receipt receipt = receiptService.toModel(receiptDTO);
        Long getId = receiptService.addReceipt(
                receipt.getDishList(),
                receipt.getDrinkList(),
                receipt.getEmployee(),
                receipt.getClient()
        );
        return getId;
    }

    @PostMapping("/open")
    public Long openBill(@RequestParam("employeeId") Long employeeId, @RequestParam("clientId") Long clientId) {
        Receipt receipt = new Receipt();
        Optional<Employee> employeeOptional = employeeService.getEmployee(employeeId);
        Optional<Client> clientOptional = clientService.getClient(clientId);

        if (clientOptional.isPresent() && employeeOptional.isPresent()) {
            return receiptService.openBill(employeeOptional.get(), clientOptional.get());
        }
        throw new NotFoundException();
    }


    @PostMapping("/addDish")
    public String addDish(@RequestParam("receiptId") Long id, @RequestParam("dishId") Long dishId) {
        Optional<Dish> dishOptional = dishService.getDish(dishId);
        if (dishOptional.isPresent()) {
            receiptService.addDish(id, dishOptional.get());
            return "Dish added";
        }

        throw new NotFoundException();
    }

    @PostMapping("addDrink")
    public String addDrink(@RequestParam("receiptId") Long id, @RequestParam("drinkId") Long drinkId) {
        Optional<Drink> drinkOptional = drinkService.getDrink(drinkId);
        if (drinkOptional.isPresent()) {
            receiptService.addDrink(id, drinkOptional.get());
            return "Drink added";
        }
        throw new NotFoundException();
    }

    @GetMapping("/{id}")
    public ReceiptDTO getReceipt(@PathVariable("id") Long id) {
        Optional<Receipt> receiptOptional = receiptService.getReceipt(id);
        if (receiptOptional.isPresent()) {
            Receipt receipt = receiptOptional.get();
            ReceiptDTO receiptDTO = receiptService.toDTO(receipt);
            receiptDTO.setId(receipt.getId());
            receiptDTO.setLocalDateTime(LocalDateTime.now());
            receiptDTO.setTotalPrice(receipt.getTotalPrice());
            receiptDTO.setTip(receiptDTO.getTotalPrice() / 100);
            return receiptDTO;
        }
        throw new NotFoundException();
    }

    @PutMapping("/update/{id}")
    public String updateReceipt(@PathVariable("id") Long id, @RequestBody ReceiptDTO receiptDTO) {
        Receipt receipt = receiptService.toModel(receiptDTO);

        receiptService.updateReceipt(
                id,
                receipt.getDishList(),
                receipt.getDrinkList(),
                receipt.getEmployee(),
                receipt.getClient()
        );
        return "receipt updated";
    }

    @DeleteMapping("/{id}")
    public String deleteReceipt(@PathVariable("id") Long id) {
        Optional<Receipt> receiptOptional = receiptService.getReceipt(id);
        if (receiptOptional.isPresent()) {
            receiptService.deleteReceipt(id);
            return "removed receipt";
        }
        throw new NotFoundException();
    }
}
