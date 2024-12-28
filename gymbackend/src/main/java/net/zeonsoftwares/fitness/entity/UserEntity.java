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
@Table(name = "users") // Define table name if needed
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "height")
    private double height;

    @Column(name = "weight")
    private double weight;

    @Column(name = "health_issue")
    private String healthIssue;

    @ManyToOne
    @JoinColumn(name = "user_trainer_id", referencedColumnName = "trainer_id")
    private TrainerEntity trainer;

    @ManyToOne
    @JoinColumn(name = "user_diet_plan_name", referencedColumnName = "diet_plan_name")
    private DietPlanEntity dietPlan;

    @ManyToOne
    @JoinColumn(name = "user_workout_plan_name", referencedColumnName = "workout_plan_name")
    private WorkoutPlanEntity workoutPlan;
}

