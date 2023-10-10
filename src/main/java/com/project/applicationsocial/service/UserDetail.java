package com.project.applicationsocial.service;

import com.project.applicationsocial.model.entity.Users;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
public class UserDetail implements UserDetails {
    private static final long serialVersionUID = 1L;

    private UUID id;

    private String name;
    private String password;
    private String first_name;
    private String last_name;

    private String roles;
    private List<GrantedAuthority> authorities;

    public UserDetail(Users user) {
        name = user.getUsername();
        password = user.getPassword();
        id = user.getId();
        roles = user.getRoles();
        first_name = user.getFirstName();
        last_name = user.getLastName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
