package com.ncoding.backend.course.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CourseService<T> {

    Page<T> getAllAvailableCourses(Pageable pageable);
    T createCourse(T course);
    T updateCourse(Long id, T course);
    ResponseEntity<?> deleteCourse(Long id);
}
