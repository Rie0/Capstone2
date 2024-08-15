package org.twspring.capstone2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "INT NOT NULL") //must exists
    private Integer OrganizationId;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL UNIQUE")
    private String name;

    //an organizer posts opportunities

    //organizers update volunteers applications and progress

    //works for the organization, has its id
}
