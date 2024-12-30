//package net.zeonsoftwares.fitness.controller;
//
//import net.zeonsoftwares.fitness.dto.WorkoutsDto;
//import net.zeonsoftwares.fitness.entity.WorkoutsEntity;
//import net.zeonsoftwares.fitness.repository.WorkoutsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping( "/api/workouts")
//public class WorkoutsController {
//
//    @Autowired
//    private WorkoutsRepository workoutsRepository;
//
//    @GetMapping
//    public List<WorkoutsDto> getAllWorkouts() {
//        return workoutsRepository.findAll().stream()
//                .map(entity -> new WorkoutsDto(
//                        entity.getWorkoutName(),
//                        entity.getWorkoutType(),
//                        entity.getTargetMuscle(),
//                        entity.getEquipmentRequired(),
//                        entity.getWorkoutDescription()
//                ))
//                .collect(Collectors.toList());
//    }
//}

package net.zeonsoftwares.fitness.controller;

import net.zeonsoftwares.fitness.dto.WorkoutsDto;
import net.zeonsoftwares.fitness.entity.WorkoutsEntity;
import net.zeonsoftwares.fitness.repository.WorkoutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutsController {

    @Autowired
    private WorkoutsRepository workoutsRepository;

    @GetMapping
    public List<WorkoutsDto> getFilteredWorkouts(
            @RequestParam(required = false) List<String> targetMuscles,
            @RequestParam(required = false) String workoutType,
            @RequestParam(required = false) String equipmentRequired) {

        List<WorkoutsEntity> workouts = workoutsRepository.findAll();

        // Apply filtering
        if (targetMuscles != null && !targetMuscles.isEmpty()) {
            workouts = workouts.stream()
                    .filter(workout -> targetMuscles.contains(workout.getTargetMuscle()))
                    .collect(Collectors.toList());
        }

        if (workoutType != null) {
            workouts = workouts.stream()
                    .filter(workout -> workout.getWorkoutType().equalsIgnoreCase(workoutType))
                    .collect(Collectors.toList());
        }

        if (equipmentRequired != null) {
            workouts = workouts.stream()
                    .filter(workout -> workout.getEquipmentRequired().equalsIgnoreCase(equipmentRequired))
                    .collect(Collectors.toList());
        }

        // Map to DTO
        return workouts.stream()
                .map(entity -> new WorkoutsDto(
                        entity.getWorkoutName(),
                        entity.getWorkoutType(),
                        entity.getTargetMuscle(),
                        entity.getEquipmentRequired(),
                        entity.getWorkoutDescription(),
                        entity.getAdmin() != null ? entity.getAdmin().getAdminId() : null  // Handle null admin
                ))
                .collect(Collectors.toList());
    }


    //get workout infromation by workout name

        @GetMapping("/workout")
        public WorkoutsDto getWorkoutByName(@RequestParam String workoutName) {
            WorkoutsEntity workout = workoutsRepository.findByWorkoutName(workoutName);
            return new WorkoutsDto(
                    workout.getWorkoutName(),
                    workout.getWorkoutType(),
                    workout.getTargetMuscle(),
                    workout.getEquipmentRequired(),
                    workout.getWorkoutDescription(),
                    workout.getAdmin() != null ? workout.getAdmin().getAdminId() : null  // Handle null admin
            );
        }
}
