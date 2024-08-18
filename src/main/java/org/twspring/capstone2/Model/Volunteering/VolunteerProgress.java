package org.twspring.capstone2.Model.Volunteering;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "Opportunity ID cannot be null")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer opportunityId;

    @NotNull(message = "Volunteer ID cannot be null")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer volunteerId;

    //initiated at undergoing by default and at the add method
    @NotBlank(message = "status cannot be blank")
    @Pattern(regexp = "Ongoing|Completed|Kicked|Withdrew", message = "Status must be one of: Undergoing, Completed, Kicked, Withdrew")
    @Column(columnDefinition = "VARCHAR(25) NOT NULL DEFAULT 'ONGOING'")
    private String status;

    @NotNull(message = "Target hours cannot be null")
    @Positive( message = "Target hours must be at least 1")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer targetHours;

    @NotNull(message = "Completed hours cannot be null")
    @PositiveOrZero( message = "Completed hours must be at least 0")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer completedHours;

    @Column(columnDefinition = "VARCHAR(500)")
    @Size(min= 25, max = 500, message = "Notes must have between 25 to 500 characters")
    private String notes;

    //maybe accepts a list of volunteers id's to update all? by attendance? so hours are much easier to calculate???

}
