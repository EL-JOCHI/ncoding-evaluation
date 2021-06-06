package com.ncoding.backend.student.controller;

import com.ncoding.backend.course.domain.Course;
import com.ncoding.backend.student.domain.Student;
import com.ncoding.backend.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class StudentController {

    private final StudentService<Student> studentService;

    @Operation(summary = "Get all students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List all students",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class),
                            examples = @ExampleObject(value = "{}"))})
    })
    @GetMapping("/students")
    public ResponseEntity<Page<Student>> getAllStudents(Pageable pageable) {
        return ResponseEntity.ok(studentService.findAll(pageable));
    }

    @Operation(summary = "Get A student by Id")
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @Operation(summary = "Get A student by his User Id")
    @GetMapping("/students/user/{userId}")
    public ResponseEntity<Student> getStudentByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(studentService.getStudentByUserId(userId));
    }

    @Operation(summary = "Creates a new Student. Link a userId is optional.")
    @PostMapping("students")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @Operation(summary = "Updates a student by Id. userId can be updated by this method.")
    @PutMapping("students/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @Operation(summary = "Deletes an student by Id. It does not delete the user.")
    @DeleteMapping("students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

}
