//package net.zeonsoftwares.fitness.controller;
//
//import net.zeonsoftwares.fitness.entity.UserEntity;
//import net.zeonsoftwares.fitness.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/login")
//@CrossOrigin(origins = "http://localhost:3000")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    // Get all users
//    @GetMapping
//    public ResponseEntity<List<UserEntity>> getAllUsers() {
//        List<UserEntity> users = userRepository.findAll();
//        return ResponseEntity.ok(users);
//    }
//
//    // Get user by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<UserEntity> getUserById(@PathVariable String id) {
//        Optional<UserEntity> user = userRepository.findById(id);
//        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//    }
//
//    // Create new user
//    @PostMapping
//    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
//        UserEntity createdUser = userRepository.save(userEntity);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//    }
//
//    // Update user
//    @PutMapping("/{id}")
//    public ResponseEntity<UserEntity> updateUser(@PathVariable String id, @RequestBody UserEntity userEntity) {
//        if (!userRepository.existsById(id)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        userEntity.setUserId(id);  // Ensure we don't change the userId during update
//        UserEntity updatedUser = userRepository.save(userEntity);
//        return ResponseEntity.ok(updatedUser);
//    }
//
//    // Delete user
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
//        if (!userRepository.existsById(id)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        userRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//}

package net.zeonsoftwares.fitness.controller;

import net.zeonsoftwares.fitness.entity.DietPlanEntity;
import net.zeonsoftwares.fitness.entity.UserEntity;
import net.zeonsoftwares.fitness.entity.WorkoutPlanEntity;
import net.zeonsoftwares.fitness.entity.IncludesEntity;
import net.zeonsoftwares.fitness.repository.IncludeRepository;
import net.zeonsoftwares.fitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IncludeRepository includeRepository;

    // Endpoint to get a user by user_id
    @GetMapping("/{user_id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("user_id") String userId) { 
        // Find the user by user_id using the repository
        UserEntity user = userRepository.findById(userId).orElse(null);

        // Return the user if found, else return 404 NOT FOUND
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // Endpoint to update user profile
    @PutMapping("/{user_id}")
    public ResponseEntity<UserEntity> updateUserProfile(@PathVariable("user_id") String userId,
            @RequestBody UserEntity userDetails) {
        // Directly interact with the UserRepository
        Optional<UserEntity> existingUserOpt = userRepository.findById(userId);

        if (existingUserOpt.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if user not found
        }

        UserEntity existingUser = existingUserOpt.get();

        // Update user details
        existingUser.setUsername(userDetails.getUsername());
        existingUser.setGender(userDetails.getGender());
        existingUser.setHealthIssue(userDetails.getHealthIssue());
        existingUser.setHeight(userDetails.getHeight());
        existingUser.setWeight(userDetails.getWeight());
        existingUser.setPassword(userDetails.getPassword()); // Update password if necessary

        // Save the updated user directly to the database
        userRepository.save(existingUser);

        return ResponseEntity.ok(existingUser); // Return the updated user

    }

    //Endpoint to check if a userId exists
    @GetMapping("/exists/{user_id}")
    public ResponseEntity<Boolean> checkIfUserExists(@PathVariable("user_id") String userId) {
        // Check if the user exists in the database
        boolean userExists = userRepository.existsById(userId);
        return ResponseEntity.ok(userExists);
    }

    //Endpoint to register a new user
    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity user) {
        // Save the user directly to the database
        UserEntity newUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

   

    //endpoint to add user_workout_plan name to the user
    @PutMapping("/{user_id}/workout_plan")
    public ResponseEntity<UserEntity> addWorkoutPlanToUser(@PathVariable("user_id") String userId,
            @RequestParam WorkoutPlanEntity workoutPlanName) {
        // Find the user by user_id using the repository
        UserEntity user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build(); // Return 404 if user not found
        }

        // Add the workout plan name to the user
        user.setWorkoutPlan(workoutPlanName);

        // Save the updated user directly to the database
        userRepository.save(user);

        return ResponseEntity.ok(user); // Return the updated user
    }

    //endpoint to add user_diet_plan name to the user
    @PutMapping("/{user_id}/diet_plan")
    public ResponseEntity<UserEntity> addDietPlanToUser(@PathVariable("user_id") String userId,
            @RequestParam DietPlanEntity dietPlanName) {
        // Find the user by user_id using the repository
        UserEntity user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build(); // Return 404 if user not found
        }

        user.setDietPlan(dietPlanName); // Add the diet plan name to the user
        

        // Save the updated user directly to the database
        userRepository.save(user);

        return ResponseEntity.ok(user); // Return the updated user
    }

    //endpoint to get the diet plan of a user
    @GetMapping("/{user_id}/diet_plan")
    public List<IncludesEntity> getDietPlanOfUser(@PathVariable("user_id") String userId) {
        // Find the user by user_id using the repository
        UserEntity user = userRepository.findById(userId).orElse(null);

        DietPlanEntity dietPlan = user.getDietPlan(); // Get the diet plan of the user

    
        
        return includeRepository.findByDietPlan_DietPlanNameContaining(dietPlan.getDietPlanName());
        
    }
}
