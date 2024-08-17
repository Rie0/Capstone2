package org.twspring.capstone2.Model.Volunteering;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteeringOpportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer organizationId;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer organizerId;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    private String title;
    //description
    //post date
    //start date/end date

    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private Integer CurrentCapacity;

    @Column(columnDefinition = "INT NOT NULL")
    private Integer MaxCapacity;

    //posted by an organizer of the organization

    //has conditions that must be applied for the volunteer to apply (gender/employment condition/age/is physically fit)
}
