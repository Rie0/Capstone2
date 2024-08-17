package org.twspring.capstone2.Model.Users;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    //account
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Column(columnDefinition = "VARCHAR(50) NOT NULL UNIQUE")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
            message = "Password must be strong (at least eight characters: one uppercase letter, one lowercase letter, one number, and one special character)")
    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    private String password;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 4, max = 25, message = "Username must be between 4 and 25 characters")
    @Column(columnDefinition = "VARCHAR(25) NOT NULL UNIQUE")
    private String username;

    //personal
    @NotBlank(message = "First name cannot be blank")
    @Pattern(regexp = "^[A-Za-z]{3,25}$",
            message = "First name must be between 3 to 25 and only alphabetic characters")
    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "^[A-Za-z]{3,25}$",
            message = "Last name must be between 3 to 25 and only alphabetic characters")
    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    private String lastName;

    @NotNull(message = "Age cannot be null")
    @Positive(message = "Age must be a positive number")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer age; //replace with birthday?

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+9665\\d{8}$",
            message = "Phone number must be a valid Saudi phone number")
    @Column(columnDefinition = "VARCHAR(15) NOT NULL UNIQUE")
    private String phoneNumber;
}

