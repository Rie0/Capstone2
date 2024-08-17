package org.twspring.capstone2.Model.Organizations;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 4, max = 25, message = "Name must have between 4 to 25 characters")
    @Column(columnDefinition = "VARCHAR(25) NOT NULL UNIQUE")
    private String name;

    //university must encourage its students to take more volunteering opportunity
}
