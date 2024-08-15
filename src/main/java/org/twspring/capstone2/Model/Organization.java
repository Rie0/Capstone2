package org.twspring.capstone2.Model;

import jakarta.persistence.*;
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
    private String name;

    //The organization id will be displayed per volunteering opportunity

    //each organization has it's rating by volunteers who worked with it
}
