package org.twspring.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone2.Api.ApiResponse;
import org.twspring.capstone2.Model.Users.Admin;
import org.twspring.capstone2.Service.Imp.AdminService;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    //===========================GET===========================
    @GetMapping("/get/all")
    public ResponseEntity getAllAdmins() {
        return ResponseEntity.status(200).body(adminService.getAllAdmins());
    }

    //===========================POST===========================
    @PostMapping("/add")
    public ResponseEntity addAdmin(@Valid @RequestBody Admin admin, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        adminService.addAdmin(admin);
        return ResponseEntity.status(201).body(new ApiResponse("Admin added successfully"));
    }

    //===========================PUT===========================
    @PutMapping("/update/{admin_id}")
    public ResponseEntity updateAdmin(@PathVariable Integer admin_id,
                                      @Valid @RequestBody Admin admin,
                                      Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        adminService.updateAdmin(admin_id, admin);
        return ResponseEntity.status(200).body(new ApiResponse("Admin updated successfully"));
    }

    //===========================DELETE===========================
    @DeleteMapping("/delete/{admin_id}")
    public ResponseEntity deleteAdmin(@PathVariable Integer admin_id) {
        adminService.deleteAdmin(admin_id);
        return ResponseEntity.status(200).body(new ApiResponse("Admin deleted successfully"));
    }
}


