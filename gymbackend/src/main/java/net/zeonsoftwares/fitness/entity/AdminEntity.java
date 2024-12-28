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
public class AdminEntity {

        @Id
        @Column(name = "admin_id", nullable = false)
        private String adminId;

        @Column(name = "admin_name", nullable = false)
        private String adminName;

        @Column(name = "admin_email", nullable = false)
        private String adminEmail;

        @Column(name = "admin_password", nullable = false)
        private String adminPassword;

        @Column(name = "admin_phone", nullable = false)
        private String adminPhone;
}
