package pl.connectis.restaurant.RestaurantApp.controller;

import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.RestaurantApp.dto.StoredItemDTO;
import pl.connectis.restaurant.RestaurantApp.model.StoredItem;
import pl.connectis.restaurant.RestaurantApp.service.StoredItemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/storage")
public class StoredItemController {

    private StoredItemService storedItemService;

    public StoredItemController(StoredItemService storedItemService) {
        this.storedItemService = storedItemService;
    }

    @PostMapping("/add")
    public Long addItem(@RequestBody StoredItemDTO storedItemDTO ){
        Long getId = storedItemService.addItem(
                storedItemDTO.getName(),
                storedItemDTO.getQuantity(),
                storedItemDTO.getUnitOfMeasure()
        );
        return getId;
    }

    @GetMapping("/getAll")
    public List<StoredItem> getAllItems(){
       return storedItemService.getAllItems();
    }

    @GetMapping("/get/{id}")
    public StoredItemDTO getItem(@PathVariable("id") Long id){
        Optional<StoredItem> storedItemOptional = storedItemService.getItem(id);

        return new StoredItemDTO(storedItemOptional.get());
    }

    @PutMapping("/update/{id}")
    public String updateItem(@PathVariable("id") Long id, @RequestParam Long quantity){
        storedItemService.updateItem(id, quantity);
        return "item updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") Long id){
        storedItemService.removeItem(id);
        return "item removed";
    }
}
