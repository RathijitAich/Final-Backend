package net.zeonsoftwares.fitness.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Consists_Of") // Updated to match schema
@IdClass(ConsistsOfId.class)
public class Consists_of_Entity { // Renamed class to match naming conventions

    @Id
    @ManyToOne
    @JoinColumn(name = "C_Workout_Plan_Name", referencedColumnName = "Workout_Plan_Name", nullable = false) // Updated to match schema
    private WorkoutPlanEntity workoutPlan;

    @Id
    @ManyToOne
    @JoinColumn(name = "C_Workout_Name", referencedColumnName = "Workout_Name", nullable = false) // Updated to match schema
    private WorkoutsEntity workout;
}
