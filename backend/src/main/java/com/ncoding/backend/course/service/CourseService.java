package com.ncoding.backend.course.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService<T> {

    Page<T> getAllAvailableCourses(Pageable pageable);
}
