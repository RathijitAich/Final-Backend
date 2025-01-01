package net.zeonsoftwares.fitness.repository;
import net.zeonsoftwares.fitness.entity.DietPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietPlanRepository extends JpaRepository<DietPlanEntity, String> {
}
