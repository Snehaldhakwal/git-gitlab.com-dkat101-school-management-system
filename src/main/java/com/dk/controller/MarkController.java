package com.dk.controller;

import com.dk.model.Mark;
import com.dk.repository.MarkRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/mark")
@Api(value="Mark", description="Operations pertaining to mark")
public class MarkController {

    @Autowired
    private MarkRepository markRepository;

    @GetMapping
    @ApiOperation(value = "View a list of available marks", response = Mark.class)
    public @ResponseBody Iterable<Mark> findAll() {
        return markRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "View a single record of available marks", response = Mark.class)
    public @ResponseBody Mark findById(@PathVariable Long id) {
        Optional<Mark> mark = this.markRepository.findById(id);
        if (mark.isPresent()) {
            return mark.get();
        } else {
            return null;
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a single record of marks", response = Mark.class)
    public Mark create(@RequestBody Mark resource) {
        return markRepository.save(resource);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a single record of available marks", response = Mark.class)
    public @ResponseBody Mark update(@PathVariable("id") Long id, @RequestBody Mark newMark) {
        return markRepository.findById(id)
            .map(mark -> {
                mark.setStudentId(newMark.getStudentId());
                mark.setSubjectId(newMark.getSubjectId());
                mark.setTeacherId(newMark.getTeacherId());
                mark.setScore(newMark.getScore());

                return markRepository.save(mark);
            })
            .orElseGet(() -> {

                return null;
            });
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete a single record of available marks")
    public void delete(@PathVariable("id") Long id) {
        markRepository.deleteById(id);
    }

}
