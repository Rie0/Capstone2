package org.twspring.capstone2.Model.Users;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Admin extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // All-args constructor
    public Admin(Integer id, String email, String password, String username, String firstName, String lastName,
                 Integer age, String phoneNumber) {
        super(email, password, username, firstName, lastName, age, phoneNumber);
        this.id = id;
    }
}
