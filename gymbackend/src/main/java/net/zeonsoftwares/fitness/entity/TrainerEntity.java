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
public class TrainerEntity {

    @Id
    @Column(name = "Trainer_ID", nullable = false) // Updated to match schema
    private String trainerId;

    @Column(name = "Trainer_Name", nullable = false) // Updated to match schema
    private String trainerName;

    @Column(name = "Email", nullable = false) // Updated to match schema
    private String trainerEmail;

    @Column(name = "Password", nullable = false) // Updated to match schema
    private String trainerPassword;

    @Column(name = "Phone_Number", nullable = false) // Updated to match schema
    private String trainerPhone;

    @ManyToOne
    @JoinColumn(name = "Trainer_Admin_ID", referencedColumnName = "Admin_ID")  // Updated to match schema
    private AdminEntity admin; // The admin managing this trainer
}
