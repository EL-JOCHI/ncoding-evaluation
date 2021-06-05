package com.ncoding.backend.user.domain;

import com.ncoding.backend.audit.Audit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends Audit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_generator", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "email", nullable = false)
    @NotBlank
    @Email(message = "Please provide a valid email")
    @Size(max = 255)
    private String email;

    @NotNull
    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login")
    private Date lastLogin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return Boolean.TRUE;
        if (!(o instanceof User)) return Boolean.FALSE;
        if (!super.equals(o)) return Boolean.FALSE;
        User user = (User) o;
        return id.equals(user.id) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, email);
    }
}
