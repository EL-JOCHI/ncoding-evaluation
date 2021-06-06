package com.ncoding.backend.enrollment.service;

import com.ncoding.backend.enrollment.domain.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EnrollmentService<E, T> {

    E enroll(Enrollment enrollment);

    Page<T> getEnrollmentsByStudentId(Long studentId, Pageable pageable);

    ResponseEntity<?> decline(Long studentId, Long courseId);
}
