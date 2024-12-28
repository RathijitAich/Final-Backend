package net.zeonsoftwares.fitness.repository;

import net.zeonsoftwares.fitness.entity.Consists_of_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Consists_of_Repository extends JpaRepository<Consists_of_Entity, net.zeonsoftwares.fitness.entity.ConsistsOfId> {

    // Method to filter by workoutPlanName
    List<Consists_of_Entity> findByWorkoutPlan_WorkoutPlanNameContaining(String workoutPlanName);

    List<Consists_of_Entity> findByWorkoutPlan_WorkoutPlanName(String workoutPlanName);
}
