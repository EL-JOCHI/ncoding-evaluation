package com.ncoding.backend.user.controller;

import com.ncoding.backend.course.domain.Course;
import com.ncoding.backend.student.domain.Student;
import com.ncoding.backend.user.controller.request.UserRequest;
import com.ncoding.backend.user.domain.User;
import com.ncoding.backend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@RestController
public class UserController {

    private final UserService<User, UserRequest> userService;

    @Autowired
    public UserController(UserService<User, UserRequest> userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List all users",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject(value = "{}"))})
    })
    @GetMapping("/users")
    public ResponseEntity<Page<User>> getAllStudents(Pageable pageable) {
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @Operation(summary = "Register a new User.")
    @PostMapping("/users/registration")
    public ResponseEntity<User> registerNewUser(@RequestBody UserRequest userRequest, HttpServletRequest request) {
        String appUrl = "http://" + request.getServerName() + ":" +
                request.getServerPort() + request.getContextPath();
        log.info(String.format("Request [%s] register new User: %s", appUrl, userRequest));
        return ResponseEntity.ok(userService.registerNewUser(userRequest));
    }

    @Operation(summary = "Gets the user if login was successful")
    @PostMapping("/users/login")
    public ResponseEntity<User> doLogin(@RequestBody UserRequest userRequest, HttpServletRequest request) {
        String appUrl = "http://" + request.getServerName() + ":" +
                request.getServerPort() + request.getContextPath();
        log.info(String.format("Request [%s] register new User: %s", appUrl, userRequest));
        return ResponseEntity.ok(userService.doLogin(userRequest));
    }

}
