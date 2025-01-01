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
@Table(name = "food") // Table name in lowercase
public class Food {

    @Id
    @Column(name = "Food_Name" , nullable = false) // Match column name
    private String foodName;

    @Column(name = "Calorie")
    private int calorie;

    @Column(name = "Protein")
    private int protein;

    @Column(name = "Fat")
    private int fat;

    @Column(name = "Carbohydrate")
    private int carbohydrate;

    @ManyToOne
    @JoinColumn(name = "food_admin_id", referencedColumnName = "admin_id")// Foreign key to AdminEntity (admin_id is the primary key)
    @JsonIgnore // Prevent serialization of the admin field
    private AdminEntity admin;        // The admin managing this workout


}
