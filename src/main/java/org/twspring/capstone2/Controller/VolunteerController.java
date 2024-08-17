package org.twspring.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone2.Api.ApiResponse;
import org.twspring.capstone2.Model.Users.Volunteer;
import org.twspring.capstone2.Service.Imp.VolunteerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/volunteer")
public class VolunteerController {
    private final VolunteerService volunteerService;

    //===========================GET===========================
    @GetMapping("/get/all")
    public ResponseEntity getAllVolunteers() {
        return ResponseEntity.status(200).body(volunteerService.getAllVolunteers());
    }

    //===========================POST===========================
    @PostMapping("/add")
    public ResponseEntity addVolunteer(@Valid @RequestBody Volunteer volunteer, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        volunteerService.addVolunteer(volunteer);
        return ResponseEntity.status(201).body(new ApiResponse("Volunteer added successfully"));
    }

    //===========================PUT===========================
    @PutMapping("/update/{volunteer_id}")
    public ResponseEntity updateVolunteer(@PathVariable Integer volunteer_id, @Valid @RequestBody Volunteer volunteer, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        volunteerService.updateVolunteer(volunteer_id, volunteer);
        return ResponseEntity.status(200).body(new ApiResponse("Volunteer updated successfully"));
    }

    //===========================DELETE===========================
    @DeleteMapping("/delete/{volunteer_id}")
    public ResponseEntity deleteVolunteer(@PathVariable Integer volunteer_id) {
        volunteerService.deleteVolunteer(volunteer_id);
        return ResponseEntity.status(200).body(new ApiResponse("Volunteer deleted successfully"));
    }
}
