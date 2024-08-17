package org.twspring.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone2.Api.ApiResponse;
import org.twspring.capstone2.Model.Users.Organizer;
import org.twspring.capstone2.Service.Imp.OrganizerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/organizer")
public class OrganizerController {
    private final OrganizerService organizerService;

    //===========================GET===========================
    @GetMapping("/get/all")
    public ResponseEntity getAllOrganizers() {
        return ResponseEntity.status(200).body(organizerService.getAllOrganizers());
    }

    //===========================POST===========================
    @PostMapping("/add")
    public ResponseEntity addOrganizer(@Valid @RequestBody Organizer organizer, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        organizerService.addOrganizer(organizer);
        return ResponseEntity.status(201).body(new ApiResponse("Organizer added successfully"));
    }

    //===========================PUT===========================
    @PutMapping("/update/{organizer_id}")
    public ResponseEntity updateOrganizer(@PathVariable Integer organizer_id, @Valid @RequestBody Organizer organizer, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        organizerService.updateOrganizer(organizer_id, organizer);
        return ResponseEntity.status(200).body(new ApiResponse("Organizer updated successfully"));
    }

    //===========================DELETE===========================
    @DeleteMapping("/delete/{organizer_id}")
    public ResponseEntity deleteOrganizer(@PathVariable Integer organizer_id) {
        organizerService.deleteOrganizer(organizer_id);
        return ResponseEntity.status(200).body(new ApiResponse("Organizer deleted successfully"));
    }
}
