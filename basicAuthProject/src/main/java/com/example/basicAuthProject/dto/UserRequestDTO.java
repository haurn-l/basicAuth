package com.example.basicAuthProject.dto;

import com.example.basicAuthProject.roles.Role;

public class UserRequestDTO {
    private String name;
    private int age;
    private String username;
    private String password;
    private Role role;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String name, int age, String username, String password, Role role) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}