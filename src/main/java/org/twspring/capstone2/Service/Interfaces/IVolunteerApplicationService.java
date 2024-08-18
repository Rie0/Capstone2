package org.twspring.capstone2.Service.Interfaces;

import org.twspring.capstone2.Model.Volunteering.VolunteerApplication;

import java.util.List;

public interface IVolunteerApplicationService {
    List<VolunteerApplication> getAllVolunteerApplicationsForOpportunity(Integer opportunityId);
    void applyForVolunteeringOpportunity(Integer volunteerId, Integer volunteeringOpportunityId, VolunteerApplication volunteerApplication);
    void acceptVolunteerIntoOpportunity(Integer id, Integer opportunityId, Integer organizerId);
    void rejectVolunteerIntoOpportunity(Integer id, Integer opportunityId, Integer organizerId); //add a status to the application for this
    void withdrawVolunteerApplication(Integer id, Integer volunteerId);
}
