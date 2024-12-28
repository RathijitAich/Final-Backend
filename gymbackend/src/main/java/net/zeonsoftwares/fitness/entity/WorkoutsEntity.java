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
@Table(name = "workouts") // Table name in lowercase
public class WorkoutsEntity {

    @Id
    @Column(name = "workout_name", nullable = false) // Match column name
    private String workoutName;

    @Column(name = "workout_type", nullable = false)
    private String workoutType;

    @Column(name = "target_muscle")
    private String targetMuscle;

    @Column(name = "equipment_required")
    private String equipmentRequired;

    @Column(name = "workout_description")
    private String workoutDescription;

    @ManyToOne
    @JoinColumn(name = "workout_admin_id", referencedColumnName = "admin_id")// Foreign key to AdminEntity (admin_id is the primary key)
    @JsonIgnore // Prevent serialization of the admin field
    private AdminEntity admin;        // The admin managing this workout
}
