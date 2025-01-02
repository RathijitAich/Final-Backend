package net.zeonsoftwares.fitness.controller;



import net.zeonsoftwares.fitness.dto.IncludesDto;
import net.zeonsoftwares.fitness.entity.IncludesEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import repository
import net.zeonsoftwares.fitness.repository.IncludeRepository;

import java.util.List;

@RestController
public class IncludeController {

    private final IncludeRepository includeRepository;

    @Autowired
    public IncludeController(IncludeRepository includeRepository) {
        this.includeRepository = includeRepository;
    }

    @GetMapping("/api/include")
    public List<IncludesEntity> getFoodsByDietPlanName(
            @RequestParam(required = false, defaultValue = "") String dietPlanName
    ) {
        System.out.println("Diet Plan Name: " + dietPlanName); // Debugging for the diet plan name
        return includeRepository.findByDietPlan_DietPlanNameContaining(dietPlanName);
        //this will return all the food items that are included in the diet plan
    }

   
    
}
