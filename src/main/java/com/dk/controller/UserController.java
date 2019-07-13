package com.dk.controller;

import com.dk.model.User;
import com.dk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @GetMapping
    public @ResponseBody Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}")
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
    public User create(@RequestBody User resource) {
        return userRepository.save(resource);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
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
                newUser.setId(id);

                return userRepository.save(newUser);
            });
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }

}
