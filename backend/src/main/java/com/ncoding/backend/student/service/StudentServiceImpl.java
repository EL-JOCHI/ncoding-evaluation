package com.ncoding.backend.student.service;

import com.ncoding.backend.student.domain.Student;
import com.ncoding.backend.student.persistence.StudentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Log4j2
@Service
public class StudentServiceImpl implements StudentService<Student> {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student getStudentById(long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Student createStudent(Student student) {
        if (studentRepository.existsByUserId(student.getUserId())) {
            String message = String.format("Student already has the same user Id [userId=%s]", student.getUserId());
            log.error(message);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long studentId, Student student) {
        return studentRepository.findById(studentId).map(current -> {
            updateStudentData(current, student);
            return studentRepository.save(current);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private void updateStudentData(Student current, Student updated) {
        current.setFirstName(updated.getFirstName());
        current.setLastName(updated.getLastName());
        current.setDocNumber(updated.getDocNumber());
        current.setAddress(updated.getAddress());
        current.setPhoneNumber(updated.getPhoneNumber());
        current.setUserId(updated.getUserId());
    }

    @Override
    public ResponseEntity<?> deleteStudent(Long studentId) {
        return studentRepository.findById(studentId).map(current -> {
            studentRepository.delete(current);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
