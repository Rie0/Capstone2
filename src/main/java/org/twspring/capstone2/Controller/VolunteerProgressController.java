package org.twspring.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone2.Api.ApiResponse;
import org.twspring.capstone2.Model.Volunteering.VolunteerProgress;
import org.twspring.capstone2.Service.Imp.VolunteerProgressService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/volunteer-progress")
public class VolunteerProgressController {

    private final VolunteerProgressService volunteerProgressService;

    //===========================GET===========================
    @GetMapping("/get/opportunity/{opportunityId}")
    public ResponseEntity getVolunteerProgressesByOpportunityId(@RequestParam Integer organizerId, @PathVariable Integer opportunityId) {
        return ResponseEntity.status(200).body(volunteerProgressService.getVolunteerProgressesByOpportunityId(organizerId, opportunityId));
    }

    @GetMapping("/get/volunteer/{volunteerId}")
    public ResponseEntity getAllVolunteerProgressesByVolunteerId(@PathVariable Integer volunteerId) {
        return ResponseEntity.status(200).body(volunteerProgressService.getAllVolunteerProgressesByVolunteerId(volunteerId));
    }

    //===========================PUT===========================
    @PutMapping("/add-hours/{id}")
    public ResponseEntity addHoursToVolunteerProgress(@PathVariable Integer id,
                                                      @RequestParam Integer organizerId,
                                                      @RequestParam Integer opportunityId,
                                                      @RequestParam Integer hours,
                                                      Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        volunteerProgressService.addHoursToVolunteerProgress(id, organizerId, opportunityId, hours);
        return ResponseEntity.status(200).body(new ApiResponse("Hours added successfully"));
    }

    //===========================DELETE===========================
    @DeleteMapping("/withdraw/{id}")
    public ResponseEntity withdrawFromVolunteeringOpportunity(@PathVariable Integer id,
                                                              @RequestParam Integer volunteerId) {
        volunteerProgressService.withdrawFromVolunteeringOpportunity(id, volunteerId);
        return ResponseEntity.status(200).body(new ApiResponse("Withdrawn from volunteering opportunity successfully"));
    }

    @DeleteMapping("/kick/{id}")
    public ResponseEntity kickVolunteerFromVolunteeringOpportunity(@PathVariable Integer id,
                                                                   @RequestParam Integer organizerId,
                                                                   @RequestParam Integer opportunityId) {
        volunteerProgressService.kickVolunteerFromVolunteeringOpportunity(id, organizerId, opportunityId);
        return ResponseEntity.status(200).body(new ApiResponse("Volunteer kicked from volunteering opportunity successfully"));
    }
}
