package org.twspring.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone2.Api.ApiResponse;
import org.twspring.capstone2.Model.Users.UniversityStaff;
import org.twspring.capstone2.Service.Imp.UniversityStaffService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/universityStaff")
public class UniversityStaffController {
    private final UniversityStaffService universityStaffService;

    //===========================GET===========================
    @GetMapping("/get/all")
    public ResponseEntity getAllUniversityStaff() {
        return ResponseEntity.status(200).body(universityStaffService.getAllUniversityStaff());
    }

    //===========================POST===========================
    @PostMapping("/add")
    public ResponseEntity addUniversityStaff(@Valid @RequestBody UniversityStaff universityStaff,
                                             Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        universityStaffService.addUniversityStaff(universityStaff);
        return ResponseEntity.status(201).body(new ApiResponse("University staff added successfully"));
    }

    //===========================PUT===========================
    @PutMapping("/update/{staff_id}")
    public ResponseEntity updateUniversityStaff(@PathVariable Integer staff_id,
                                                @Valid @RequestBody UniversityStaff universityStaff,
                                                Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        universityStaffService.updateUniversityStaff(staff_id, universityStaff);
        return ResponseEntity.status(200).body(new ApiResponse("University staff updated successfully"));
    }

    //===========================DELETE===========================
    @DeleteMapping("/delete/{staff_id}")
    public ResponseEntity deleteUniversityStaff(@PathVariable Integer staff_id) {
        universityStaffService.deleteUniversityStaff(staff_id);
        return ResponseEntity.status(200).body(new ApiResponse("University staff deleted successfully"));
    }
}
