package net.zeonsoftwares.fitness.controller;
import net.zeonsoftwares.fitness.entity.Food;
import net.zeonsoftwares.fitness.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/api/foods")
    public List<Food> getAllFoods() {
        return foodService.getAllFood();
    }
}