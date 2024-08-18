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

import java.time.LocalDate;
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
    public List<VolunteerApplication> getAllVolunteerApplicationsForOpportunity(Integer opportunityId) { //edit so only supervisor can see
        if (volunteeringOpportunityRepository.findVolunteeringOpportunityById(opportunityId)== null) {
            throw new ApiException("Opportunity with id " + opportunityId + " not found");
        }
        List<VolunteerApplication> volunteerApplications = volunteerApplicationRepository.findVolunteerApplicationByOpportunityId(opportunityId);
        if (volunteerApplications.isEmpty()) {
            throw new ApiException("No applications found");
        }
        return volunteerApplications;
    }

    //get applications by volunteer id for the volunteer

    @Override
    public void applyForVolunteeringOpportunity(Integer volunteerId, Integer volunteeringOpportunityId) {
        Volunteer volunteer = volunteerRepository.findVolunteerById(volunteerId);
        VolunteeringOpportunity volunteeringOpportunity = volunteeringOpportunityRepository.findVolunteeringOpportunityById(volunteeringOpportunityId);

        if (volunteer == null) {
            throw new ApiException("Volunteer with id " + volunteerId + " not found");
        }
        if (volunteeringOpportunity == null) {
            throw new ApiException("VolunteeringOpportunity with id " + volunteeringOpportunityId + " not found");
        }

        //check if the user has already applied (replace with a repository method???)
        List<VolunteerApplication> volunteerApplications = volunteerApplicationRepository.findVolunteerApplicationByOpportunityId(volunteeringOpportunityId);
        for (VolunteerApplication va: volunteerApplications) {
            if (va.getVolunteerId()==volunteer.getId()) {
                throw new ApiException("Volunteer already applied to this opportunity");
            }
        }
        if (!volunteeringOpportunity.isRegistrationOpen()){
            throw new ApiException("VolunteeringOpportunity is closed");
        }
        VolunteerApplication volunteerApplication = new VolunteerApplication();
        volunteerApplication.setVolunteerId(volunteerId);
        volunteerApplication.setOpportunityId(volunteeringOpportunityId);
        volunteerApplication.setApplicationDate(LocalDate.now());
        volunteerApplication.setFitScore("Good");
        volunteerApplication.setStatus("Pending");
        //(2)calculate fit score
        volunteerApplicationRepository.save(volunteerApplication);
    }

    @Override //disallow duplicates
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

        //disallow duplicates

        //Create a volunteerProgress object
        VolunteerProgress volunteerProgress = new VolunteerProgress();

        volunteerProgress.setVolunteerId(volunteerApplication.getVolunteerId());
        volunteerProgress.setOpportunityId(opportunityId);
        volunteerProgress.setCompletedHours(0);
        volunteerProgress.setTargetHours(opportunity.getTargetHours());
        volunteerProgress.setStatus("Ongoing");
        volunteerProgressRepository.save(volunteerProgress);
        volunteerApplication.setStatus("Accepted");
        volunteerApplicationRepository.save(volunteerApplication);

        //Edit the opportunity stats
        opportunity.setCurrentCapacity(opportunity.getCurrentCapacity()+1);
        volunteeringOpportunityRepository.save(opportunity);
        if (opportunity.getCurrentCapacity()>=opportunity.getMaxCapacity()){ //close the opportunity if capacity is maxed
            opportunity.setRegistrationOpen(false);
            volunteeringOpportunityRepository.save(opportunity);
        //Edit reject pending volunteers
        List<VolunteerApplication> applications = volunteerApplicationRepository.findVolunteerApplicationWithPendingStatus();
        for (VolunteerApplication application : applications) {
            application.setStatus("Rejected");
            volunteerApplicationRepository.save(application);
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
        volunteerApplicationRepository.save(volunteerApplication);
    }

    @Override
    public void withdrawVolunteerApplication(Integer id, Integer volunteerId) {
        VolunteerApplication volunteerApplication = volunteerApplicationRepository.findVolunteerApplicationById(id);
        if (volunteerApplication == null) {
            throw new ApiException("Volunteer application not found");
        }
        Volunteer volunteer = volunteerRepository.findVolunteerById(volunteerId);
        if (volunteer == null) {
            throw new ApiException("Volunteer with id " + volunteerId + " not found");
        }
        if (volunteer.getId()!=volunteerApplication.getVolunteerId()){
            throw new ApiException("Volunteer doesn't own this application");
        }
        volunteerApplicationRepository.delete(volunteerApplication);
    }
}
