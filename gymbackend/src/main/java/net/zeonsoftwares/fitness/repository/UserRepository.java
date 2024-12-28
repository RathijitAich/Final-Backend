package net.zeonsoftwares.fitness.repository;

import net.zeonsoftwares.fitness.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
    UserEntity findByUserId(String userId);
}