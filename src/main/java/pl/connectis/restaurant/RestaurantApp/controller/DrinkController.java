package pl.connectis.restaurant.RestaurantApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.RestaurantApp.dto.DrinkDTO;
import pl.connectis.restaurant.RestaurantApp.exception.NotFoundException;
import pl.connectis.restaurant.RestaurantApp.model.Drink;
import pl.connectis.restaurant.RestaurantApp.service.DrinkService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drink")
public class DrinkController {

    private DrinkService drinkService;

    @Autowired
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @PostMapping("/add")
    public Long addDrink(@RequestBody DrinkDTO drinkDTO) {
        Long getId = drinkService.addDrink(
                drinkDTO.getName(),
                drinkDTO.getDescription(),
                drinkDTO.getServing(),
                drinkDTO.getPrice(),
                drinkDTO.getAvailable()
        );
        return getId;
    }

    @GetMapping("/{id}")
    public DrinkDTO getDrink(@PathVariable("id") Long id) {
        Optional<Drink> drinkOptional = drinkService.getDrink(id);
        if (drinkOptional.isPresent()) {
            return new DrinkDTO(drinkOptional.get());
        }
        throw new NotFoundException();
    }

    @GetMapping("/allDrinks")
    public List<Drink> getAllDrinks() {
        return drinkService.getAllDrinks();
    }

    @PostMapping("/update/{id}")
    public String updateDrink(@PathVariable("id") Long id, @RequestParam Double price) {
        Optional<Drink> drinkOptional = drinkService.getDrink(id);
        if (drinkOptional.isPresent()) {
            drinkService.updateDrinkPrice(id, price);
            return "updated drink";
        }
        throw new NotFoundException();
    }

    @DeleteMapping("/{id}")
    public String deleteDrink(@PathVariable("id") Long id) {
        Optional<Drink> drinkOptional = drinkService.getDrink(id);
        if (drinkOptional.isPresent()) {
            drinkService.removeDrink(id);
            return "Drink removed";
        }
        throw new NotFoundException();
    }

}
