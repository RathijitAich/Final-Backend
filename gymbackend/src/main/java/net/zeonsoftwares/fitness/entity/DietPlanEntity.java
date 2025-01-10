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
@Table(name = "Diet_Plan") // Updated to match schema
public class DietPlanEntity {

    @Id
    @Column(name = "Diet_Plan_Name", nullable = false, unique = true) // Updated to match schema
    private String dietPlanName;

    @ManyToOne
    @JoinColumn(name = "Diet_Plan_Trainer_ID", referencedColumnName = "Trainer_ID") // Updated to match schema
    private TrainerEntity trainer; // The trainer who created this diet plan
}
