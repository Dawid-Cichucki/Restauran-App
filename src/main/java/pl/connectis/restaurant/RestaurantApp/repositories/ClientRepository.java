package pl.connectis.restaurant.RestaurantApp.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.connectis.restaurant.RestaurantApp.model.Client;


public interface ClientRepository extends CrudRepository<Client, Long> {
}
