package com.ncoding.backend.enrollment.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EnrollmentKey implements Serializable {

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "course_id")
    private Long courseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return Boolean.TRUE;
        if (Objects.isNull(o) || getClass() != o.getClass()) return Boolean.FALSE;
        EnrollmentKey that = (EnrollmentKey) o;
        return studentId.equals(that.studentId) && courseId.equals(that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }

    @Override
    public String toString() {
        return "EnrollmentKey{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
