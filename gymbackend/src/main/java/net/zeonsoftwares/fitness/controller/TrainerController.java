package net.zeonsoftwares.fitness.controller;

import net.zeonsoftwares.fitness.entity.Consists_of_Entity;
import net.zeonsoftwares.fitness.entity.WorkoutPlanEntity;
import net.zeonsoftwares.fitness.entity.WorkoutsEntity;
import net.zeonsoftwares.fitness.repository.Consists_of_Repository;
import net.zeonsoftwares.fitness.repository.WorkoutPlanRepository;
import net.zeonsoftwares.fitness.repository.WorkoutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        List<Consists_of_Entity> relationships = consistsOfRepository.findByWorkoutPlan_WorkoutPlanName(workoutPlanName);
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
}
