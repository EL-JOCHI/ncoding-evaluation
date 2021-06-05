package com.ncoding.backend.course.service;

import com.ncoding.backend.course.domain.Course;
import com.ncoding.backend.course.persistence.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CourseServiceImpl implements CourseService<Course> {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        super();
        this.courseRepository = courseRepository;
    }

    @Override
    public Page<Course> getAllAvailableCourses(Pageable pageable) {
        return courseRepository.findCoursesByAvailable(Boolean.TRUE, pageable);
    }
}
