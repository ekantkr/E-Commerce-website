package com.excelr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.excelr.Enum.UserAccountStatus;
import com.excelr.Enum.UserRole;
import com.excelr.model.User;
import com.excelr.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Find a user by their ID, throwing an exception if not found
    public User findUserById(Integer id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    // Find a user by their email, throwing an exception if not found
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    // Find a user's ID by their email, throwing an exception if not found
    public Integer findUserIdByEmail(String email) {
        User user = findUserByEmail(email);
        return user.getUserId();  // Using getUserId() to get the user's ID
    }

    // Save a new or updated user to the repository
    public User saveUser(User user) {
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    // Register a new user (wrapper around saveUser for clarity)
    public User registerNewUser(User user) {
        // Additional logic, such as checking for existing users, can go here
    	 user.setRole(UserRole.ROLE_USER);  // Assuming USER is the default role
         user.setUserAccountStatus(UserAccountStatus.ACTIVE);  // Assuming ACTIVE is the default status

         // Encrypt the password before saving
         user.setPassword(passwordEncoder.encode(user.getPassword()));

        
        return saveUser(user);
    }

    // Delete a user by their ID
    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }
}
