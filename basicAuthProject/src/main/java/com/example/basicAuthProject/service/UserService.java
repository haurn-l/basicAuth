package com.example.basicAuthProject.service;

import com.example.basicAuthProject.dto.UserRequestDTO;
import com.example.basicAuthProject.dto.UserResponseDTO;

import com.example.basicAuthProject.entity.User;
import com.example.basicAuthProject.exception.CustomException;
import com.example.basicAuthProject.repository.UserRepository;
import com.example.basicAuthProject.roles.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        if (isUsernameTaken(userRequestDTO.getUsername())) {
            throw new CustomException("Username already exists");
        }

        // Şifreyi BCryptPasswordEncoder ile şifreliyoruz
        String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());

        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setAge(userRequestDTO.getAge());
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(encodedPassword);
        user.setRole(userRequestDTO.getRole());
        user.setStatus(Status.ACTIVE); // Default olarak ACTIVE verdik

        userRepository.save(user); // kaydet

        // Response DTO oluşturma
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setAge(user.getAge());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setRole(user.getRole());
        responseDTO.setStatus(user.getStatus());

        return responseDTO;
    }



    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("User not found!"));

    }


    public String deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found"));
        if (user.getStatus() == Status.PASSIVE) {
            throw new CustomException("Cannot delete a deactive user!");
        }
        userRepository.deleteById(id);
        return "User successfully deleted.";
    }
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new CustomException("User not found with id: " + id);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found!"));
        user.setStatus(Status.PASSIVE);
        userRepository.save(user);
    }

    public void activateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found!"));
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
    }
}
