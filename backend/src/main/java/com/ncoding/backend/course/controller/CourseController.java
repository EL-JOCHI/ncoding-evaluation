package com.ncoding.backend.course.controller;

import com.ncoding.backend.course.domain.Course;
import com.ncoding.backend.course.service.CourseService;
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

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class CourseController {

    private final CourseService<Course> courseService;

    @Operation(summary = "Get all available courses for enrollment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List all available courses for enrollment",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Course.class),
                            examples = @ExampleObject(value = "{\n" +
                                    "            \"createdAt\": \"2021-06-05T15:00:00.000+00:00\",\n" +
                                    "            \"updatedAt\": \"2021-06-05T15:00:00.000+00:00\",\n" +
                                    "            \"id\": 1,\n" +
                                    "            \"name\": \"Java: First Steps\",\n" +
                                    "            \"description\": \"The OCP Oracle Certified Professional Java SE 11 Developer Complete Study Guide in an indispensable resource for anyone preparing for the certification exam.\",\n" +
                                    "            \"available\": true\n" +
                                    "        }"))})
    })
    @GetMapping("courses")
    public ResponseEntity<Page<Course>> getAllAvailableCourses(Pageable pageable) {
        return ResponseEntity.ok(courseService.getAllAvailableCourses(pageable));
    }

    @Operation(summary = "Creates a new Course")
    @PostMapping("courses")
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @Operation(summary = "Updates a Course using the id")
    @PutMapping("courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateCourse(id, course));
    }

    @Operation(summary = "Deletes a Course using the id")
    @DeleteMapping("courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.deleteCourse(id));
    }
}
