package com.excelr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.excelr.model.Cart;
import com.excelr.model.User;
import com.excelr.service.UserService;
import com.excelr.repo.CartRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CartRepo cartRepo;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }   
    
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @PostMapping("/entry")
    public ResponseEntity<User> createUser(@RequestBody User userDtls) {
        return ResponseEntity.ok(userService.saveUser(userDtls));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/cart")
    public ResponseEntity<Cart> createCartForUser(@PathVariable Integer id) {
        User user = userService.findUserById(id); 
        
        Cart cart = new Cart();
        cart.setUser(user);
        Cart savedCart = cartRepo.save(cart);

        return ResponseEntity.ok(savedCart);
    }
}
