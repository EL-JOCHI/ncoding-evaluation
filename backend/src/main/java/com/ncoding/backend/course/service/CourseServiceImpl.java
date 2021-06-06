package com.ncoding.backend.course.service;

import com.ncoding.backend.course.domain.Course;
import com.ncoding.backend.course.persistence.CourseRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Log4j2
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

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        log.info("updating course: "+ id);
        return courseRepository.findById(id).map(current -> {
            updateCourseData(current, course);
            return courseRepository.save(current);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private void updateCourseData(Course current, Course updated) {
        current.setName(updated.getName());
        current.setAvailable(updated.getAvailable());
        current.setDescription(updated.getDescription());
        current.setStartDate(updated.getStartDate());
        current.setEndDate(updated.getEndDate());
    }

    @Override
    public ResponseEntity<?> deleteCourse(Long id) {
        return courseRepository.findById(id).map(current -> {
            courseRepository.delete(current);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
