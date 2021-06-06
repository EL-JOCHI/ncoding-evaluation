package com.ncoding.backend.student.persistence;

import com.ncoding.backend.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Boolean existsByUserId(Long userId);

    Optional<Student> findByUserId(Long userId);
}