// package net.zeonsoftwares.fitness.controller;

// public class DietPlanController {
    
// }


package net.zeonsoftwares.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.zeonsoftwares.fitness.entity.DietPlanEntity;
import net.zeonsoftwares.fitness.entity.Food;
import net.zeonsoftwares.fitness.repository.DietPlanRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diet-plan")
public class DietPlanController {

    @Autowired
    private DietPlanRepository dietPlanRepository;


    //endpoint to get all the diet plans
    @GetMapping("/all")
    public List<DietPlanEntity> getAllDietPlans() {
        return dietPlanRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> acceptDietPlanData(@RequestBody Map<String, Object> formData) {
        // Extract individual fields
        double bmi = Double.parseDouble(formData.get("bmi").toString());
        String dietType = formData.get("dietType").toString();
        List<String> healthConditions = (List<String>) formData.get("healthConditions");
    
        // Initialize the response string
        String response = "No matching condition found";
    
        // Check for specific health condition
        boolean hasDiabetes = healthConditions.contains("Diabetes");

        boolean hasHeartProblem = healthConditions.contains("Heart Problem");

    
        // Build response string based on conditions
        if (bmi < 18.5) {
            if ("veg".equalsIgnoreCase(dietType)) {
                if (hasDiabetes) {
                    response = "<18.5_Veg_Diabetes";
                } else if (hasHeartProblem) {
                    response = "<18.5_Veg_Heart_Problem";
                } else  {
                    response = "<18.5_Veg_No-Condition";
                }
            } else if ("non-veg".equalsIgnoreCase(dietType)) {
                if (hasDiabetes) {
                    response = "<18.5_Non-Veg_Diabetes";
                } else if (hasHeartProblem) {
                    response = "<18.5_Non-Veg_Heart_Problem";
                } else {
                    response = "<18.5_Non-Veg_No-Condition";
                }
            }
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            if ("veg".equalsIgnoreCase(dietType)) {
                if (hasDiabetes) {
                    response = "18.5-24.9_Veg_Diabetes";
                } else if (hasHeartProblem) {
                    response = "18.5-24.9_Veg_Heart_Problem";
                } else {
                    response = "18.5-24.9_Veg_No-Condition";
                }
            } else if ("non-veg".equalsIgnoreCase(dietType)) {
                if (hasDiabetes) {
                    response = "18.5-24.9_Non-Veg_Diabetes";
                } else if (hasHeartProblem) {
                    response = "18.5-24.9_Non-Veg_Heart_Problem";
                } else {
                    response = "18.5-24.9_Non-Veg_No-Condition";
                }
            }
        } else { // BMI >= 25
            if ("veg".equalsIgnoreCase(dietType)) {
                if (hasDiabetes) {
                    response = ">=25_Veg_Diabetes";
                } else if (hasHeartProblem) {
                    response = ">=25_Veg_Heart_Problem";
                } else {
                    response = ">=25_Veg_No-Condition";
                }
            } else if ("non-veg".equalsIgnoreCase(dietType)) {
                if (hasDiabetes) {
                    response = ">=25_Non-Veg_Diabetes";
                } else if (hasHeartProblem) {
                    response = ">=25_Non-Veg_Heart_Problem";
                } else {
                    response = ">=25_Non-Veg_No-Condition";
                }
            }
        }
        
    
        return ResponseEntity.ok(response);
    }
}
