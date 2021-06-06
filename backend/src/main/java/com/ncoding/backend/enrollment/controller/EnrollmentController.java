package com.ncoding.backend.enrollment.controller;

import com.ncoding.backend.enrollment.controller.response.EnrollmentResponse;
import com.ncoding.backend.enrollment.domain.Enrollment;
import com.ncoding.backend.enrollment.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class EnrollmentController {

    private final EnrollmentService<Enrollment, EnrollmentResponse> enrollmentService;

    @Operation(summary = "List All the courses enrollments of a student")
    @GetMapping("enrollments/{studentId}")
    public ResponseEntity<Page<EnrollmentResponse>> getEnrollmentsByStudentId(@PathVariable Long studentId,
                                                                              Pageable pageable) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudentId(studentId, pageable));
    }

    @Operation(summary = "Enroll a student in a course")
    @PostMapping("enrollments/")
    public ResponseEntity<Enrollment> enrollmentStudentInCourse(@Valid @RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(enrollmentService.enroll(enrollment));
    }

    @Operation(summary = "Removes the course enrollment from a user.")
    @DeleteMapping("enrollments/{studentId}")
    public ResponseEntity<?> declineEnrollmentByStudentId(@PathVariable Long studentId,
                                                          @RequestParam Long courseId) {
        return enrollmentService.decline(studentId, courseId);
    }
}
