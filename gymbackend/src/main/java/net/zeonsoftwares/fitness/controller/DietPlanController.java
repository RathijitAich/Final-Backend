// package net.zeonsoftwares.fitness.controller;

// public class DietPlanController {
    
// }


package net.zeonsoftwares.fitness.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diet-plan")
public class DietPlanController {

    @PostMapping
    public ResponseEntity<String> acceptDietPlanData(@RequestBody Map<String, Object> formData) {
        // Extract individual fields
        double bmi = Double.parseDouble(formData.get("bmi").toString());
        String dietType = formData.get("dietType").toString();
        List<String> healthConditions = (List<String>) formData.get("healthConditions");
    
        // Initialize the response string
        String response;
    
        // Check for specific health condition
        boolean hasDiabetes = healthConditions.contains("Diabetes");
    
        // Build response string based on conditions
        if (bmi < 18.5) {
            if ("veg".equalsIgnoreCase(dietType) && hasDiabetes) {
                response = "<18.5_Veg_Diabetes";
            } else if ("non-veg".equalsIgnoreCase(dietType) && hasDiabetes) {
                response = "<18.5_Non-Veg_Diabetes";
            } else {
                response = "<18.5_" + dietType + "_No-Diabetes";
            }
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            if ("veg".equalsIgnoreCase(dietType) && hasDiabetes) {
                response = "18.5-24.9_Veg_Diabetes";
            } else {
                response = "18.5-24.9_" + dietType + "_No-Diabetes";
            }
        } else { // BMI >= 25
            if ("veg".equalsIgnoreCase(dietType) && hasDiabetes) {
                response = ">=25_Veg_Diabetes";
            } else {
                response = ">=25_" + dietType + "_No-Diabetes";
            }
        }
    
        // Return the response
        return ResponseEntity.ok(response);
    }
}
