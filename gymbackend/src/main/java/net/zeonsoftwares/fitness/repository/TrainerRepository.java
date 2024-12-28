package net.zeonsoftwares.fitness.repository;

import net.zeonsoftwares.fitness.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface TrainerRepository extends JpaRepository<TrainerEntity, String> {
    TrainerEntity findByTrainerId(String trainerId);
}

