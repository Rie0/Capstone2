package org.twspring.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone2.Api.ApiResponse;
import org.twspring.capstone2.Model.Organizations.University;
import org.twspring.capstone2.Service.Imp.UniversityService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/university")
public class UniversityController {
    private final UniversityService universityService;

    //===========================GET===========================
    @GetMapping("/get/all")
    public ResponseEntity getAllUniversities() {
        return ResponseEntity.status(200).body(universityService.getAllUniversities());
    }

    //===========================POST===========================
    @PostMapping("/add")
    public ResponseEntity addUniversity(@Valid @RequestBody University university, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        universityService.addUniversity(university);
        return ResponseEntity.status(201).body(new ApiResponse("University added successfully"));
    }

    //===========================PUT===========================
    @PutMapping("/update/{university_id}")
    public ResponseEntity updateUniversity(@PathVariable Integer university_id, @Valid @RequestBody University university, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        universityService.updateUniversity(university_id, university);
        return ResponseEntity.status(200).body(new ApiResponse("University updated successfully"));
    }

    //===========================DELETE===========================
    @DeleteMapping("/delete/{university_id}")
    public ResponseEntity deleteUniversity(@PathVariable Integer university_id) {
        universityService.deleteUniversity(university_id);
        return ResponseEntity.status(200).body(new ApiResponse("University deleted successfully"));
    }
}
