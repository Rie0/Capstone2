package org.twspring.capstone2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //when a volunteers apply for an opportunity

    //must be approved by an organizer of the organization

    //must be qualified
}
