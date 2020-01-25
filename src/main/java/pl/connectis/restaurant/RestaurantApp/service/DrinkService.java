package pl.connectis.restaurant.RestaurantApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.connectis.restaurant.RestaurantApp.model.Drink;
import pl.connectis.restaurant.RestaurantApp.repositories.DrinkRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.connectis.restaurant.RestaurantApp.validation.ValidationClass.isNull;
import static pl.connectis.restaurant.RestaurantApp.validation.ValidationClass.isNullOrEmpty;

@Service
public class DrinkService {

    DrinkRepository drinkRepository;

    @Autowired
    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public Long addDrink(String name, String descryption, Double serving, Double price, Boolean available) {
        Drink drink = new Drink();
        drink.setName(name);
        drink.setDescription(descryption);
        drink.setServing(serving);
        drink.setPrice(price);
        drink.setAvailable(available);

        validateDrink(drink);

        drinkRepository.save(drink);
        return drink.getId();
    }

    public void updateDrinkPrice(Long id, Double price) {
        Optional<Drink> drinkOptional = drinkRepository.findById(id);

        if (drinkOptional.isPresent()) {
            Drink drink = drinkOptional.get();
            drink.setPrice(price);

            validateDrink(drink);

            drinkRepository.save(drink);
            return;
        }
        throw new RuntimeException();
    }

    public void removeDrink(Long id) {
        drinkRepository.deleteById(id);
    }

    public Optional<Drink> getDrink(Long id) {
        return drinkRepository.findById(id);
    }

    public List<Drink> getAllDrinks() {
        List<Drink> drinkList = new ArrayList<>();
        for (Drink drink : drinkRepository.findAll()) {
            if (!drink.getAvailable()) {
                continue;
            }
            drinkList.add(drink);
        }
        return drinkList;
    }

    private void validateDrink(Drink drink) {
        if (isNullOrEmpty(drink.getName()) ||
                isNullOrEmpty(drink.getDescription()) ||
                isNull(drink.getPrice()) ||
                isNull(drink.getAvailable()) ||
                isNull(drink.getServing())) {
            throw new RuntimeException();
        }
    }
}
