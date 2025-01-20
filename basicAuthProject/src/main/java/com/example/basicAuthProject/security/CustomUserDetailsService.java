package com.example.basicAuthProject.security;

import com.example.basicAuthProject.entity.User;
import com.example.basicAuthProject.exception.CustomException;
import com.example.basicAuthProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws CustomException {
        // Kullanıcıyı veritabanından buluyoruz
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("Kullanıcı bulunamadı: " + username));

        // Bulunan kullanıcıyı CustomUserDetails olarak döndürüyoruz
        return new CustomUserDetails(user);
    }
}
