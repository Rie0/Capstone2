package org.twspring.capstone2.Model.Users;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Organizer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "INT NOT NULL")
    @NotNull(message = "Organization Id cannot be null")
    private Integer organizationId;

    @NotBlank(message = "Role cannot be blank")
    @Pattern(regexp = "^(SUPERVISOR|EMPLOYEE)$",
            message = "Role must be either 'SUPERVISOR' or 'EMPLOYEE'")
    @Column(columnDefinition = "VARCHAR(10) NOT NULL")
    private String role;

    // All-args constructor
    public Organizer(Integer id, String email, String password, String username, String firstName, String lastName,
                     Integer age, String phoneNumber, Integer organizationId, String role) {
        super(email, password, username, firstName, lastName, age, phoneNumber);
        this.id = id;
        this.organizationId = organizationId;
        this.role = role;
    }
}
    //an organizer SV posts opportunities

    //organizers all types update volunteers applications and progress

