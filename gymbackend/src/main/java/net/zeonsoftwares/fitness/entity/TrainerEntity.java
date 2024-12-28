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
            @Column(name = "trainer_id", nullable = false)
            private String trainerId;

            @Column(name = "trainer_name", nullable = false)
            private String trainerName;

            @Column(name = "trainer_email", nullable = false)
            private String trainerEmail;

            @Column(name = "trainer_password", nullable = false)
            private String trainerPassword;

            @Column(name = "trainer_phone", nullable = false)
            private String trainerPhone;
    @ManyToOne
    @JoinColumn(name = "trainer_admin_id", referencedColumnName = "admin_id")  // Foreign key to AdminEntity (admin_id is the primary key)
    private AdminEntity admin;        // The admin managing this trainer
}
