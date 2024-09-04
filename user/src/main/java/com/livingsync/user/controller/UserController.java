package com.livingsync.user.controller;


import com.livingsync.user.model.User;
import com.livingsync.user.service.SnsService;
import com.livingsync.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private SnsService snsService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/subscribe/{email}")
    public String subscribeEmail(@PathVariable String email) {
        String topicArn = "arn:aws:sns:ap-south-1:014498630957:notifications";  // Replace with your SNS topic ARN
        return snsService.subscribeEmailToTopic(topicArn, email);
    }
    @GetMapping("/subscribe/phone/{phone}")
    public String subscribePhone(@PathVariable String phone) {
        String topicArn = "arn:aws:sns:ap-south-1:014498630957:notifications";  // Replace with your SNS topic ARN
        return snsService.subscribePhoneToTopic(topicArn, phone);
    }
}
