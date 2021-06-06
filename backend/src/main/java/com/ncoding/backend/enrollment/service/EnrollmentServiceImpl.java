package com.ncoding.backend.enrollment.service;

import com.ncoding.backend.course.persistence.CourseRepository;
import com.ncoding.backend.enrollment.controller.response.EnrollmentResponse;
import com.ncoding.backend.enrollment.domain.Enrollment;
import com.ncoding.backend.enrollment.domain.EnrollmentKey;
import com.ncoding.backend.enrollment.persistence.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService<Enrollment, EnrollmentResponse> {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Enrollment enroll(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Page<EnrollmentResponse> getEnrollmentsByStudentId(Long studentId, Pageable pageable) {
        Page<Enrollment> enrollments = enrollmentRepository.findEnrollmentsByIdStudentId(studentId, pageable);
        var totalElements = enrollments.getTotalElements();
        return new PageImpl<>(enrollments
                .stream()
                .map(enrollment -> EnrollmentResponse.builder()
                        .enrollmentDate(enrollment.getEnrollmentDate())
                        .course(courseRepository.findById(enrollment.getId().getCourseId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                        .build())
                .collect(Collectors.toList()), pageable, totalElements);
    }

    @Override
    public ResponseEntity<?> decline(Long studentId, Long courseId) {
        return enrollmentRepository.findById(EnrollmentKey.builder()
                .courseId(courseId)
                .studentId(studentId)
                .build()).map(current -> {
            enrollmentRepository.delete(current);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
