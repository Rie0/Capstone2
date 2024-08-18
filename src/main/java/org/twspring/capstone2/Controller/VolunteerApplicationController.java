package org.twspring.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone2.Api.ApiResponse;
import org.twspring.capstone2.Model.Volunteering.VolunteerApplication;
import org.twspring.capstone2.Service.Imp.VolunteerApplicationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/volunteer-application")
public class VolunteerApplicationController {

    private final VolunteerApplicationService volunteerApplicationService;

    //===========================GET===========================
    @GetMapping("/get/all/{opportunityId}")
    public ResponseEntity getAllVolunteerApplicationsForOpportunity(@PathVariable Integer opportunityId) {
        return ResponseEntity.status(200).body(volunteerApplicationService.getAllVolunteerApplicationsForOpportunity(opportunityId));
    }

    //===========================POST===========================
    @PostMapping("/apply")
    public ResponseEntity applyForVolunteeringOpportunity(@RequestParam Integer volunteerId,
                                                          @RequestParam Integer volunteeringOpportunityId,
                                                          @Valid @RequestBody VolunteerApplication volunteerApplication, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        volunteerApplicationService.applyForVolunteeringOpportunity(volunteerId, volunteeringOpportunityId, volunteerApplication);
        return ResponseEntity.status(201).body(new ApiResponse("Application submitted successfully"));
    }

    //===========================PUT===========================
    @PutMapping("/accept/{id}")
    public ResponseEntity acceptVolunteerIntoOpportunity(@PathVariable Integer id,
                                                         @RequestParam Integer opportunityId,
                                                         @RequestParam Integer organizerId) {
        volunteerApplicationService.acceptVolunteerIntoOpportunity(id, opportunityId, organizerId);
        return ResponseEntity.status(200).body(new ApiResponse("Volunteer accepted successfully"));
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity rejectVolunteerIntoOpportunity(@PathVariable Integer id,
                                                         @RequestParam Integer opportunityId,
                                                         @RequestParam Integer organizerId) {
        volunteerApplicationService.rejectVolunteerIntoOpportunity(id, opportunityId, organizerId);
        return ResponseEntity.status(200).body(new ApiResponse("Volunteer rejected successfully"));
    }

    //===========================DELETE===========================
    @DeleteMapping("/withdraw/{id}")
    public ResponseEntity withdrawVolunteerApplication(@PathVariable Integer id,
                                                       @RequestParam Integer volunteerId) {
        volunteerApplicationService.withdrawVolunteerApplication(id, volunteerId);
        return ResponseEntity.status(200).body(new ApiResponse("Application withdrawn successfully"));
    }
}
