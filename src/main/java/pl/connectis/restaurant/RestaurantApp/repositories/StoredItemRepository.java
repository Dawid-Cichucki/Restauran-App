package pl.connectis.restaurant.RestaurantApp.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.connectis.restaurant.RestaurantApp.model.StoredItem;


public interface StoredItemRepository extends CrudRepository<StoredItem, Long> {
}
