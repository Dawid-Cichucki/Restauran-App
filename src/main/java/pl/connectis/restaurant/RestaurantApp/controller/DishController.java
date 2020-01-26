package pl.connectis.restaurant.RestaurantApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.RestaurantApp.dto.DishDTO;
import pl.connectis.restaurant.RestaurantApp.exception.NotFoundException;
import pl.connectis.restaurant.RestaurantApp.model.Dish;
import pl.connectis.restaurant.RestaurantApp.service.DishService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class DishController {

    private DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping(path = "/add")
    public Long addDish(@RequestBody DishDTO dishDTO) {
        Long dishId = dishService.addDish(
                dishDTO.getName(),
                dishDTO.getDescription(),
                dishDTO.getPrice(),
                dishDTO.getAvailable()
        );
        return dishId;
    }

    @GetMapping(path = "/{id}")
    public DishDTO getDish(@PathVariable("id") Long id) {
        Optional<Dish> dishOptional = dishService.getDish(id);
        if (dishOptional.isPresent()) {
            return new DishDTO(dishOptional.get());
        }
        throw new NotFoundException();
    }

    @GetMapping(path = "/menu")
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @DeleteMapping(path = "/{id}")
    public void removeDish(@PathVariable("id") Long id) {
        Optional<Dish> dishOptional = dishService.getDish(id);
        if (dishOptional.isPresent()) {
            dishService.removeDish(id);
        }
        throw new NotFoundException();
    }

    @PostMapping(path = "/update/{id}/{price}")
    public String updatePrice(@PathVariable("id") Long id, @PathVariable("price") Double price) {
        Optional<Dish> dishOptional = dishService.getDish(id);

        if (dishOptional.isPresent()) {
            dishService.updateDishPrice(id, price);
            return "Dish updated";
        }
        throw new NotFoundException();
    }

    @PutMapping(path = "/update/{id}")
    public String updateDish(@PathVariable("id") Long id, @RequestBody DishDTO dishDTO) {
        Optional<Dish> dishOptional = dishService.getDish(id);

        if (dishOptional.isPresent()) {
            dishService.updateDish(id, dishDTO);
            return "Dish updated";
        }
        Long newId = addDish(dishDTO);
        return "New dish created " + newId;
    }
}
