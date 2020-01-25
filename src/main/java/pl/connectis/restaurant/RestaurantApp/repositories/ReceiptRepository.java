package pl.connectis.restaurant.RestaurantApp.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.connectis.restaurant.RestaurantApp.model.Receipt;


public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
}
