package net.zeonsoftwares.fitness.controller;

import net.zeonsoftwares.fitness.dto.Consists_of_Dto;
import net.zeonsoftwares.fitness.services.Consists_of_Services;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Consists_of_Controller {

    private final Consists_of_Services consists_of_service;

    public Consists_of_Controller(Consists_of_Services consists_of_service) {
        this.consists_of_service = consists_of_service;
    }

    @GetMapping("/api/consists_of")
    public List<Consists_of_Dto> getPremadePlans(
            @RequestParam(required = false, defaultValue = "") String workoutPlanName
    ) {
        System.out.println("Workout Plan Name: " + workoutPlanName); // Debugging for the workout plan name
        return consists_of_service.getPlansByWorkoutPlanName(workoutPlanName);
    }
}
