package pl.connectis.restaurant.RestaurantApp.repositories;



import org.springframework.data.repository.PagingAndSortingRepository;
import pl.connectis.restaurant.RestaurantApp.model.Drink;


public interface DrinkRepository extends PagingAndSortingRepository<Drink, Long> {
}
