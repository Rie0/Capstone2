package org.twspring.capstone2.Service.Imp;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone2.Model.Users.Organizer;
import org.twspring.capstone2.Repository.OrganizerRepository;
import org.twspring.capstone2.Service.Interfaces.IOrganizerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerService implements IOrganizerService {

    private final OrganizerRepository organizerRepository;

    @Override
    public List<Organizer> getAllOrganizers() {
        List<Organizer> organizers = organizerRepository.findAll();
        if (organizers.isEmpty()) {
            throw new EntityNotFoundException("No organizers found");
        }
        return organizers;
    }

    @Override
    public void addOrganizer(Organizer organizer) {
        organizerRepository.save(organizer);
    }

    @Override
    public void updateOrganizer(Integer id, Organizer organizer) {
        Organizer existingOrganizer = organizerRepository.findOrganizerById(id);
        if (existingOrganizer == null) {
            throw new EntityNotFoundException("Organizer with ID " + id + " not found");
        }
        //commented fields are the ones that don't make sense to change
        existingOrganizer.setEmail(organizer.getEmail());
        existingOrganizer.setPhoneNumber(organizer.getPhoneNumber());
        existingOrganizer.setUsername(organizer.getUsername());
//        existingOrganizer.setFirstName(organizer.getFirstName());
//        existingOrganizer.setLastName(organizer.getLastName());
        existingOrganizer.setPassword(organizer.getPassword());
//        existingOrganizer.setRole(organizer.getRole()); //only a supervisor can edit this field
        // Organization ID should not be updated, so it's not included here
        organizerRepository.save(existingOrganizer);
    }

    @Override
    public void deleteOrganizer(Integer id) {
        Organizer organizer = organizerRepository.findOrganizerById(id);
        if (organizer == null) {
            throw new EntityNotFoundException("Organizer with ID " + id + " not found");
        }
        organizerRepository.delete(organizer);
    }
}
