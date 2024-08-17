package org.twspring.capstone2.Model.Organizations;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL UNIQUE")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 4, max = 25, message = "Name must have between 4 to 25 characters")
    private String name;
}
