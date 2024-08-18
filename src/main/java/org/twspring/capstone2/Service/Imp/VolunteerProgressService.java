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
    private final OrganizationRepository organizationRepository;

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
        if(volunteerProgresses.isEmpty()){
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
        if(volunteerProgresses.isEmpty()){
            throw new ApiException("No volunteer progress found");
        }
        return volunteerProgresses;
    }

    @Override
    public void editNoteOnVolunteerProgress(Integer id, Integer organizerId, String note) {
        VolunteerProgress volunteerProgress = volunteerProgressRepository.findVolunteerProgressById(id);
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);

        if (volunteerProgress == null){
            throw new ApiException("Volunteer progress with id " + id + " not found");
        }
        if (organizer==null){
            throw new ApiException("organizer with id " + organizerId + " not found");
        }

        Organization organization = organizationRepository.findOrganizationById(organizer.getOrganizationId());

        if (organizer.getOrganizationId()!=organization.getId()){
            throw new ApiException("Only organizers of the organization can edit the volunteer progress");
        }
        if (!volunteerProgress.getStatus().equalsIgnoreCase("Ongoing")){
            throw new ApiException("Volunteer progress is not ongoing");
        }

        if (note.length()<25||note.length()>500){
            throw new ApiException("Note length must be between 25 and 500");
        }

        // Update the comment
        volunteerProgress.setNotes(note);
        volunteerProgressRepository.save(volunteerProgress);
    }

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
        if (!volunteerProgress.getStatus().equalsIgnoreCase("Ongoing")){
            throw new ApiException("Volunteer progress is not ongoing");
        }

        if (hours < 1){
            throw new ApiException("Hours cannot be less than 1");
        }

        volunteerProgress.setCompletedHours(volunteerProgress.getCompletedHours()+hours);
        volunteerProgressRepository.save(volunteerProgress);

        //check if volunteer reached the target hours
        if (volunteerProgress.getCompletedHours() >= volunteerProgress.getTargetHours()){
            volunteerProgress.setStatus("Completed");
            volunteerProgressRepository.save(volunteerProgress);
            //edit the volunteer profile
           Volunteer volunteer = volunteerRepository.findVolunteerById(volunteerProgress.getVolunteerId());
           volunteer.setNumberOfOpportunitiesCompleted(volunteer.getNumberOfOpportunitiesCompleted()+1);
           volunteer.setNumberOfHours(volunteer.getNumberOfHours()+volunteerProgress.getCompletedHours());
           volunteerRepository.save(volunteer);
        }

    }

    //add notes

    @Override
    public void withdrawFromVolunteeringOpportunity(Integer id, Integer volunteerId) {
        VolunteerProgress volunteerProgress = volunteerProgressRepository.findVolunteerProgressById(id);
        Volunteer volunteer = volunteerRepository.findVolunteerById(id);
        if (volunteerProgress == null){
            throw new ApiException("Volunteer progress with id " + id + " not found");
        }
        if (volunteer==null){
            throw new ApiException("Volunteer with id " + volunteerId + " not found");
        }
        if (volunteerProgress.getVolunteerId()!=volunteer.getId()){
            throw new ApiException("Volunteer doesn't own this volunteer progress");
        }
        if (!volunteerProgress.getStatus().equalsIgnoreCase("Ongoing")){
            throw new ApiException("Volunteer progress is not ongoing");
        }

        volunteerProgress.setStatus("Withdrew");
        volunteerProgressRepository.save(volunteerProgress);
        //edit the volunteer profile
        volunteer.setNumberOfOpportunitiesWithdrew(volunteer.getNumberOfOpportunitiesWithdrew()+1);
        volunteer.setNumberOfHours(volunteer.getNumberOfHours()+volunteerProgress.getCompletedHours());
        volunteerRepository.save(volunteer);

    }

    @Override
    public void kickVolunteerFromVolunteeringOpportunity(Integer id, Integer supervisorId) {
        VolunteerProgress volunteerProgress = volunteerProgressRepository.findVolunteerProgressById(id);
        Organizer organizer = organizerRepository.findOrganizerById(supervisorId);


        if (volunteerProgress == null){
            throw new ApiException("Volunteer progress with id " + id + " not found");
        }
        if (organizer==null){
            throw new ApiException("organizer with id " + supervisorId + " not found");
        }

        VolunteeringOpportunity volunteeringOpportunity = volunteeringOpportunityRepository.findVolunteeringOpportunityById(volunteerProgress.getOpportunityId());
        Volunteer volunteer = volunteerRepository.findVolunteerById(volunteerProgress.getVolunteerId());

        if (organizer.getId()!=volunteeringOpportunity.getSupervisorId()){
            throw new ApiException("Only the supervisor of this opportunity can kick volunteers");
        }
        if (!volunteerProgress.getStatus().equalsIgnoreCase("Ongoing")){
            throw new ApiException("Volunteer progress is not ongoing");
        }

        volunteerProgress.setStatus("Kicked");
        volunteerProgressRepository.save(volunteerProgress);
        //edit the volunteer profile
        volunteer.setNumberOfOpportunitiesKicked(volunteer.getNumberOfOpportunitiesKicked()+1);
        volunteer.setNumberOfHours(volunteer.getNumberOfHours()+volunteerProgress.getCompletedHours());
        volunteerRepository.save(volunteer);
    }
}
