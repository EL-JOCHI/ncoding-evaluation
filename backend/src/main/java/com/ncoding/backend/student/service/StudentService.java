package com.ncoding.backend.student.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StudentService<T> {
    Page<T> findAll(Pageable pageable);

    T getStudentById(long studentId);

    T createStudent(T student);

    T updateStudent(Long studentId, T student);

    ResponseEntity<?> deleteStudent(Long studentId);
}
