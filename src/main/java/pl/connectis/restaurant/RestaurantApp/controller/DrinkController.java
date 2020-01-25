package pl.connectis.restaurant.RestaurantApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.RestaurantApp.dto.DrinkDTO;
import pl.connectis.restaurant.RestaurantApp.model.Drink;
import pl.connectis.restaurant.RestaurantApp.service.DrinkService;

import java.util.ArrayList;
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

    @GetMapping("/get/{id}")
    public DrinkDTO getDrink(@PathVariable("id") Long id) {
        Optional<Drink> drinkOptional = drinkService.getDrink(id);

        return new DrinkDTO(drinkOptional.get());
    }

    @GetMapping("allDrinks")
    public List<Drink> getAllDrinks(){
        return drinkService.getAllDrinks();
    }

    @PutMapping("/update/{id}")
    public String updateDrink(@PathVariable("id") Long id,@RequestParam Double price){
        drinkService.updateDrinkPrice(id, price);
        return "updated drink";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDrink(@PathVariable("id") Long id){
        drinkService.removeDrink(id);
        return "Drink removed";
    }

}
