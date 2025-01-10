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
@Table(name = "Admin") // Explicitly specify the table name
public class AdminEntity {
    @Id
    @Column(name = "Admin_ID", nullable = false)
    private String adminId;

    @Column(name = "Admin_Name", nullable = false)
    private String adminName;

    @Column(name = "Email", nullable = false)
    private String adminEmail;

    @Column(name = "Password", nullable = false)
    private String adminPassword;

    @Column(name = "Phone_Number", nullable = false)
    private String adminPhone;
}

