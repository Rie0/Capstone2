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
public class VolunteerProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //for a volunteer by an opportunity

    //updated only by the organizer

    //maybe accepts a list of volunteers id's to update all? by attendance? so hours are much easier to calculate???

    //
}
