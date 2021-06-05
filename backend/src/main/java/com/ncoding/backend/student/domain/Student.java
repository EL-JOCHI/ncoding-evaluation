package com.ncoding.backend.student.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_generator", sequenceName = "student_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    @NotBlank
    @Size(max=80)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    @NotBlank
    @Size(max=80)
    private String lastName;

    @Column(name = "doc_number")
    @NotBlank
    @Size(max=30)
    private String docNumber;

    @Column(name = "phone_number")
    @NotBlank
    @Size(max=30)
    private String phoneNumber;

    @Column(name = "address")
    @NotBlank
    @Size(max=255)
    private String address;

    @Column(name = "user_id")
    private Long userId;

    @Override
    public String toString() {
        return String.format("[%s] %s %s", id, firstName, lastName);
    }

}