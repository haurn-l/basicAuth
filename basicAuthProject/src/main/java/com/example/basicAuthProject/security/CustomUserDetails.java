package com.example.basicAuthProject.security;

import com.example.basicAuthProject.entity.User;
import com.example.basicAuthProject.roles.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Kullanıcı rolüne göre yetkileri döndürüyoruz.
        // Burada sadece role üzerinden yetkiler atanabilir.
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();  // Şifreyi veritabanından alıyoruz.
    }

    @Override
    public String getUsername() {
        return user.getUsername();  // Kullanıcı adını veritabanından alıyoruz.
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus().equals(Status.ACTIVE);  // Hesap kilitli mi? Sadece aktif kullanıcılar giriş yapabilir.
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().equals(Status.ACTIVE);  // Hesap aktif mi?
    }

    public User getUser() {
        return user;  // Kullanıcıyı döndürüyoruz.
    }
}