package com.dk.controller;

import com.dk.model.Subject;
import com.dk.repository.SubjectRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/subject")
@Api(value="Subject", description="Operations pertaining to subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    @ApiOperation(value = "View a list of available subjects", response = Subject.class)
    public @ResponseBody Iterable<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "View a single record of available subjects", response = Subject.class)
    public @ResponseBody Subject findById(@PathVariable Integer id) {
        Optional<Subject> subject = this.subjectRepository.findById(id);
        if (subject.isPresent()) {
            return subject.get();
        } else {
            return null;
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a single record of subject", response = Subject.class)
    public Subject create(@RequestBody Subject resource) {
        return subjectRepository.save(resource);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a single record of available subjects", response = Subject.class)
    public @ResponseBody Subject update(@PathVariable("id") Integer id, @RequestBody Subject newSubject) {
        return subjectRepository.findById(id)
            .map(subject -> {
                subject.setName(newSubject.getName());
                subject.setDescription(newSubject.getDescription());

                return subjectRepository.save(subject);
            })
            .orElseGet(() -> {

                return null;
            });
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete a single record of available subjects")
    public void delete(@PathVariable("id") Integer id) {
        subjectRepository.deleteById(id);
    }

}
