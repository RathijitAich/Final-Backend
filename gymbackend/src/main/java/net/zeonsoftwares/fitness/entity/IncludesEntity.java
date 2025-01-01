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
@Table(name = "includes") // Table name in lowercase
@IdClass(IncludesId.class)
public class IncludesEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "diet_plan_name", referencedColumnName = "diet_plan_name", nullable = false)
    private DietPlanEntity dietPlan;

    @Id
    @ManyToOne
    @JoinColumn(name = "food_name", referencedColumnName = "Food_name", nullable = false)
    private Food food;
}
