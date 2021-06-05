package com.ncoding.backend.course.persistence;

import com.ncoding.backend.course.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findCoursesByAvailable(boolean availability, Pageable pageable);
}