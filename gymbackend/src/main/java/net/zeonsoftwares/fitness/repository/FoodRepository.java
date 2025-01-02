package net.zeonsoftwares.fitness.repository;

import net.zeonsoftwares.fitness.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, String> {

    //findByfoodName
    Food findByFoodName(String foodName);

}
