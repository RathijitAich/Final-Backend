package net.zeonsoftwares.fitness.entity;

import java.io.Serializable;
import java.util.Objects;

public class ConsistsOfId implements Serializable {
    private String workoutPlan;
    private String workout;

    // Default constructor
    public ConsistsOfId() {}

    // Parameterized constructor
    public ConsistsOfId(String workoutPlan, String workout) {
        this.workoutPlan = workoutPlan;
        this.workout = workout;
    }

    // Getters and setters
    public String getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(String workoutPlan) {
        this.workoutPlan = workoutPlan;
    }

    public String getWorkout() {
        return workout;
    }

    public void setWorkout(String workout) {
        this.workout = workout;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsistsOfId that = (ConsistsOfId) o;
        return Objects.equals(workoutPlan, that.workoutPlan) && Objects.equals(workout, that.workout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workoutPlan, workout);
    }
}