package org.twspring.capstone2.Controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone2.Api.ApiResponse;
import org.twspring.capstone2.Model.Volunteering.VolunteeringOpportunity;
import org.twspring.capstone2.Service.Imp.VolunteeringOpportunityService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/volunteering-opportunity")
public class VolunteeringOpportunityController {
    private final VolunteeringOpportunityService volunteeringOpportunityService;

    //===========================GET===========================
    @GetMapping("/get/all")
    public ResponseEntity getAllVolunteeringOpportunities() {
        return ResponseEntity.status(200).body(volunteeringOpportunityService.getAllVolunteeringOpportunities());
    }

    @GetMapping("/get/by_organization/{organizationId}")
    public ResponseEntity getVolunteeringOpportunitiesByOrganization(@PathVariable Integer organizationId) {
        return ResponseEntity.status(200).body(volunteeringOpportunityService.getVolunteeringOpportunitiesByOrganization(organizationId));
    }

    @GetMapping("/get/open-opportunities")
    public ResponseEntity getOpenVolunteeringOpportunities() {
        return ResponseEntity.status(200).body(volunteeringOpportunityService.getOpenVolunteeringOpportunities());
    }
    @GetMapping("/search/{string}")
    public ResponseEntity searchVolunteeringOpportunities(@PathVariable String string) {
        return ResponseEntity.status(200).body(volunteeringOpportunityService.searchVolunteeringOpportunitiesByDescription(string));
    }


    //===========================POST===========================
    @PostMapping("/add/{organizationId}/{organizerId}")
    public ResponseEntity addVolunteeringOpportunity(
            @PathVariable Integer organizationId,
            @PathVariable Integer organizerId,
            @Valid @RequestBody VolunteeringOpportunity volunteeringOpportunity,
            Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        volunteeringOpportunityService.addVolunteeringOpportunity(organizationId, organizerId, volunteeringOpportunity);
        return ResponseEntity.status(201).body(new ApiResponse("Volunteering opportunity added successfully"));
    }

    //===========================PUT===========================
    @PutMapping("/update/{opportunityId}")
    public ResponseEntity updateVolunteeringOpportunity( //edit
            @PathVariable Integer opportunityId,
            @Valid @RequestBody VolunteeringOpportunity volunteeringOpportunity,
            Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        volunteeringOpportunityService.updateVolunteeringOpportunity(opportunityId, volunteeringOpportunity);
        return ResponseEntity.status(200).body(new ApiResponse("Volunteering opportunity updated successfully"));
    }

    //===========================DELETE===========================
    @DeleteMapping("/delete/{opportunityId}") // fix validations
    public ResponseEntity deleteVolunteeringOpportunity(@PathVariable Integer opportunityId) {
        volunteeringOpportunityService.deleteVolunteeringOpportunity(opportunityId);
        return ResponseEntity.status(200).body(new ApiResponse("Volunteering opportunity deleted successfully"));
    }
}

