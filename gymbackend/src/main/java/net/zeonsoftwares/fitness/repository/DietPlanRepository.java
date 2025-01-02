package net.zeonsoftwares.fitness.repository;
import net.zeonsoftwares.fitness.entity.DietPlanEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DietPlanRepository extends JpaRepository<DietPlanEntity, String> {

    //dietPlanRepository.findAll();

    // return all the diet plans
    List<DietPlanEntity> findAll();
}
