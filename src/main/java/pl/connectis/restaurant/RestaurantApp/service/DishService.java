package pl.connectis.restaurant.RestaurantApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.connectis.restaurant.RestaurantApp.dto.DishDTO;
import pl.connectis.restaurant.RestaurantApp.exception.BadRequestException;
import pl.connectis.restaurant.RestaurantApp.model.Dish;
import pl.connectis.restaurant.RestaurantApp.repositories.DishRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.connectis.restaurant.RestaurantApp.validation.ValidationClass.isNull;
import static pl.connectis.restaurant.RestaurantApp.validation.ValidationClass.isNullOrEmpty;

@Service
public class DishService {

    DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Long addDish(String name, String description, Double price, Boolean available) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setDescription(description);
        dish.setPrice(price);
        dish.setAvailable(available);

        validateDish(dish);

        dishRepository.save(dish);

        return dish.getId();
    }

    public void updateDishPrice(Long id, Double price) {
        Optional<Dish> dishOptional = dishRepository.findById(id);
        if (dishOptional.isPresent()) {
            Dish dish = dishOptional.get();
            dish.setPrice(price);

            validateDish(dish);

            dishRepository.save(dish);
            return;
        }
        throw new RuntimeException();
    }

    public void removeDish(Long id) {
        dishRepository.deleteById(id);
    }

    public Optional<Dish> getDish(Long id) {
        return dishRepository.findById(id);
    }

    public List<Dish> getAllDishes() {
        List<Dish> dishList = new ArrayList<>();
        for (Dish dish : dishRepository.findAll()) {
            if (dish.getAvailable() == false){
                continue;
            }
            dishList.add(dish);
        }
        return dishList;
    }

    public void updateDish(Long id ,DishDTO dishDTO){
        Optional<Dish> dishOptional = dishRepository.findById(id);
        Dish dish = dishOptional.get();
        dish.setPrice(dishDTO.getPrice());
        dish.setAvailable(dishDTO.getAvailable());
        dish.setDescription(dishDTO.getDescription());
        dish.setName(dishDTO.getName());

        validateDish(dish);

        dishRepository.save(dish);
    }

    private void validateDish(Dish dish) {
        if (isNullOrEmpty(dish.getName()) ||
                isNullOrEmpty(dish.getDescription()) ||
                isNull(dish.getPrice()) ||
                isNull(dish.getAvailable())) {
            throw new BadRequestException();
        }
    }

    public DishDTO toDto(Dish dish){
        DishDTO dishDTO = new DishDTO();
        dishDTO.setName(dish.getName());
        dishDTO.setAvailable(dish.getAvailable());
        dishDTO.setDescription(dish.getDescription());
        dishDTO.setId(dish.getId());
        dishDTO.setPrice(dish.getPrice());

        return dishDTO;
    }

    public Dish toModel(DishDTO dishDTO){
        Dish dish = new Dish();
        dish.setName(dishDTO.getName());
        dish.setAvailable(dishDTO.getAvailable());
        dish.setDescription(dishDTO.getDescription());
        dish.setId(dishDTO.getId());
        dish.setPrice(dishDTO.getPrice());

        return dish;
    }
}
