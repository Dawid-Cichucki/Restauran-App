package pl.connectis.restaurant.RestaurantApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.RestaurantApp.dto.ReceiptDTO;
import pl.connectis.restaurant.RestaurantApp.service.ReceiptService;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }


    @PostMapping("/add")
    public Long addReceipt(@RequestBody ReceiptDTO receiptDTO){
        Long getId = receiptService.addReceipt(
                receiptDTO.getDishList(),
                receiptDTO.getDrinkList(),
                receiptDTO.getEmployee(),
                receiptDTO.getClient()
        );
        return getId;
    }

    @GetMapping("/get/{id}")
    public ReceiptDTO getReceipt(@PathVariable("id") Long id){
        return new ReceiptDTO(receiptService.getReceipt(id).get());
    }

    @PutMapping("/update/{id}")
    public String updateReceipt(@PathVariable("id") Long id, @RequestBody ReceiptDTO receiptDTO){
        receiptService.updateReceipt(
                id,
                receiptDTO.getDishList(),
                receiptDTO.getDrinkList(),
                receiptDTO.getEmployee(),
                receiptDTO.getClient()
        );
        return "receipt updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReceipt(@PathVariable("id") Long id){
        receiptService.deleteReceipt(id);
        return "removed receipt";
    }
}
