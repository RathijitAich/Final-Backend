////package net.zeonsoftwares.fitness.controller;
////
////import net.zeonsoftwares.fitness.dto.WorkoutPlanDto;
////import net.zeonsoftwares.fitness.entity.WorkoutPlanEntity;
////import net.zeonsoftwares.fitness.repository.WorkoutPlanRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RestController;
////
////import java.util.List;
////import java.util.stream.Collectors;
////
////@RestController
////@RequestMapping("/api/workouts")
////public class WorkoutPlanController {
////
////    @Autowired
////    private WorkoutPlanRepository workoutPlanRepository;
////
////    @GetMapping
////    public List<WorkoutPlanDto> getAllWorkouts() {
////        return workoutPlanRepository.findAll().stream()
////                .map(workoutPlan -> new WorkoutPlanDto(workoutPlan.getWorkoutPlanName()))
////                .collect(Collectors.toList());
////    }
////}
//package net.zeonsoftwares.fitness.controller;
//
//import net.zeonsoftwares.fitness.dto.WorkoutPlanDto;
//import net.zeonsoftwares.fitness.entity.WorkoutPlanEntity;
//import net.zeonsoftwares.fitness.repository.WorkoutPlanRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/workoutPlans")
//public class WorkoutPlanController {
//
//    @Autowired
//    private WorkoutPlanRepository workoutPlanRepository;
//
//    // Retrieve all workout plans
//    @GetMapping
//    public List<WorkoutPlanDto> getAllWorkoutPlans() {
//        return workoutPlanRepository.findAll().stream()
//                .map(workoutPlan -> new WorkoutPlanDto(workoutPlan.getWorkoutPlanName()))
//                .collect(Collectors.toList());
//    }
//
//    // Retrieve a specific workout plan by name
//    @GetMapping("/{name}")
//    public WorkoutPlanDto getWorkoutPlanByName(@PathVariable String name) {
//        Optional<WorkoutPlanEntity> workoutPlan = workoutPlanRepository.findById(name);
//        if (workoutPlan.isPresent()) {
//            return new WorkoutPlanDto(workoutPlan.get().getWorkoutPlanName());
//        } else {
//            throw new RuntimeException("Workout Plan with name " + name + " not found");
//        }
//    }
//
//    // Create a new workout plan
//    @PostMapping
//    public WorkoutPlanDto createWorkoutPlan(@RequestBody WorkoutPlanDto workoutPlanDto) {
//        WorkoutPlanEntity workoutPlanEntity = new WorkoutPlanEntity();
//        workoutPlanEntity.setWorkoutPlanName(workoutPlanDto.getWorkoutPlanName());
//        workoutPlanRepository.save(workoutPlanEntity);
//        return workoutPlanDto;
//    }
//
//    // Update an existing workout plan
//    @PutMapping("/{name}")
//    public WorkoutPlanDto updateWorkoutPlan(@PathVariable String name, @RequestBody WorkoutPlanDto workoutPlanDto) {
//        Optional<WorkoutPlanEntity> existingPlan = workoutPlanRepository.findById(name);
//        if (existingPlan.isPresent()) {
//            WorkoutPlanEntity workoutPlanEntity = existingPlan.get();
//            workoutPlanEntity.setWorkoutPlanName(workoutPlanDto.getWorkoutPlanName());
//            workoutPlanRepository.save(workoutPlanEntity);
//            return workoutPlanDto;
//        } else {
//            throw new RuntimeException("Workout Plan with name " + name + " not found");
//        }
//    }
//
//    // Delete a workout plan by name
//    @DeleteMapping("/{name}")
//    public String deleteWorkoutPlan(@PathVariable String name) {
//        if (workoutPlanRepository.existsById(name)) {
//            workoutPlanRepository.deleteById(name);
//            return "Workout Plan with name " + name + " has been deleted";
//        } else {
//            throw new RuntimeException("Workout Plan with name " + name + " not found");
//        }
//    }
//}

