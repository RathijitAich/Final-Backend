package net.zeonsoftwares.fitness.services;
import net.zeonsoftwares.fitness.entity.Food;
import net.zeonsoftwares.fitness.repository.FoodRepository;
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
