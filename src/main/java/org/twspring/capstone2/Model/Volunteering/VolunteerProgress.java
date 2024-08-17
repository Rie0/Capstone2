package org.twspring.capstone2.Model.Volunteering;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    //initiated at undergoing by default and at the add method
    @NotBlank
    @Pattern(regexp = "Undergoing|Completed|Kicked|Withdrew", message = "Status must be one of: Undergoing, Completed, Kicked, Withdrew")
    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    private String status;

    private Integer targetHours;
    private Integer completedHours;

    private String kickedHours;
    //for a volunteer by an opportunity

    //updated only by an organizer(either type)

    //maybe accepts a list of volunteers id's to update all? by attendance? so hours are much easier to calculate???

    //
}
