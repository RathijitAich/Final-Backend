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
@Table(name = "consists_of") // Table name in lowercase
@IdClass(ConsistsOfId.class)
public class Consists_of_Entity {

    @Id
    @ManyToOne
    @JoinColumn(name = "workout_plan_name", referencedColumnName = "workout_plan_name", nullable = false)
    private WorkoutPlanEntity workoutPlan;

    @Id
    @ManyToOne
    @JoinColumn(name = "workout_name", referencedColumnName = "workout_name", nullable = false)
    private WorkoutsEntity workout;
}