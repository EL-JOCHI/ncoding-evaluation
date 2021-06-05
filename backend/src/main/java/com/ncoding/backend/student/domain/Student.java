package com.ncoding.backend.student.domain;

import com.ncoding.backend.audit.Audit;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_generator", sequenceName = "student_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "user_id")
    private Long userId;

    @Override
    public String toString() {
        return String.format("[%s] %s %s", id, firstName, lastName);
    }

    public void updateDataWith(Student student) {
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.mobileNumber = student.getMobileNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student student = (Student) o;

        return id != null && id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, userId);
    }
}