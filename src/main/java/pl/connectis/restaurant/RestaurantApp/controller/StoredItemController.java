package pl.connectis.restaurant.RestaurantApp.controller;

import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.RestaurantApp.dto.StoredItemDTO;
import pl.connectis.restaurant.RestaurantApp.exception.NotFoundException;
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
    public Long addItem(@RequestBody StoredItemDTO storedItemDTO) {
        Long getId = storedItemService.addItem(
                storedItemDTO.getName(),
                storedItemDTO.getQuantity(),
                storedItemDTO.getUnitOfMeasure()
        );
        return getId;
    }

    @GetMapping("/All")
    public List<StoredItem> getAllItems() {
        return storedItemService.getAllItems();
    }

    @GetMapping("/{id}")
    public StoredItemDTO getItem(@PathVariable("id") Long id) {
        Optional<StoredItem> storedItemOptional = storedItemService.getItem(id);
        if (storedItemOptional.isPresent()) {
            return new StoredItemDTO(storedItemOptional.get());
        }
        throw new NotFoundException();
    }

    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable("id") Long id, @RequestParam Long quantity) {
        Optional<StoredItem> storedItemOptional = storedItemService.getItem(id);
        if (storedItemOptional.isPresent()) {
            storedItemService.updateItem(id, quantity);
            return "item updated";
        }
        throw new NotFoundException();
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable("id") Long id) {
        Optional<StoredItem> storedItemOptional = storedItemService.getItem(id);
        if (storedItemOptional.isPresent()) {
            storedItemService.removeItem(id);
            return "item removed";
        }
        throw new NotFoundException();
    }
}
