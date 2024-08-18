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
    public List<VolunteerApplication> getAllVolunteerApplicationsForOpportunity(Integer opportunityId, Integer supervisorId) { //edit so only supervisor can see
       VolunteeringOpportunity vo = volunteeringOpportunityRepository.findVolunteeringOpportunityById(opportunityId);
        if (vo== null) {
            throw new ApiException("Volunteering opportunity with id " + opportunityId + " not found");
        }
        if(vo.getSupervisorId()!=supervisorId){
            throw  new ApiException("Only the supervisor of this opportunity can view the applications");
        }
        List<VolunteerApplication> volunteerApplications = volunteerApplicationRepository.findVolunteerApplicationByOpportunityId(opportunityId);
        if (volunteerApplications.isEmpty()) {
            throw new ApiException("No applications found");
        }
        return volunteerApplications;
    }

    @Override    //get applications by volunteer id for the volunteer
    public List<VolunteerApplication> getVolunteerApplicationsByVolunteerId(Integer volunteerId) {
        List<VolunteerApplication> volunteerApplications = volunteerApplicationRepository.findVolunteerApplicationByVolunteerId(volunteerId);

        if (volunteerApplications.isEmpty()) {
            throw new ApiException("No applications found for volunteer with id " + volunteerId);
        }

        return volunteerApplications;
    }



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

        //check if the volunteer has already applied
        for (VolunteerApplication va: volunteerApplicationRepository.findVolunteerApplicationByOpportunityId(volunteeringOpportunityId)) {
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
        volunteerApplication.setFitScore("Good");
        volunteerApplication.setStatus("Pending");
        //(2)calculate fit score
        volunteerApplicationRepository.save(volunteerApplication);
    }

    @Override
    public void acceptVolunteerIntoOpportunity(Integer id, Integer opportunityId, Integer organizerId) {
        VolunteerApplication volunteerApplication = volunteerApplicationRepository.findVolunteerApplicationById(id);
        VolunteeringOpportunity opportunity = volunteeringOpportunityRepository.findVolunteeringOpportunityById(opportunityId);

        if (volunteerApplication == null) {
            throw new ApiException("Volunteer application with id " + id + " not found");
        }
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
        if (organizer.getId()!=opportunity.getId()){
            throw new ApiException("Only the supervisor of this opportunity can accept volunteers");
        }

        //disallow duplicates
        if (!volunteerApplication.getStatus().equalsIgnoreCase("Pending")) {
            throw new ApiException("Only pending applications can be accepted");
        }

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

        //close the opportunity if capacity is maxed
        if (opportunity.getCurrentCapacity()>=opportunity.getMaxCapacity()){
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
    public void rejectVolunteerFromOpportunity(Integer id, Integer opportunityId, Integer organizerId) {
        VolunteeringOpportunity opportunity = volunteeringOpportunityRepository.findVolunteeringOpportunityById(opportunityId);
        VolunteerApplication volunteerApplication = volunteerApplicationRepository.findVolunteerApplicationById(id);

        if (volunteerApplication == null) {
            throw new ApiException("Volunteer application with id " + id + " not found");
        }
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

        if (!volunteerApplication.getStatus().equalsIgnoreCase("pending")) {
            throw new ApiException("Only pending applications can be rejected");
        }

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
            throw new ApiException("Only the volunteer of this application can withdraw");
        }
        if (!volunteerApplication.getStatus().equalsIgnoreCase("pending")) {
            throw new ApiException("Only pending applications can be withdrawn");
        }
        volunteerApplicationRepository.delete(volunteerApplication);
    }
}
