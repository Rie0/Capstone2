package org.twspring.capstone2.Service.Imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone2.Api.ApiException;
import org.twspring.capstone2.Model.Users.Organizer;
import org.twspring.capstone2.Model.Users.Volunteer;
import org.twspring.capstone2.Model.Volunteering.VolunteerApplication;
import org.twspring.capstone2.Model.Volunteering.VolunteerProgress;
import org.twspring.capstone2.Model.Volunteering.VolunteeringOpportunity;
import org.twspring.capstone2.Repository.*;
import org.twspring.capstone2.Service.Interfaces.IVolunteerApplicationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerApplicationService implements IVolunteerApplicationService {
    private final VolunteerApplicationRepository volunteerApplicationRepository;
    private final VolunteeringOpportunityRepository volunteeringOpportunityRepository;
    private final VolunteerRepository volunteerRepository;
    private final OrganizerRepository organizerRepository;
    private final VolunteerProgressRepository volunteerProgressRepository;

    @Override
    public List<VolunteerApplication> getAllVolunteerApplicationsForOpportunity(Integer opportunityId) {
        List<VolunteerApplication> volunteerApplications = volunteerApplicationRepository.findVolunteerApplicationByOpportunityId(opportunityId);
        if (volunteerApplications.isEmpty()) {
            throw new ApiException("No volunteer applications found");
        }
        return volunteerApplications;
    }

    //get applications by volunteer id

    @Override
    public void applyForVolunteeringOpportunity(Integer volunteerId, Integer volunteeringOpportunityId, VolunteerApplication volunteerApplication) {
        Volunteer volunteer = volunteerRepository.findVolunteerById(volunteerId);
        VolunteeringOpportunity volunteeringOpportunity = volunteeringOpportunityRepository.findVolunteeringOpportunityById(volunteeringOpportunityId);

        if (volunteer == null) {
            throw new ApiException("Volunteer with id " + volunteerId + " not found");
        }
        if (volunteeringOpportunity == null) {
            throw new ApiException("VolunteeringOpportunity with id " + volunteeringOpportunityId + " not found");
        }
        if (!volunteeringOpportunity.isRegistrationOpen()){
            throw new ApiException("VolunteeringOpportunity is closed");
        }
        //(2)calculate fit score
        volunteerApplicationRepository.save(volunteerApplication);
    }

    @Override
    public void acceptVolunteerIntoOpportunity(Integer id, Integer opportunityId, Integer organizerId) {
        VolunteeringOpportunity opportunity = volunteeringOpportunityRepository.findVolunteeringOpportunityById(opportunityId);
       //valid opportunity
        if (opportunity == null) {
            throw new ApiException("Volunteer with id " + opportunityId + " not found");
        }
        if (!opportunity.isRegistrationOpen()) {
            throw new ApiException("Volunteering Opportunity is closed");
        }
        //valid organizer (supervisor)
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer with id " + organizerId + " not found");
        }
        if (organizer.getOrganizationId()!=opportunity.getOrganizationId()){
            throw new ApiException("Organizer doesn't belong to the organization");
        }
        if (!organizer.getRole().equalsIgnoreCase("Supervisor")){
            throw new ApiException("Only supervisors can accept volunteers");
        }
        VolunteerApplication volunteerApplication = volunteerApplicationRepository.findVolunteerApplicationById(id);

        //Create a volunteerProgress object
        VolunteerProgress volunteerProgress = new VolunteerProgress();

        volunteerProgress.setVolunteerId(volunteerApplication.getVolunteerId());
        volunteerProgress.setOpportunityId(opportunityId);
        volunteerProgress.setCompletedHours(0);
        volunteerProgress.setTargetHours(opportunity.getTargetHours());
        volunteerProgress.setStatus("Undergoing");
        volunteerProgressRepository.save(volunteerProgress);
        volunteerApplication.setStatus("Accepted");

        //Edit the opportunity stats
        opportunity.setCurrentCapacity(opportunity.getCurrentCapacity()+1);
        if (opportunity.getCurrentCapacity()>=opportunity.getMaxCapacity()){ //close the opportunity if capacity is maxed
            opportunity.setRegistrationOpen(false);
        //Edit reject pending volunteers
        List<VolunteerApplication> applications = volunteerApplicationRepository.findVolunteerApplicationWithPendingStatus();
        for (VolunteerApplication application : applications) {
            application.setStatus("Rejected");
        }
        }


    }

    @Override
    public void rejectVolunteerIntoOpportunity(Integer id, Integer opportunityId, Integer organizerId) {
        VolunteeringOpportunity opportunity = volunteeringOpportunityRepository.findVolunteeringOpportunityById(opportunityId);
        //valid opportunity
        if (opportunity == null) {
            throw new ApiException("Volunteer with id " + opportunityId + " not found");
        }
        if (!opportunity.isRegistrationOpen()) {
            throw new ApiException("Volunteering Opportunity is closed");
        }
        //valid organizer (supervisor)
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer with id " + organizerId + " not found");
        }
        if (organizer.getOrganizationId()!=opportunity.getOrganizationId()){
            throw new ApiException("Organizer doesn't belong to the organization");
        }
        if (!organizer.getRole().equalsIgnoreCase("Supervisor")){
            throw new ApiException("Only supervisors can accept volunteers");
        }
        VolunteerApplication volunteerApplication = volunteerApplicationRepository.findVolunteerApplicationById(id);
        volunteerApplication.setStatus("Rejected");
    }

    @Override
    public void withdrawVolunteerApplication(Integer id, Integer volunteerId) {
        VolunteerApplication volunteerApplication = volunteerApplicationRepository.findVolunteerApplicationById(id);
        if (volunteerApplication == null) {
            throw new ApiException("Volunteer application not found");
        }
        Volunteer volunteer = volunteerRepository.findVolunteerById(id);
        if (volunteer == null) {
            throw new ApiException("Volunteer with id " + volunteerId + " not found");
        }
        if (volunteer.getId()!=volunteerApplication.getVolunteerId()){
            throw new ApiException("Volunteer doesn't own this application");
        }
        volunteerApplicationRepository.delete(volunteerApplication);
    }
}
