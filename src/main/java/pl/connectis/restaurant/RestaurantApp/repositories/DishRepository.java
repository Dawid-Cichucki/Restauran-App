package pl.connectis.restaurant.RestaurantApp.repositories;



import org.springframework.data.repository.PagingAndSortingRepository;
import pl.connectis.restaurant.RestaurantApp.model.Dish;


public interface DishRepository extends PagingAndSortingRepository<Dish, Long> {
}
