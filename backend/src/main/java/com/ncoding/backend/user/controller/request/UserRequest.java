package com.ncoding.backend.user.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements Serializable {

    @NotNull
    @NotBlank
    @Email(message = "Please provide a valid email")
    @Size(max = 50)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String password;
}
