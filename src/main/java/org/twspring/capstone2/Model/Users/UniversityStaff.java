package org.twspring.capstone2.Model.Users;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class UniversityStaff extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "INT NOT NULL")
    @NotNull(message = "University ID cannot be null")
    private Integer universityId;

    public UniversityStaff(Integer id, String email, String password, String username, String firstName,
                           String lastName, Integer age, String phoneNumber, Integer universityId) {
        super(email, password, username, firstName, lastName, age, phoneNumber);
        this.id = id;
        this.universityId = universityId;
    }
}
