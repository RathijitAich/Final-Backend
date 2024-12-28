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
public class DietPlanEntity {
    @Id
    @Column(name = "diet_plan_name")
    private String dietPlanName;
}
