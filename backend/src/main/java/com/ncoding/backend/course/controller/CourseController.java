package com.ncoding.backend.course.controller;

import com.ncoding.backend.course.domain.Course;
import com.ncoding.backend.course.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class CourseController {

    private final CourseService<Course> courseService;

    @Operation(summary = "Get all available courses for enrollment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List all available courses for enrollment",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Course.class))})
    })
    @GetMapping("courses")
    public ResponseEntity<Page<Course>> getAllAvailableCourses(Pageable pageable) {
        return ResponseEntity.ok(courseService.getAllAvailableCourses(pageable));
    }
}
