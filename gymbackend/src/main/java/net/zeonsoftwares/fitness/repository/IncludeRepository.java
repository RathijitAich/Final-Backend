package net.zeonsoftwares.fitness.repository;

import net.zeonsoftwares.fitness.entity.IncludesEntity;
import net.zeonsoftwares.fitness.entity.IncludesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncludeRepository extends JpaRepository<IncludesEntity, IncludesId> {

  //Method to find foods by diet plan name
    List<IncludesEntity> findByDietPlan_DietPlanNameContaining(String dietPlanName);
    
}
