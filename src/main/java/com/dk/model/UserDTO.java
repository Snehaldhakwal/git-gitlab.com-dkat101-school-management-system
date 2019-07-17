package com.dk.model;

import com.dk.helper.UserRoleOptions;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserDTO {

    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;
    private @Getter @Setter String tel;
    private @Getter @Setter String email;
    private @Getter @Setter String username;
    private @Getter @Setter String password;
    @Enumerated(EnumType.STRING)
    private @Getter @Setter UserRoleOptions role;

}
