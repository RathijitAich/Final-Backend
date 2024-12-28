package net.zeonsoftwares.fitness.repository;

import net.zeonsoftwares.fitness.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, String> {
    AdminEntity findByAdminId(String adminId);

}