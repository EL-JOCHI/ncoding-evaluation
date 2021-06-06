package com.ncoding.backend.enrollment.controller.response;

import com.ncoding.backend.course.domain.Course;
import com.ncoding.backend.student.domain.Student;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponse {
    private Course course;
    private Date enrollmentDate;
}