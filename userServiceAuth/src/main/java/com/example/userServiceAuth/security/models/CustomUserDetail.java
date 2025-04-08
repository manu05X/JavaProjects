package com.example.userServiceAuth.security.models;

import com.example.userServiceAuth.models.Role;
import com.example.userServiceAuth.models.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.NoArgsConstructor;


@Getter
@Setter
@JsonDeserialize(as = CustomUserDetail.class) // Jackson will use the default constructor
public class CustomUserDetail implements UserDetails {

    private User user;

    @JsonCreator
    public CustomUserDetail() {
    }

    // Constructor with User parameter
    @JsonCreator
    public CustomUserDetail(User user) {
        this.user = user;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // This logic could be uncommented when roles are implemented
        // List<CustomGrantedAuthority> customGrantedAuthorities = new ArrayList<>();
        // for(Role role: user.getRoles()) {
        //     customGrantedAuthorities.add(new CustomGrantedAuthority(role));
        // }
        return null;  // Return the appropriate authorities if needed
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}



/*
@Getter
@Setter
@JsonDeserialize(as = CustomUserDetail.class)
public class CustomUserDetail implements UserDetails {

    private User user;



    public CustomUserDetail(User user) {
        this.user = user;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<CustomGrantedAuthority> customGrantedAuthorities = new ArrayList<>();
//        for(Role role: user.getRoles()) {
//            customGrantedAuthorities.add(new CustomGrantedAuthority(role));
//        }

        //return customGrantedAuthorities;
        return null;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
//    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}

 */