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
@Table(name = "Workout_Plan") // Updated to match schema
public class WorkoutPlanEntity {

    @Id
    @Column(name = "Workout_Plan_Name", nullable = false, unique = true) // Updated to match schema
    private String workoutPlanName;

    @ManyToOne
    @JoinColumn(name = "Workout_Plan_Trainer_ID", referencedColumnName = "Trainer_ID") // Updated to match schema
    private TrainerEntity trainer; // The trainer who created this workout plan
}
