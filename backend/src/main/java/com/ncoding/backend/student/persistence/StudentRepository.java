package com.ncoding.backend.student.persistence;

import com.ncoding.backend.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}