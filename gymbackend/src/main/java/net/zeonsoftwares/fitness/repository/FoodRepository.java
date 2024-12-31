package net.javaguides.Food_Dictionary_Backend.Repository;

import net.javaguides.Food_Dictionary_Backend.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, String> {
}
