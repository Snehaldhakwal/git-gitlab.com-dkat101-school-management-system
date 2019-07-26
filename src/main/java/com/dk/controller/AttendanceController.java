package com.dk.controller;

import com.dk.model.Attendance;
import com.dk.repository.AttendanceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/attendance")
@Api(value="Attendance", description="Operations pertaining to attendance")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping
    @ApiOperation(
            value = "View a list of available attendances",
            response = Iterable.class,
            authorizations = { @Authorization(value="apiKey") }
    )
    public @ResponseBody Iterable<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(
            value = "View a single record of available attendances",
            response = Attendance.class,
            authorizations = { @Authorization(value="apiKey") }
    )
    public @ResponseBody Attendance findById(@PathVariable Long id) {
        Optional<Attendance> attendance = this.attendanceRepository.findById(id);
        if (attendance.isPresent()) {
            return attendance.get();
        } else {
            return null;
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(
            value = "Create a single record of attendance",
            response = Attendance.class,
            authorizations = { @Authorization(value="apiKey") }
    )
    public Attendance create(@RequestBody Attendance resource) {
        return attendanceRepository.save(resource);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Update a single record of available attendances",
            response = Attendance.class,
            authorizations = { @Authorization(value="apiKey") }
    )
    public @ResponseBody Attendance update(@PathVariable("id") Long id, @RequestBody Attendance newAttendance) {
        return attendanceRepository.findById(id)
            .map(attendance -> {
                attendance.setUserId(newAttendance.getUserId());
                attendance.setType(newAttendance.getType());

                return attendanceRepository.save(attendance);
            })
            .orElseGet(() -> {

                return null;
            });
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Delete a single record of available attendances",
            authorizations = { @Authorization(value="apiKey") }
    )
    public void delete(@PathVariable("id") Long id) {
        attendanceRepository.deleteById(id);
    }

}
