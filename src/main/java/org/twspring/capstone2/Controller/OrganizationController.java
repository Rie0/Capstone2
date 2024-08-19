package org.twspring.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone2.Api.ApiResponse;
import org.twspring.capstone2.Model.Organizations.Organization;
import org.twspring.capstone2.Service.Imp.OrganizationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    //===========================GET===========================
    @GetMapping("/get/all")
    public ResponseEntity getAllOrganizations() {
        return ResponseEntity.status(200).body(organizationService.getAllOrganizations());
    }

    //===========================POST===========================
    @PostMapping("/add")
    public ResponseEntity addOrganization(@Valid @RequestBody Organization organization,
                                          Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        organizationService.addOrganization(organization);
        return ResponseEntity.status(201).body(new ApiResponse("Organization added successfully"));
    }

    //===========================PUT===========================
    @PutMapping("/update/{organization_id}")
    public ResponseEntity updateOrganization(@PathVariable Integer organization_id,
                                             @Valid @RequestBody Organization organization,
                                             Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        organizationService.updateOrganization(organization_id, organization);
        return ResponseEntity.status(200).body(new ApiResponse("Organization updated successfully"));
    }

    //===========================DELETE===========================
    @DeleteMapping("/delete/{organization_id}")
    public ResponseEntity deleteOrganization(@PathVariable Integer organization_id) {
        organizationService.deleteOrganization(organization_id);
        return ResponseEntity.status(200).body(new ApiResponse("Organization deleted successfully"));
    }
}

