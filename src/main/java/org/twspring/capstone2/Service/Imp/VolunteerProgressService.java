package org.twspring.capstone2.Service.Imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone2.Api.ApiException;
import org.twspring.capstone2.Model.Organizations.Organization;
import org.twspring.capstone2.Model.Users.Organizer;
import org.twspring.capstone2.Model.Users.Volunteer;
import org.twspring.capstone2.Model.Volunteering.VolunteerProgress;
import org.twspring.capstone2.Model.Volunteering.VolunteeringOpportunity;
import org.twspring.capstone2.Repository.*;
import org.twspring.capstone2.Service.Interfaces.IVolunteerProgressService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerProgressService implements IVolunteerProgressService {
    private final VolunteerProgressRepository volunteerProgressRepository;
    private final OrganizerRepository organizerRepository;
    private final VolunteeringOpportunityRepository volunteeringOpportunityRepository;
    private final VolunteerRepository volunteerRepository;

    @Override
    public List<VolunteerProgress> getVolunteerProgressesByOpportunityId(Integer organizerId, Integer opportunityId) {
        VolunteeringOpportunity opportunity = volunteeringOpportunityRepository.findVolunteeringOpportunityById(opportunityId);
        if(opportunity == null){
            throw new ApiException("Volunteering opportunity with id " + opportunityId + " not found");
        }

        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if(organizer == null){
            throw new ApiException("Organizer with id " + organizerId + " not found");
        }
        if (organizer.getOrganizationId()!=opportunity.getOrganizationId()){
            throw new ApiException("Organizer doesn't belong to the organization");
        }
        List<VolunteerProgress> volunteerProgresses = volunteerProgressRepository.findVolunteerProgressByOpportunityId(opportunityId);
        if(volunteerProgresses == null){
            throw new ApiException("No volunteer progress found");
        }
        return volunteerProgresses;
    }

    @Override
    public List<VolunteerProgress> getAllVolunteerProgressesByVolunteerId(Integer volunteerId) { //for the vol
        Volunteer volunteer = volunteerRepository.findVolunteerById(volunteerId);
        if(volunteer == null){
            throw new ApiException("Volunteer with id " + volunteerId + " not found");
        }
        List<VolunteerProgress> volunteerProgresses = volunteerProgressRepository.findVolunteerProgressByVolunteerId(volunteerId);
        if(volunteerProgresses == null){
            throw new ApiException("No volunteer progress found");
        }
        return volunteerProgresses;
    }


    //important to update only ongoing/existing progresses by a valid organizer

    @Override
    public void addHoursToVolunteerProgress(Integer id, Integer organizerId, Integer opportunityId, Integer hours) {
        VolunteerProgress volunteerProgress  = volunteerProgressRepository.findVolunteerProgressById(id);
        if(volunteerProgress == null){
            throw new ApiException("Volunteering progress with id " + id + " not found");
        }

        VolunteeringOpportunity opportunity = volunteeringOpportunityRepository.findVolunteeringOpportunityById(opportunityId);
        if(opportunity == null){
            throw new ApiException("Volunteering opportunity with id " + opportunityId + " not found");
        }

        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if(organizer == null){
            throw new ApiException("Organizer with id " + organizerId + " not found");
        }
        if (organizer.getOrganizationId()!=opportunity.getOrganizationId()){
            throw new ApiException("Organizer doesn't belong to the organization");
        }

        if (hours < 1){
            throw new ApiException("Hours cannot be less than 1");
        }

        volunteerProgress.setCompletedHours(volunteerProgress.getCompletedHours()+hours);

    }

    @Override
    public void withdrawFromVolunteeringOpportunity(Integer id, Integer volunteerId) {

    }

    @Override
    public void kickVolunteerFromVolunteeringOpportunity(Integer id, Integer organizerId, Integer opportunityId) {

    }
}
