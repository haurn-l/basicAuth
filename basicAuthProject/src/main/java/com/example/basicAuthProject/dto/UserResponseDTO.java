package com.example.basicAuthProject.dto;

import com.example.basicAuthProject.entity.User;
import com.example.basicAuthProject.roles.Role;
import com.example.basicAuthProject.roles.Status;

public class UserResponseDTO {
    private Long id;
    private String name;
    private int age;
    private String username;
    private Role role;
    private Status status;

    // Constructor with all fields
    public UserResponseDTO(Long id, String name, int age, String username, Role role, Status status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.username = username;
        this.role = role;
        this.status = status;
    }

    // Default constructor
    public UserResponseDTO() {}

    // Constructor to convert User entity to DTO
    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
        this.username = user.getUsername();
        this.role = user.getRole();  // Assuming role is an enum
        this.status = user.getStatus();  // Assuming status is an enum
    }

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
