package net.javaguides.Food_Dictionary_Backend.Service;

import net.javaguides.Food_Dictionary_Backend.Entity.Food;
import net.javaguides.Food_Dictionary_Backend.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }
}
