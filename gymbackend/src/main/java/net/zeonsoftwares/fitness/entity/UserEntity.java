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
@Table(name = "User") // Updated to match schema
public class UserEntity {

    @Id
    @Column(name = "User_ID") // Updated to match schema
    private String userId;

    @Column(name = "Username", nullable = false) // Updated to match schema
    private String username;

    @Column(name = "Password", nullable = false) // Updated to match schema
    private String password;

    @Column(name = "Gender") // Updated to match schema
    private String gender;

    @Column(name = "Height") // Updated to match schema
    private double height;

    @Column(name = "Weight") // Updated to match schema
    private double weight;

    @Column(name = "Health_Issue") // Updated to match schema
    private String healthIssue;

    @ManyToOne
    @JoinColumn(name = "User_Trainer_ID", referencedColumnName = "Trainer_ID") // Updated to match schema
    private TrainerEntity trainer;

    @ManyToOne
    @JoinColumn(name = "User_Diet_Plan_Name", referencedColumnName = "Diet_Plan_Name") // Updated to match schema
    private DietPlanEntity dietPlan;

    @ManyToOne
    @JoinColumn(name = "User_Workout_Plan", referencedColumnName = "Workout_Plan_Name") // Updated to match schema
    private WorkoutPlanEntity workoutPlan;

    // set method for workout plan
}
