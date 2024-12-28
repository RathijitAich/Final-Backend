package net.zeonsoftwares.fitness.repository;

import net.zeonsoftwares.fitness.entity.WorkoutsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutsRepository extends JpaRepository<WorkoutsEntity, String> {
}
