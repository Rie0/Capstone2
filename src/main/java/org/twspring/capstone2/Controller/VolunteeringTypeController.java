package org.twspring.capstone2.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone2.Model.Volunteering.VolunteeringType;
import org.twspring.capstone2.Service.Interfaces.IVolunteeringTypeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/volunteering_type")
@RequiredArgsConstructor
public class VolunteeringTypeController {

    private final IVolunteeringTypeService volunteeringTypeService;

    @GetMapping("get/all")
    public ResponseEntity<List<VolunteeringType>> getAllVolunteeringTypes() {
        List<VolunteeringType> volunteeringTypes = volunteeringTypeService.getAllVolunteeringTypes();
        return ResponseEntity.ok(volunteeringTypes);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addVolunteeringType(@RequestBody VolunteeringType volunteeringType) {
        volunteeringTypeService.addVolunteeringType(volunteeringType);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Void> updateVolunteeringType(@PathVariable Integer id, @RequestBody VolunteeringType volunteeringType) {
        volunteeringTypeService.updateVolunteeringType(id, volunteeringType);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteVolunteeringType(@PathVariable Integer id) {
        volunteeringTypeService.deleteVolunteeringType(id);
        return ResponseEntity.noContent().build();
    }
}
