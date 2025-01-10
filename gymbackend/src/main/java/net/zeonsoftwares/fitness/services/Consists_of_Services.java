package net.zeonsoftwares.fitness.services;

import net.zeonsoftwares.fitness.dto.Consists_of_Dto;
import net.zeonsoftwares.fitness.entity.Consists_of_Entity;
import net.zeonsoftwares.fitness.repository.Consists_of_Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Consists_of_Services {

    private final Consists_of_Repository repository;

    public Consists_of_Services(Consists_of_Repository repository) {
        this.repository = repository;
    }

   

     /*
      the query that has been used here is 
     @Query("SELECT c FROM Consists_of_Entity c WHERE c.workoutPlan.workoutPlanName LIKE %:workoutPlanName%")

     it is written in my sql like 
     select * from consists_of where workout_plan_name = '%workoutPlanName%';
      */

      
    public List<Consists_of_Dto> getPlansByWorkoutPlanName(String workoutPlanName) {
        // Fetch workout plans filtered by workoutPlanName
        List<Consists_of_Entity> entities = repository.findByWorkoutPlan_WorkoutPlanNameContaining(workoutPlanName);

        // Map entities to DTOs
        return entities.stream()
                .map(entity -> {
                    if (entity.getWorkoutPlan() != null && entity.getWorkout() != null) {
                        return new Consists_of_Dto(
                                entity.getWorkoutPlan().getWorkoutPlanName(),
                                entity.getWorkout().getWorkoutName()
                        );
                    }
                    return null;
                })
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }
}


