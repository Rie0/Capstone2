package org.twspring.capstone2.Service.Interfaces;

import org.twspring.capstone2.Model.Volunteering.VolunteerApplication;

import java.util.List;

public interface IVolunteerApplicationService {
    List<VolunteerApplication> getAllVolunteerApplicationsForOpportunity(Integer opportunityId, Integer supervisorId);
    List<VolunteerApplication> getVolunteerApplicationsByVolunteerId(Integer volunteerId);
    void applyForVolunteeringOpportunity(Integer volunteerId, Integer volunteeringOpportunityId);
    void acceptVolunteerIntoOpportunity(Integer id, Integer opportunityId, Integer organizerId);
    void rejectVolunteerFromOpportunity(Integer id, Integer opportunityId, Integer organizerId);
    void withdrawVolunteerApplication(Integer id, Integer volunteerId);
}
