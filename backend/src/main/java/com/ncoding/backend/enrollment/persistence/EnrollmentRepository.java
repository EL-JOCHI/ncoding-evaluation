package com.ncoding.backend.enrollment.persistence;

import com.ncoding.backend.enrollment.controller.response.EnrollmentResponse;
import com.ncoding.backend.enrollment.domain.Enrollment;
import com.ncoding.backend.enrollment.domain.EnrollmentKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentKey> {

    Page<Enrollment> findEnrollmentsByIdStudentId(Long studentId, Pageable pageable);
}