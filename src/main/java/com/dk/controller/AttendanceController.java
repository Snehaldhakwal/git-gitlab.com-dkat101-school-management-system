package com.dk.controller;

import com.dk.model.Attendance;
import com.dk.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping
    public @ResponseBody Iterable<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

    @GetMapping(path = "/{id}")
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
    public Attendance create(@RequestBody Attendance resource) {
        return attendanceRepository.save(resource);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
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
    public void delete(@PathVariable("id") Long id) {
        attendanceRepository.deleteById(id);
    }

}
