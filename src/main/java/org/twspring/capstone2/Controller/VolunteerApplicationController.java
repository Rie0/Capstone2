package org.twspring.capstone2.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone2.Api.ApiResponse;
import org.twspring.capstone2.Service.Imp.VolunteerApplicationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/volunteer-application")
public class VolunteerApplicationController {

    private final VolunteerApplicationService volunteerApplicationService;

    //===========================GET===========================
    @GetMapping("/get/all/{supervisorId}/{opportunityId}")
    public ResponseEntity getAllVolunteerApplicationsForOpportunity(@PathVariable Integer supervisorId, @PathVariable Integer opportunityId) {
        return ResponseEntity.status(200).body(volunteerApplicationService.getAllVolunteerApplicationsForOpportunity(supervisorId,opportunityId));
    }

    @GetMapping("/get/for-volunteer/{volunteerId}")
    public ResponseEntity getVolunteerApplication(@PathVariable Integer volunteerId) {
        return ResponseEntity.status(200).body(volunteerApplicationService.getVolunteerApplicationsByVolunteerId(volunteerId));
    }

    @GetMapping("/get/best-qualified/{supervisorId}/{opportunityId}")
    public ResponseEntity getBestQualifiedVolunteerApplicationsForOpportunity(
            @PathVariable Integer supervisorId,
            @PathVariable Integer opportunityId) {
        return ResponseEntity.status(200).body(volunteerApplicationService.getBestQualifiedVolunteerApplicationsForOpportunity(opportunityId, supervisorId));
    }

    //===========================POST===========================
    @PostMapping("/apply/{volunteerId}/{volunteeringOpportunityId}")
    public ResponseEntity applyForVolunteeringOpportunity(@PathVariable Integer volunteerId,
                                                          @PathVariable Integer volunteeringOpportunityId) {
        volunteerApplicationService.applyForVolunteeringOpportunity(volunteerId, volunteeringOpportunityId);
        return ResponseEntity.status(201).body(new ApiResponse("Application submitted successfully"));
    }

    //===========================PUT===========================
    @PutMapping("/accept/{id}/{volunteeringOpportunityId}/{organizerId}")
    public ResponseEntity acceptVolunteerIntoOpportunity(@PathVariable Integer id,
                                                         @PathVariable Integer volunteeringOpportunityId,
                                                         @PathVariable Integer organizerId) {
        volunteerApplicationService.acceptVolunteerIntoOpportunity(id, volunteeringOpportunityId, organizerId);
        return ResponseEntity.status(200).body(new ApiResponse("Volunteer accepted successfully"));
    }

    @PutMapping("/reject/{id}/{opportunityId}/{organizerId}")
    public ResponseEntity rejectVolunteerFromOpportunity(@PathVariable Integer id,
                                                         @PathVariable Integer opportunityId,
                                                         @PathVariable Integer organizerId) {
        volunteerApplicationService.rejectVolunteerFromOpportunity(id, opportunityId, organizerId);
        return ResponseEntity.status(200).body(new ApiResponse("Volunteer rejected successfully"));
    }

    //===========================DELETE===========================
    @DeleteMapping("/withdraw/{id}/{volunteerId}")
    public ResponseEntity withdrawVolunteerApplication(@PathVariable Integer id,
                                                       @PathVariable Integer volunteerId) {
        volunteerApplicationService.withdrawVolunteerApplication(id, volunteerId);
        return ResponseEntity.status(200).body(new ApiResponse("Application withdrawn successfully"));
    }
}
