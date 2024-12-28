//package net.zeonsoftwares.fitness.services;
//
//import net.zeonsoftwares.fitness.entity.AdminEntity;
//import net.zeonsoftwares.fitness.entity.TrainerEntity;
//import net.zeonsoftwares.fitness.entity.UserEntity;
//import net.zeonsoftwares.fitness.repository.AdminRepository;
//import net.zeonsoftwares.fitness.repository.TrainerRepository;
//import net.zeonsoftwares.fitness.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class Loginservices {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private TrainerRepository trainerRepository;
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//    public Optional<UserEntity> validateUserLogin(String username, String password) {
//        return userRepository.findByUsernameAndPassword(username, password);
//    }
//
//    public Optional<TrainerEntity> validateTrainerLogin(String email, String password) {
//        return trainerRepository.findByTrainerEmailAndTrainerPassword(email, password);
//    }
//
//    public Optional<AdminEntity> validateAdminLogin(String email, String password) {
//        return adminRepository.findByAdminEmailAndAdminPassword(email, password);
//    }
//}
