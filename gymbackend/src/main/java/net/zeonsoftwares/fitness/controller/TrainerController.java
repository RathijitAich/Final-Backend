package net.zeonsoftwares.fitness.controller;

import net.zeonsoftwares.fitness.dto.DietPlanDto;
import net.zeonsoftwares.fitness.dto.WorkoutPlanDto;
import net.zeonsoftwares.fitness.entity.Consists_of_Entity;
import net.zeonsoftwares.fitness.entity.DietPlanEntity;
import net.zeonsoftwares.fitness.entity.Food;
import net.zeonsoftwares.fitness.entity.IncludesEntity;
import net.zeonsoftwares.fitness.entity.WorkoutPlanEntity;
import net.zeonsoftwares.fitness.entity.WorkoutsEntity;
import net.zeonsoftwares.fitness.repository.Consists_of_Repository;
import net.zeonsoftwares.fitness.repository.DietPlanRepository;
import net.zeonsoftwares.fitness.repository.FoodRepository;
import net.zeonsoftwares.fitness.repository.IncludeRepository;
import net.zeonsoftwares.fitness.repository.TrainerRepository;
import net.zeonsoftwares.fitness.repository.WorkoutPlanRepository;
import net.zeonsoftwares.fitness.repository.WorkoutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.zeonsoftwares.fitness.entity.TrainerEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Autowired
    private WorkoutsRepository workoutsRepository;

    @Autowired
    private Consists_of_Repository consistsOfRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private DietPlanRepository dietPlanRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private IncludeRepository includesRepository;

    @PostMapping("/add_workout_to_plan")
    public ResponseEntity<String> addWorkoutToPlan(
            @RequestParam String workoutPlanName,
            @RequestParam String workoutName) {

        Optional<WorkoutPlanEntity> workoutPlanOpt = workoutPlanRepository.findById(workoutPlanName);
        if (workoutPlanOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Workout plan not found.");
        }

        Optional<WorkoutsEntity> workoutOpt = workoutsRepository.findById(workoutName);
        if (workoutOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Workout not found.");
        }

        Consists_of_Entity consistsOfEntity = new Consists_of_Entity();
        consistsOfEntity.setWorkoutPlan(workoutPlanOpt.get());
        consistsOfEntity.setWorkout(workoutOpt.get());
        consistsOfRepository.save(consistsOfEntity);

        return ResponseEntity.ok("Workout added to the plan successfully.");
    }

    @DeleteMapping("/remove_workout_from_plan")
    public ResponseEntity<String> removeWorkoutFromPlan(
            @RequestParam String workoutPlanName,
            @RequestParam String workoutName) {

        List<Consists_of_Entity> relationships = consistsOfRepository
                .findByWorkoutPlan_WorkoutPlanName(workoutPlanName);
        if (relationships.isEmpty()) {
            return ResponseEntity.badRequest().body("No such relationship exists.");
        }

        Optional<Consists_of_Entity> targetEntity = relationships.stream()
                .filter(relation -> relation.getWorkout().getWorkoutName().equals(workoutName))
                .findFirst();

        if (targetEntity.isEmpty()) {
            return ResponseEntity.badRequest().body("Workout is not part of the given plan.");
        }

        consistsOfRepository.delete(targetEntity.get());
        return ResponseEntity.ok("Workout removed from the plan successfully.");
    }

    @GetMapping("/consists_of")
    public ResponseEntity<List<Consists_of_Entity>> getWorkoutsByPlan(@RequestParam String workoutPlanName) {
        List<Consists_of_Entity> workouts = consistsOfRepository.findByWorkoutPlan_WorkoutPlanName(workoutPlanName);
        return ResponseEntity.ok(workouts);
    }

    // endpoints to get all trainers

    @GetMapping("/all")
    public List<TrainerEntity> getAllTrainers() {
        return trainerRepository.findAll();
    }

    // endpoints to get trainer by id
    @GetMapping("/get/{trainerId}")
    public ResponseEntity<TrainerEntity> getTrainerById(@PathVariable String trainerId) {
        TrainerEntity trainerEntity = trainerRepository.findByTrainerId(trainerId);
        if (trainerEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trainerEntity);
    }

    // add food to diet plan
    @PostMapping("/add-food-to-diet-plan")
    public ResponseEntity<String> addFoodToDietPlan(
            @RequestParam String dietPlanName,
            @RequestParam String foodName) {

        Optional<DietPlanEntity> dietPlanOpt = dietPlanRepository.findById(dietPlanName);
        if (dietPlanOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Diet plan not found.");
        }

        Optional<Food> foodOpt = foodRepository.findById(foodName);
        if (foodOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Food not found.");
        }

        IncludesEntity IncludesEntity = new IncludesEntity();

        IncludesEntity.setDietPlan(dietPlanOpt.get());
        IncludesEntity.setFood(foodOpt.get());
        includesRepository.save(IncludesEntity);

        return ResponseEntity.ok("Food added to the diet plan successfully.");
    }

    // remove food from diet plan
    @DeleteMapping("/remove-food-from-diet-plan")

    public ResponseEntity<String> removeFoodFromDietPlan(
            @RequestParam String dietPlanName,
            @RequestParam String foodName) {

        List<IncludesEntity> relationships = includesRepository.findByDietPlan_DietPlanName(dietPlanName);
        if (relationships.isEmpty()) {
            return ResponseEntity.badRequest().body("No such relationship exists.");
        }

        Optional<IncludesEntity> targetEntity = relationships.stream()
                .filter(relation -> relation.getFood().getFoodName().equals(foodName))
                .findFirst();

        if (targetEntity.isEmpty()) {
            return ResponseEntity.badRequest().body("Food is not part of the given diet plan.");
        }

        includesRepository.delete(targetEntity.get());
        return ResponseEntity.ok("Food removed from the diet plan successfully.");
    }

    
    // workout plan name and trainer id
    @PostMapping("/add-workout-plan")
    public ResponseEntity<String> addWorkoutPlan(@RequestBody WorkoutPlanDto workoutPlanDto) {

        //PRINTING THE WORKOUT PLAN NAME AND TRAINER ID
        System.out.println("Workout Plan Name: " + workoutPlanDto.getWorkoutPlanName());
        System.out.println("Trainer ID: " + workoutPlanDto.getWorkoutPlanTrainerId());
        if (workoutPlanDto.getWorkoutPlanName() == null || workoutPlanDto.getWorkoutPlanTrainerId() == null) {
            return ResponseEntity.badRequest().body("Workout plan name and Trainer ID cannot be null.");
        }

        // Fetch the trainer managing this workout plan
        TrainerEntity trainer = trainerRepository.findByTrainerId(workoutPlanDto.getWorkoutPlanTrainerId());

        if (trainer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Trainer with ID " + workoutPlanDto.getWorkoutPlanTrainerId() + " not found.");
        }

        // Map WorkoutPlanDto to WorkoutPlan
        WorkoutPlanEntity workoutPlan = new WorkoutPlanEntity(
                workoutPlanDto.getWorkoutPlanName(),
                trainer);

        try {
            // Save workout plan to the database
            workoutPlanRepository.save(workoutPlan);
            return ResponseEntity.status(HttpStatus.CREATED).body("Workout plan added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add workout plan. Error: " + e.getMessage());
        }
    }



    // Diet plan and trainer
    @PostMapping("/add-diet-plan")
    public ResponseEntity<String> addDietPlan(@RequestBody DietPlanDto dietPlanDto) {
        if (dietPlanDto.getDietPlanName() == null || dietPlanDto.getDietPlanTrainerId() == null) {
            return ResponseEntity.badRequest().body("Diet plan name and Trainer ID cannot be null.");
        }

        // Fetch the trainer managing this diet plan
        TrainerEntity trainer = trainerRepository.findByTrainerId(dietPlanDto.getDietPlanTrainerId());

        if (trainer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Trainer with ID " + dietPlanDto.getDietPlanTrainerId() + " not found.");
        }

        // Map DietPlanDto to DietPlan
        DietPlanEntity dietPlan = new DietPlanEntity(
                dietPlanDto.getDietPlanName(),
                trainer);

        try {
            // Save diet plan to the database
            dietPlanRepository.save(dietPlan);
            return ResponseEntity.status(HttpStatus.CREATED).body("Diet plan added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add diet plan. Error: " + e.getMessage());
        }
    }

}
