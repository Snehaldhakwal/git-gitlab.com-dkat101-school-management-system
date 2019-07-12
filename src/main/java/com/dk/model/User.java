package com.dk.model;

import com.dk.helpers.UserRoleOptions;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter @Setter Long id;
    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;
    private @Getter @Setter String tel;
    private @Getter @Setter String email;
    private @Setter String username;
    private @Setter String password;
    @Enumerated(EnumType.STRING)
    private @Getter @Setter UserRoleOptions role;
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private @Getter List<String> systemRole = new ArrayList<>();
    @CreationTimestamp
    private @Getter LocalDateTime createdOn;
    @UpdateTimestamp
    private @Getter LocalDateTime updatedOn;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.systemRole.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
