package com.ncoding.backend.course.domain;

import com.ncoding.backend.audit.Audit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Course extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "course_generator", sequenceName = "course_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "available")
    @NotNull
    private Boolean available;

    @Override
    public String toString() {
        return String.format("[%s] - %s ", id, name);
    }

}