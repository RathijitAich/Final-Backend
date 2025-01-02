package net.zeonsoftwares.fitness.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/diet-planner")
public class DietPlannerController {

    @PostMapping("/submit")
    public ResponseEntity<String> submitDietPlan(@RequestBody DietPlanRequest request) {
        // Process the request (e.g., save to database, generate response)
        System.out.println("Received Diet Plan Request: " + request);
        return ResponseEntity.ok("Diet plan submitted successfully!");
    }
}

// Define a DTO to match the form data structure
class DietPlanRequest {
    private String bmi;
    private String goal;
    private String dietType;
    private List<String> healthConditions;

    // Getters and Setters
    public String getBmi() { return bmi; }
    public void setBmi(String bmi) { this.bmi = bmi; }
    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }
    public String getDietType() { return dietType; }
    public void setDietType(String dietType) { this.dietType = dietType; }
    public List<String> getHealthConditions() { return healthConditions; }
    public void setHealthConditions(List<String> healthConditions) { this.healthConditions = healthConditions; }
}

