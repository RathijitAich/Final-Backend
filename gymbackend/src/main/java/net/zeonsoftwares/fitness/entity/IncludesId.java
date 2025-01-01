package net.zeonsoftwares.fitness.entity;

import java.io.Serializable;
import java.util.Objects;

public class IncludesId implements Serializable {
    private String dietPlan;
    private String food;

    // Default constructor
    public IncludesId() {}

    // Parameterized constructor
    public IncludesId(String dietPlan, String food) {
        this.dietPlan = dietPlan;
        this.food = food;
    }

    // Getters and setters
    public String getDietPlan() {
        return dietPlan;
    }

    public void setDietPlan(String dietPlan) {
        this.dietPlan = dietPlan;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncludesId that = (IncludesId) o;
        return Objects.equals(dietPlan, that.dietPlan) && Objects.equals(food, that.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dietPlan, food);
    }
}