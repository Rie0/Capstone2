package org.twspring.capstone2.Model.Volunteering;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteeringOpportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Organization ID cannot be null")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer organizationId;

    @NotNull(message = "Organization ID cannot be null")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer supervisorId;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 10, max = 25, message = "Title must be between 10 and 25 characters")
    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    private String title;

    @NotNull(message = "Volunteering Type ID cannot be null")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer volunteeringTypeId;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 50, max = 500, message = "Description must be between 50 and 500 characters")
    @Column(columnDefinition = "VARCHAR(500) NOT NULL")
    private String description;

    @NotNull(message = "Is registration open cannot be null")
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isRegistrationOpen=true;

    @NotNull(message = "Post date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE NOT NULL DEFAULT TIMESTAMP(CURRENT_DATE)")
    private LocalDate postDate; //final

    @NotNull(message = "Target hours cannot be null")
    @Positive( message = "Target hours must be at least 1")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer targetHours;

    @NotNull(message = "Start date cannot be null")
    @JsonFormat( pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE NOT NULL")
    private LocalDate endDate;

    //@NotNull(message = "Current capacity cannot be null")
    @PositiveOrZero(message = "Current capacity must be zero or greater")
    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    private Integer currentCapacity;

    @NotNull(message = "Max capacity cannot be null")
    @Positive( message = "Max capacity must be at least 1")
    @Column(columnDefinition = "INT NOT NULL")
    private Integer maxCapacity;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    private String location;

    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    @Pattern(regexp = "^(Teams|Solo)$", message = "Work type must be Teams or Solo")
    private String workType;

    //conditions:
    // change to add three conditions, (must|preferred|not required)

    @Pattern(regexp = "^(F|M|Any)$", message = "Gender must be 'M' for male or 'F' for female or 'Any' for both")
    @Column(columnDefinition = "VARCHAR(5)")
    private String requiredGender;

    @NotBlank(message = "Employment Status cannot be blank")
    @Pattern(regexp = "university student|unemployed|employee|Any", message = "Status must be one of the following: university student, unemployed, employee, or Any")
    @Column(columnDefinition = "VARCHAR(25)")
    private String requiredEmploymentStatus;

    @Min(value = 18, message = "Minimum min age allowed is 18")
    @Column(columnDefinition = "INT")
    private Integer minAge;

    @NotNull(message = "Physical fitness status cannot be null")
    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private boolean isPhysicallyFit;

    @NotNull(message = "Availability for weekends cannot be null")
    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private boolean isAvailableForWeekends;

    @NotNull(message = "Driver's license status cannot be null")
    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private boolean hasDriversLicense;

    @NotNull(message = "Ability to work with children cannot be null")
    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private boolean canWorkWithChildren;

    @NotNull(message = "Ability to work with elderly cannot be null")
    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private boolean canWorkWithElderly;

    @NotNull(message = "First aid certification status cannot be null")
    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private boolean hasFirstAidCertification;

    @NotNull(message = "Travel capability cannot be null")
    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private boolean canTravel;

    //posted by an organizer of the organization

    //has conditions that must be applied for the volunteer to apply (gender/employment condition/age/is physically fit)
}
