package com.dk.model;

import com.dk.helpers.UserRoleOptions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter @Setter Long id;
    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;
    private @Getter @Setter String tel;
    private @Getter @Setter String email;
    private @Getter @Setter String username;
    @JsonIgnore
    private @Getter @Setter String password;
    @Enumerated(EnumType.STRING)
    private @Getter @Setter UserRoleOptions role;
    @CreationTimestamp
    private @Getter LocalDateTime createdOn;
    @UpdateTimestamp
    private @Getter LocalDateTime updatedOn;

}
