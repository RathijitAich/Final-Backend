package net.zeonsoftwares.fitness.controller;

import net.zeonsoftwares.fitness.dto.FoodDto;
import net.zeonsoftwares.fitness.dto.TrainerDto;
import net.zeonsoftwares.fitness.dto.WorkoutsDto;
import net.zeonsoftwares.fitness.entity.AdminEntity;
import net.zeonsoftwares.fitness.entity.Food;
import net.zeonsoftwares.fitness.entity.TrainerEntity;
import net.zeonsoftwares.fitness.entity.WorkoutsEntity;
import net.zeonsoftwares.fitness.repository.AdminRepository;
import net.zeonsoftwares.fitness.repository.FoodRepository;
import net.zeonsoftwares.fitness.repository.TrainerRepository;
import net.zeonsoftwares.fitness.repository.WorkoutsRepository;


import net.zeonsoftwares.fitness.*;// this imports everything

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private WorkoutsRepository workoutsRepository;

    @Autowired
    private FoodRepository FoodRepository;

    // Add Trainer
    @PostMapping("/add-trainer")
    public ResponseEntity<String> addTrainer(@RequestBody TrainerDto trainerDto) {
        if (trainerDto.getTrainer_id() == null || trainerDto.getTrainer_admin_id() == null) {
            return ResponseEntity.badRequest().body("Trainer ID and Admin ID cannot be null.");
        }

        // Fetch the admin managing this trainer
        AdminEntity admin = adminRepository.findByAdminId(trainerDto.getTrainer_admin_id());

        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Admin with ID " + trainerDto.getTrainer_admin_id() + " not found.");
        }

        // Map TrainerDto to TrainerEntity
        TrainerEntity trainerEntity = new TrainerEntity(
                trainerDto.getTrainer_id(),
                trainerDto.getTrainer_name(),
                trainerDto.getTrainer_email(),
                trainerDto.getTrainer_password(),
                trainerDto.getTrainer_phone(),
                admin
        );

        try {
            // Save trainer to the database
            trainerRepository.save(trainerEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Trainer added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add trainer. Error: " + e.getMessage());
        }
    }

    // Remove Trainer
    @DeleteMapping("/remove-trainer/{trainerId}")
    public ResponseEntity<String> removeTrainer(@PathVariable String trainerId) {
        if (trainerId == null || trainerId.isBlank()) {
            return ResponseEntity.badRequest().body("Trainer ID cannot be null or blank.");
        }

        // Check if the trainer exists
        TrainerEntity trainerEntity = trainerRepository.findByTrainerId(trainerId);

        if (trainerEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Trainer with ID " + trainerId + " not found.");
        }

        try {
            // Delete the trainer from the database
            trainerRepository.delete(trainerEntity);
            return ResponseEntity.ok("Trainer removed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to remove trainer. Error: " + e.getMessage());
        }
    }

    // Add Workout
    @PostMapping("/add-workout")
    public ResponseEntity<String> addWorkout(@RequestBody WorkoutsDto workoutsDto) {
        if (workoutsDto.getWorkoutName() == null || workoutsDto.getWorkout_admin_id() == null) {
            return ResponseEntity.badRequest().body("Workout name and Admin ID cannot be null.");
        }

        // Fetch the admin managing this workout
        AdminEntity admin = adminRepository.findByAdminId(workoutsDto.getWorkout_admin_id());

        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Admin with ID " + workoutsDto.getWorkout_admin_id() + " not found.");
        }

        // Map WorkoutsDto to WorkoutsEntity
        WorkoutsEntity workoutsEntity = new WorkoutsEntity(
                workoutsDto.getWorkoutName(),
                workoutsDto.getWorkoutType(),
                workoutsDto.getTargetMuscle(),
                workoutsDto.getEquipmentRequired(),
                workoutsDto.getWorkoutDescription(),
                admin
        );

        try {
            // Save workout to the database
            workoutsRepository.save(workoutsEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Workout added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add workout. Error: " + e.getMessage());
        }
    }

    // Remove Workout
    @DeleteMapping("/remove-workout/{workoutName}")
    public ResponseEntity<String> removeWorkout(@PathVariable String workoutName) {
        if (workoutName == null || workoutName.isBlank()) {
            return ResponseEntity.badRequest().body("Workout name cannot be null or blank.");
        }

        // Check if the workout exists
        WorkoutsEntity workoutsEntity = workoutsRepository.findById(workoutName).orElse(null);

        if (workoutsEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Workout with name " + workoutName + " not found.");
        }

        try {
            // Delete the workout from the database
            workoutsRepository.delete(workoutsEntity);
            return ResponseEntity.ok("Workout removed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to remove workout. Error: " + e.getMessage());
        }
    }


    //add food
    @PostMapping("/add-food")
    public ResponseEntity<String> addFood(@RequestBody FoodDto foodDto) {
        if (foodDto.getFoodName() == null || foodDto.getFood_admin_Id() == null) {
            return ResponseEntity.badRequest().body("Food name and Admin ID cannot be null.");
        }

        // Fetch the admin managing this food
        AdminEntity admin = adminRepository.findByAdminId(foodDto.getFood_admin_Id());

        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Admin with ID " + foodDto.getFood_admin_Id() + " not found.");
        }

        // Map FoodDto to Food
        Food food = new Food(
                foodDto.getFoodName(),
                foodDto.getCalorie(),
                foodDto.getProtein(),
                foodDto.getFat(),
                foodDto.getCarbohydrate(),
                admin
        );

        try {
            // Save food to the database
            //use an instance of the food repository to save the food
            FoodRepository.save(food);
            return ResponseEntity.status(HttpStatus.CREATED).body("Food added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add food. Error: " + e.getMessage());
        }
    }

    // Remove Food
    @DeleteMapping("/remove-food/{foodName}")

    public ResponseEntity<String> removeFood(@PathVariable String foodName) {
        if (foodName == null || foodName.isBlank()) {
            return ResponseEntity.badRequest().body("Food name cannot be null or blank.");
        }

        // Check if the food exists
        Food food = FoodRepository.findByFoodName(foodName);

        if (food == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Food with name " + foodName + " not found.");
        }

        try {
            // Delete the food from the database
            FoodRepository.delete(food);
            return ResponseEntity.ok("Food removed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to remove food. Error: " + e.getMessage());
        }
    }
}
