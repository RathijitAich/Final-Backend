package net.zeonsoftwares.fitness.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Workout") // Updated to match schema
public class WorkoutsEntity {

    @Id
    @Column(name = "Workout_Name", nullable = false) // Updated to match schema
    private String workoutName;

    @Column(name = "Workout_Type", nullable = false) // Updated to match schema
    private String workoutType;

    @Column(name = "Target_Muscle") // Updated to match schema
    private String targetMuscle;

    @Column(name = "Equipment") // Updated to match schema
    private String equipmentRequired;

    @Column(name = "Description") // Updated to match schema
    private String workoutDescription;

    @ManyToOne
    @JoinColumn(name = "Workout_Admin_ID", referencedColumnName = "Admin_ID") // Updated to match schema
    @JsonIgnore // Prevent serialization of the admin field
    private AdminEntity admin; // The admin managing this workout
}
