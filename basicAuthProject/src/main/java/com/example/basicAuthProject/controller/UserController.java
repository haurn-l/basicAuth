package com.example.basicAuthProject.controller;

import com.example.basicAuthProject.dto.UserRequestDTO;
import com.example.basicAuthProject.dto.UserResponseDTO;
import com.example.basicAuthProject.entity.User;
import com.example.basicAuthProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
     UserService userService;

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.registerUser(userRequestDTO);
    }

    @GetMapping("/me")
    public String getUserDetails(Principal principal) {//aktif olarak girişyapmış kullanıcıya ait verileri almak için principal kullanılıyor
        User user = userService.getUserByUsername(principal.getName());
        return "User Information: " + user.toString();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @DeleteMapping("/deleteme")
    public String deleteSelf(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return userService.deleteUser(user.getId()); // Kullanıcı kendi kaydını silebilir.
    }


    @PutMapping("/deactivate/{id}")
    public String deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return "The user has been deactivated.";
    }

    @PutMapping("/activate/{id}")
    public String activateUser(@PathVariable Long id) {
        userService.activateUser(id);
        return "The user has been activated.";
    }
    @GetMapping("/user/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new UserResponseDTO(user);
    }
    @GetMapping("/all")
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream()
                .map(UserResponseDTO::new)//streamerı
                .toList();
    }
}
