package net.zeonsoftwares.fitness.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Food")
public class Food {

    @Id
    @Column(name = "Food_Name")
    private String foodName;

    @Column(name = "Calorie")
    private int calorie;

    @Column(name = "Protein")
    private int protein;

    @Column(name = "Fat")
    private int fat;

    @Column(name = "Carbohydrate")
    private int carbohydrate;

    @Column(name = "Food_Admin_Id")
    private String foodAdminId;

    // Getters and Setters
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getFoodAdminId() {
        return foodAdminId;
    }

    public void setFoodAdminId(String foodAdminId) {
        this.foodAdminId = foodAdminId;
    }
}
