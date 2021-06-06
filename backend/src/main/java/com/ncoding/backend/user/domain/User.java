package com.ncoding.backend.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncoding.backend.user.controller.request.UserRequest;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`user`")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_generator", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "email", nullable = false)
    @NotBlank
    @Email(message = "Please provide a valid email")
    @Size(max = 50)
    private String email;

    @JsonIgnore
    @NotNull
    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String password;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login")
    private Date lastLogin;

}
