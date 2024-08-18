package org.twspring.capstone2.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone2.Model.Organizations.Organization;
import org.twspring.capstone2.Model.Organizations.University;
import org.twspring.capstone2.Model.Users.*;
import org.twspring.capstone2.Model.Volunteering.VolunteeringType;
import org.twspring.capstone2.Service.Imp.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/test")
public class TestController {

    private final AdminService adminService;
    private final UniversityService universityService;
    private final OrganizationService organizationService;
    private final UniversityStaffService universityStaffService;
    private final OrganizerService organizerService;
    private final VolunteerService volunteerService;
    private final VolunteeringTypeService volunteeringTypeService;

    @PostMapping("/create-test-entities")
    public ResponseEntity createTestEntities() {
        //IDs are null to be auto generated

        //Organizations
        Organization org1 = new Organization(
                null, "Organization1"
        );

        Organization org2 = new Organization(
                null, "Organization2"
        );

        Organization org3 = new Organization(
                null, "Organization3"
        );
        organizationService.addOrganization(org1);
        organizationService.addOrganization(org2);
        organizationService.addOrganization(org3);


        // Universities
        University university1 = new University(null, "University1");
        University university2 = new University(null, "University2");
        University university3 = new University(null, "University3");

        universityService.addUniversity(university1);
        universityService.addUniversity(university2);
        universityService.addUniversity(university3);

        //Admins
        Admin admin1 = new Admin(
                null, "admin1@gmail.com", "AdminP@ss123", "admin1", "Maha", "Ahmed", 35, "+966512345682"
        );

        Admin admin2 = new Admin(
                null, "admin2@gmail.com", "AdminS@fe456", "admin2", "Omar", "Ali", 40, "+966512345683"
        );

        Admin admin3 = new Admin(
                null, "admin3@gmail.com", "AdminP@ss789", "admin3", "Lena", "Saeed", 29, "+966512345684"
        );

        adminService.addAdmin(admin1);
        adminService.addAdmin(admin2);
        adminService.addAdmin(admin3);

        //Organizer

        Organizer organizer1 = new Organizer(
                null, "organizer1@OrgOne.com", "OrgP@ss123", "organizer1", "Sami", "Nasser", 45, "+966512345685",
                1, "SUPERVISOR"
        );

        Organizer organizer2 = new Organizer(
                null, "organizer2@OrgTwo.com", "OrgS@fe456", "organizer2", "Amina", "Hassan", 38, "+966512345686",
                1, "EMPLOYEE"
        );

        Organizer organizer3 = new Organizer(
                null, "organizer3@OrgThree.com", "OrgP@ss789", "organizer3", "Youssef", "Fadel", 29, "+966512345687",
                1, "EMPLOYEE"
        );

        organizerService.addOrganizer(organizer1);
        organizerService.addOrganizer(organizer2);
        organizerService.addOrganizer(organizer3);

        // UniversityStaff

        UniversityStaff staff1 = new UniversityStaff(
                null, "staff1@TechUniversity", "StaffP@ss123", "staff1", "Nadia", "Jaber", 40, "+966512345688",
                1
        );

        UniversityStaff staff2 = new UniversityStaff(
                null, "staff2@ScienceUniversity", "StaffS@fe456", "staff2", "Rami", "Ali", 50, "+966512345689",
                1
        );

        UniversityStaff staff3 = new UniversityStaff(
                null, "staff3@ArtUniversity", "StaffP@ss789", "staff3", "Hana", "Zaid", 33, "+966512345690",
                1
        );
        universityStaffService.addUniversityStaff(staff1);
        universityStaffService.addUniversityStaff(staff2);
        universityStaffService.addUniversityStaff(staff3);



        //Volunteers
        Volunteer volunteer1 = new Volunteer(
                null, "volunteer1@gmail.com", "paSsswo#rd123", "volunteer1", "Aisha", "Bassam", 25, "+966512345678",
                "F", "university student", "Bio of Volunteer 1 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", true, true, false, true, false, true, true, true,
                false, 1,0, 0, 0, 0
        );

        Volunteer volunteer2 = new Volunteer(
                null, "volunteer2@gmail.com", "P@ssw0rd123", "volunteer2", "Fahad", "Mansoor", 22, "+966512345681",
                "M", "university student", "Bio of Volunteer 2 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", true, false, false, true, true, false, false, true,
                true, 2, 0,0, 0, 0
        );

        Volunteer volunteer3 = new Volunteer(
                null, "volunteer3@gmail.com", "Str0ngP@ssw0rd", "volunteer3", "Layla", "Saeed", 28, "+966512345680",
                "F", "unemployed", "Bio of Volunteer 3 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", false, true, true, false, true, true, true, true,
                true, 3, 0,0, 0, 0
        );
        volunteerService.addVolunteer(volunteer1);
        volunteerService.addVolunteer(volunteer2);
        volunteerService.addVolunteer(volunteer3);

        // Volunteering Types
        VolunteeringType type1 = new VolunteeringType(null, "Environmental");
        VolunteeringType type2 = new VolunteeringType(null, "Educational");
        VolunteeringType type3 = new VolunteeringType(null, "Health");
        volunteeringTypeService.addVolunteeringType(type1);
        volunteeringTypeService.addVolunteeringType(type2);
        volunteeringTypeService.addVolunteeringType(type3);

        return ResponseEntity.status(201).body("Test entities created successfully");
    }
}
