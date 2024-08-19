package org.twspring.capstone2.Service.Imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone2.Api.ApiException;
import org.twspring.capstone2.Model.Users.Organizer;
import org.twspring.capstone2.Model.Users.Volunteer;
import org.twspring.capstone2.Repository.OrganizerRepository;
import org.twspring.capstone2.Repository.VolunteerRepository;
import org.twspring.capstone2.Service.Interfaces.IVolunteerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerService implements IVolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final OrganizerRepository organizerRepository;

    @Override
    public List<Volunteer> getAllVolunteers() {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        if (volunteers.isEmpty()) {
            throw new ApiException("No volunteers found");
        }
        return volunteers;
    }

    //
    @Override
    public Volunteer getVolunteerProfileForWatching(Integer volunteerId) {
        Volunteer volunteer = volunteerRepository.findVolunteerById(volunteerId);
        if (volunteer == null) {
            throw new ApiException("Volunteer with ID " + volunteerId + " not found");
        }
        // Remove personal info
        volunteer.setEmail(null);
        volunteer.setPhoneNumber(null);
        volunteer.setPassword(null);
        return volunteer;
    }

    @Override
    public Volunteer getVolunteerProfileForOrganizer(Integer organizerId, Integer volunteerId) {
        Volunteer volunteer = volunteerRepository.findVolunteerById(volunteerId);
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer with ID " + organizerId + " not found");
        }
        if (volunteer == null) {
            throw new ApiException("Volunteer with ID " + volunteerId + " not found");
        }
        // Remove password
        volunteer.setPassword(null);
        return volunteer;
    }


    @Override
    public void addVolunteer(Volunteer volunteer) {
        //initiate as zero for a new user
        volunteer.setNumberOfOpportunitiesCompleted(0);
        volunteer.setNumberOfOpportunitiesWithdrew(0);
        volunteer.setNumberOfOpportunitiesKicked(0);
        volunteerRepository.save(volunteer);
    }

    @Override
    public void updateVolunteer(Integer id, Volunteer volunteer) {
        Volunteer existingVolunteer = volunteerRepository.findVolunteerById(id);
        if (existingVolunteer == null) {
            throw new ApiException("Volunteer with ID " + id + " not found");
        }

        //commented fields are the ones that don't make sense to change
        existingVolunteer.setUsername(volunteer.getUsername());
        existingVolunteer.setEmail(volunteer.getEmail());
        existingVolunteer.setPhoneNumber(volunteer.getPhoneNumber());
//        existingVolunteer.setFirstName(volunteer.getFirstName());
//        existingVolunteer.setLastName(volunteer.getLastName());
        existingVolunteer.setPassword(volunteer.getPassword());
        //existingVolunteer.setGender(volunteer.getGender());
        existingVolunteer.setEmploymentStatus(volunteer.getEmploymentStatus());
        existingVolunteer.setBio(volunteer.getBio());
        existingVolunteer.setPhysicallyFit(volunteer.isPhysicallyFit());
        existingVolunteer.setAvailableForWeekends(volunteer.isAvailableForWeekends());
        existingVolunteer.setHasDriversLicense(volunteer.isHasDriversLicense());
        existingVolunteer.setCanWorkWithChildren(volunteer.isCanWorkWithChildren());
        existingVolunteer.setCanWorkWithElderly(volunteer.isCanWorkWithElderly());
        existingVolunteer.setHasFirstAidCertification(volunteer.isHasFirstAidCertification());
        existingVolunteer.setCanTravel(volunteer.isCanTravel());
        existingVolunteer.setPrefersTeamwork(volunteer.isPrefersTeamwork());
        existingVolunteer.setPrefersSoloWork(volunteer.isPrefersSoloWork());
        existingVolunteer.setPreferredVolunteeringTypeId(volunteer.getPreferredVolunteeringTypeId());
        volunteerRepository.save(existingVolunteer);
    }

    @Override
    public void deleteVolunteer(Integer id) {
        Volunteer volunteer = volunteerRepository.findVolunteerById(id);
        if (volunteer == null) {
            throw new ApiException("Volunteer with ID " + id + " not found");
        }
        volunteerRepository.delete(volunteer);
    }
}
