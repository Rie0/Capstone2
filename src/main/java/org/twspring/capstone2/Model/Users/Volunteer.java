package org.twspring.capstone2.Model.Users;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Entity
@Data
@NoArgsConstructor
public class Volunteer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^(F|M)$", message = "Gender must be 'M' for male or 'F' for female")
    @NotBlank(message = "Gender cannot be blank")
    @Size(min = 1, max = 1, message = "Gender must be a single character")
    @Column(columnDefinition = "VARCHAR(1) NOT NULL")
    private String gender;

    @NotBlank(message = "Employment Status cannot be blank")
    @Pattern(regexp = "university student|unemployed|employee",
            message = "Status must be one of the following only: university student, unemployed, employee")
    @Size(min = 4, max = 25, message = "Employment Status must between 4 to 25 characters")
    @Column(columnDefinition = "VARCHAR(25) NOT NULL")
    private String employmentStatus;

    @NotBlank(message = "Bio cannot be blank")
    @Column(columnDefinition = "VARCHAR(500) NOT NULL")
    @Size(min= 25, max = 500, message = "Bio must have between 25 to 500 characters")
    private String bio;

    // Important info
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

    // Preferences
    @NotNull(message = "Preference for teamwork cannot be null")
    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private boolean prefersTeamwork;

    @NotNull(message = "Preference for solo work cannot be null")
    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private boolean prefersSoloWork;

    @NotNull(message = "Preferred Volunteering Type Id cannot be null")
    @Positive(message = "Preferred Volunteering Type Id cannot be zero or a negative number")
    @Column(columnDefinition = "INT NOT NULL")
    @Range(min = 1, message = "Preferred Volunteering Type Id must be at least 1")
    private Integer preferredVolunteeringTypeId;

    // Stats
    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    @PositiveOrZero( message = "Number of Opportunities Completed cannot be negative")
    private Integer numberOfOpportunitiesCompleted;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    @PositiveOrZero(message = "Number of Opportunities Withdrew cannot be negative")
    private Integer numberOfOpportunitiesWithdrew;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
    @PositiveOrZero(message = "Number of Opportunities Kicked cannot be negative")
    private Integer numberOfOpportunitiesKicked;

    // All-args constructor
    public Volunteer(Integer id, String email, String password, String username, String firstName, String lastName,
                     Integer age, String phoneNumber, String gender, String employmentStatus, String bio,
                     boolean isPhysicallyFit, boolean isAvailableForWeekends, boolean hasDriversLicense,
                     boolean canWorkWithChildren, boolean canWorkWithElderly, boolean hasFirstAidCertification,
                     boolean canTravel, boolean prefersTeamwork, boolean prefersSoloWork,
                     Integer preferredVolunteeringTypeId, Integer numberOfOpportunitiesCompleted,
                     Integer numberOfOpportunitiesWithdrew, Integer numberOfOpportunitiesKicked) {
        super(email, password, username, firstName, lastName, age, phoneNumber);
        this.id = id;
        this.gender = gender;
        this.employmentStatus = employmentStatus;
        this.bio = bio;
        this.isPhysicallyFit = isPhysicallyFit;
        this.isAvailableForWeekends = isAvailableForWeekends;
        this.hasDriversLicense = hasDriversLicense;
        this.canWorkWithChildren = canWorkWithChildren;
        this.canWorkWithElderly = canWorkWithElderly;
        this.hasFirstAidCertification = hasFirstAidCertification;
        this.canTravel = canTravel;
        this.prefersTeamwork = prefersTeamwork;
        this.prefersSoloWork = prefersSoloWork;
        this.preferredVolunteeringTypeId = preferredVolunteeringTypeId;
        this.numberOfOpportunitiesCompleted = numberOfOpportunitiesCompleted;
        this.numberOfOpportunitiesWithdrew = numberOfOpportunitiesWithdrew;
        this.numberOfOpportunitiesKicked = numberOfOpportunitiesKicked;
    }
}


    //volunteers apply to volunteering opportunities

    //for uni
    //top 3 volunteers per month/week will be displayed (Get request to get top 3 volunteers by gained hours)

    //top 3 volunteers of all time (by <Integer> badge_Ids count)

    //has their own search by opportunities they can enlist to

