package pl.connectis.restaurant.RestaurantApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.RestaurantApp.dto.DishDTO;

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
    public Long addDish(@RequestBody DishDTO dishDTO){
        Long dishId =dishService.addDish(
                dishDTO.getName(),
                dishDTO.getDescription(),
                dishDTO.getPrice(),
                dishDTO.getAvailable()
        );
        return dishId;
    }

    @GetMapping(path = "/get/{id}")
    public DishDTO getDish(@PathVariable("id") Long id){
        Optional<Dish> dishOptional = dishService.getDish(id);

        return new DishDTO(dishOptional.get());
    }

    @GetMapping(path = "/get/menu")
    public List<Dish> getAllDishes(){
      return dishService.getAllDishes();
    }

    @DeleteMapping(path = "/delete/{id}")
    public void removeDish(@PathVariable("id") Long id){
        dishService.removeDish(id);
    }

    @PutMapping(path = "/update/{id}/{price}")
    public String updateDish(@PathVariable("id") Long id, @PathVariable("price") Double price){
        dishService.updateDishPrice(id, price);
        return "Dish updated";
    }
}
