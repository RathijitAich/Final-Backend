package net.zeonsoftwares.fitness.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class FoodDto {

    private String foodName;
    private int calorie;
    private int protein;
    private int fat;
    private int carbohydrate;
    private String food_admin_Id;

    
}
