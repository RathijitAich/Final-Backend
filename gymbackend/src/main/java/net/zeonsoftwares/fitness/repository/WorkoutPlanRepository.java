package net.zeonsoftwares.fitness.repository;
import net.zeonsoftwares.fitness.entity.WorkoutPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlanEntity, String> {
}
