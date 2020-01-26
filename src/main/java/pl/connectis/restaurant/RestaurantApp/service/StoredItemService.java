package pl.connectis.restaurant.RestaurantApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.connectis.restaurant.RestaurantApp.exception.BadRequestException;
import pl.connectis.restaurant.RestaurantApp.model.Dish;
import pl.connectis.restaurant.RestaurantApp.model.StoredItem;
import pl.connectis.restaurant.RestaurantApp.model.UnitsOfMeasure;
import pl.connectis.restaurant.RestaurantApp.repositories.StoredItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.connectis.restaurant.RestaurantApp.validation.ValidationClass.*;

@Service
public class StoredItemService {

    private StoredItemRepository storedItemRepository;

    @Autowired
    public StoredItemService(StoredItemRepository storedItemRepository) {
        this.storedItemRepository = storedItemRepository;
    }

    public Long addItem(String name, Long quantity, UnitsOfMeasure unitsOfMeasure) {
        StoredItem storedItem = new StoredItem();
        storedItem.setName(name);
        storedItem.setQuantity(quantity);
        storedItem.setUnitOfMeasure(unitsOfMeasure);

        validateItem(storedItem);

        storedItemRepository.save(storedItem);

        return storedItem.getId();
    }

    public void updateItem(Long id, Long quantity) {
        Optional<StoredItem> storedItemOptional = storedItemRepository.findById(id);
        if (storedItemOptional.isPresent()) {
            StoredItem storedItem = storedItemOptional.get();
            storedItem.setQuantity(quantity);

            validateItem(storedItem);

            storedItemRepository.save(storedItem);
            return;
        }

    }

    public void removeItem(Long id) {
        storedItemRepository.deleteById(id);
    }

    public Optional<StoredItem> getItem(Long id) {
        return storedItemRepository.findById(id);
    }

    public List<StoredItem> getAllItems() {
        List<StoredItem> itemList = new ArrayList<>();
        for (StoredItem storedItem : storedItemRepository.findAll()) {
            itemList.add(storedItem);
        }
        return itemList;
    }

    private void validateItem(StoredItem storedItem) {
        if (isNullOrEmpty(storedItem.getName()) ||
                isNull(storedItem.getQuantity()) ||
                isNull(storedItem.getUnitOfMeasure())) {
            throw new BadRequestException();
        }
    }
}
