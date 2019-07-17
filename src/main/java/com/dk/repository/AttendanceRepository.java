package com.dk.repository;

import com.dk.model.Attendance;
import org.springframework.data.repository.CrudRepository;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
}
