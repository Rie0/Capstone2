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
    @GetMapping("/get/opportunity/{organizerId}/{opportunityId}")
    public ResponseEntity getVolunteerProgressesByOpportunityId(@PathVariable Integer organizerId, @PathVariable Integer opportunityId) {
        return ResponseEntity.status(200).body(volunteerProgressService.getVolunteerProgressesByOpportunityId(organizerId, opportunityId));
    }

    @GetMapping("/get/volunteer/{volunteerId}")
    public ResponseEntity getAllVolunteerProgressesByVolunteerId(@PathVariable Integer volunteerId) {
        return ResponseEntity.status(200).body(volunteerProgressService.getAllVolunteerProgressesByVolunteerId(volunteerId));
    }

    //===========================PUT===========================
    @PutMapping("/add-hours/{id}/{opportunityId}/{organizerId}/{hours}")
    public ResponseEntity addHoursToVolunteerProgress(@PathVariable Integer id,
                                                      @PathVariable Integer opportunityId,
                                                      @PathVariable Integer organizerId,
                                                      @PathVariable Integer hours) {

        volunteerProgressService.addHoursToVolunteerProgress(id, organizerId, opportunityId, hours);
        return ResponseEntity.status(200).body(new ApiResponse("Hours added successfully"));
    }
    @PutMapping("/edit-note/{id}/{organizerId}")
    public ResponseEntity editNoteOnVolunteerProgress(@PathVariable Integer id,
                                                         @PathVariable Integer organizerId,
                                                         @RequestBody String comment) {
        volunteerProgressService.editNoteOnVolunteerProgress(id, organizerId, comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment edited successfully"));
    }

    @PutMapping("/withdraw/{id}/{volunteerId}")
    public ResponseEntity withdrawFromVolunteeringOpportunity(@PathVariable Integer id,
                                                              @PathVariable Integer volunteerId) {
        volunteerProgressService.withdrawFromVolunteeringOpportunity(id, volunteerId);
        return ResponseEntity.status(200).body(new ApiResponse("Withdrawn from volunteering opportunity successfully"));
    }

    @PutMapping("/kick/{id}/{organizerId}")
    public ResponseEntity kickVolunteerFromVolunteeringOpportunity(@PathVariable Integer id,
                                                                   @PathVariable Integer organizerId) {
        volunteerProgressService.kickVolunteerFromVolunteeringOpportunity(id, organizerId);
        return ResponseEntity.status(200).body(new ApiResponse("Volunteer kicked from volunteering opportunity successfully"));
    }
}
