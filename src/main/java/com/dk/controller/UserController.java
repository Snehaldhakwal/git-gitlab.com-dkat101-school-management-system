package com.dk.controller;

import com.dk.model.User;
import com.dk.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
@Api(value="User", description="Operations pertaining to user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @GetMapping
    @ApiOperation(
            value = "View a list of available users",
            response = User.class,
            authorizations = { @Authorization(value="apiKey") }
    )
    public @ResponseBody Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(
            value = "View a single record of available users",
            response = User.class,
            authorizations = { @Authorization(value="apiKey") }
    )
    public @ResponseBody User findById(@PathVariable Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(
            value = "Create a single record of users",
            response = User.class,
            authorizations = { @Authorization(value="apiKey") }
    )
    public User create(@RequestBody User resource) {
        return userRepository.save(resource);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Update a single record of available users",
            response = User.class,
            authorizations = { @Authorization(value="apiKey") }
    )
    public @ResponseBody User update(@PathVariable("id") Long id, @RequestBody User newUser) {
        return userRepository.findById(id)
            .map(user -> {
                user.setFirstName(newUser.getFirstName());
                user.setLastName(newUser.getLastName());
                user.setTel(newUser.getTel());
                user.setEmail(newUser.getEmail());
                user.setPassword(bcryptEncoder.encode(newUser.getPassword()));
                user.setRole(newUser.getRole());

                return userRepository.save(user);
            })
            .orElseGet(() -> {

                //return userRepository.save(newUser);
                return null;
            });
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Delete a single record of available users",
            authorizations = { @Authorization(value="apiKey") }
    )
    public void delete(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }

}
