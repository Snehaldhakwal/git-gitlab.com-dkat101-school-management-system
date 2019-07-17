package com.dk.controller;

import com.dk.model.Subject;
import com.dk.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public @ResponseBody Iterable<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @GetMapping(path = "/{id}")
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
    public Subject create(@RequestBody Subject resource) {
        return subjectRepository.save(resource);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
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
    public void delete(@PathVariable("id") Integer id) {
        subjectRepository.deleteById(id);
    }

}
