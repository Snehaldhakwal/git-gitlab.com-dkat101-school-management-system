package com.dk.model;

import com.dk.helper.UserRoleOptions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "System input: The database generated ID")
    private @Getter Long id;
    @ApiModelProperty(notes = "User input: The user's first name")
    private @Getter @Setter String firstName;
    @ApiModelProperty(notes = "User input: The user's last name")
    private @Getter @Setter String lastName;
    @ApiModelProperty(notes = "User input: The user's tel")
    private @Getter @Setter String tel;
    @ApiModelProperty(notes = "User input: The user's email")
    private @Getter @Setter String email;
    @ApiModelProperty(notes = "User input: The user's username")
    private @Getter @Setter String username;
    @JsonIgnore
    @ApiModelProperty(notes = "User input: The user's password")
    private @Getter @Setter String password;
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "User input: The user's role. {parent, teacher, admin, super_admin, student}")
    private @Getter @Setter UserRoleOptions role;
    @CreationTimestamp
    @ApiModelProperty(notes = "System input: The database generated created on time")
    private @Getter LocalDateTime createdOn;
    @UpdateTimestamp
    @ApiModelProperty(notes = "System input: The database generated updated on time")
    private @Getter LocalDateTime updatedOn;

}
