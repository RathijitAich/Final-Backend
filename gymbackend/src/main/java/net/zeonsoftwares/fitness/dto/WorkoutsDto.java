package net.zeonsoftwares.fitness.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkoutsDto {
    private String workoutName;
    private String workoutType;
    private String targetMuscle;
    private String equipmentRequired;
    private String workoutDescription;
    private String workout_admin_id;
}
