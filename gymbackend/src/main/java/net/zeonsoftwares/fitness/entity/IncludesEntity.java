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
@Table(name = "Includes") // Updated to match schema
@IdClass(IncludesId.class)
public class IncludesEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "I_Diet_Plan_Name", referencedColumnName = "Diet_Plan_Name", nullable = false) // Updated to match schema
    private DietPlanEntity dietPlan;

    @Id
    @ManyToOne
    @JoinColumn(name = "I_Food_Name", referencedColumnName = "Food_Name", nullable = false) // Updated to match schema
    private Food food;
}

