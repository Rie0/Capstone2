package org.twspring.capstone2.Model.Volunteering;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Opportunity ID cannot be null")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer opportunityId;

    @NotNull(message = "Volunteer ID cannot be null")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer volunteerId;

    //(2)
    @Pattern(regexp = "^(Perfect|Good|acceptable|Weak)$")
    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    private String fitScore; //a point for each attribute required

    @NotNull(message = "Application date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE NOT NULL DEFAULT TIMESTAMP(CURRENT_DATE)")
    private final LocalDate applicationDate = LocalDate.now();

    @Pattern(regexp = "^(Pending|Accepted|Rejected)$")
    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    private String status; //a point for each attribute required


    //when a volunteers apply for an opportunity (MAKE SURE NO ONE CAN SEE ALL THE DETAILS INSIDE VOLUNTEER)

    //must be approved by an organizer of the organization

    //must be qualified to apply
}
