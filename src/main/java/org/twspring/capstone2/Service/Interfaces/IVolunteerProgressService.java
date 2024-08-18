package org.twspring.capstone2.Service.Interfaces;

import org.twspring.capstone2.Model.Volunteering.VolunteerProgress;

import java.util.List;

public interface IVolunteerProgressService {

    List<VolunteerProgress> getVolunteerProgressesByOpportunityId(Integer organizerId, Integer opportunityId); //for the org
    List<VolunteerProgress> getAllVolunteerProgressesByVolunteerId(Integer volunteerId); //for the vol

    //set
    public void editNoteOnVolunteerProgress(Integer id, Integer organizerId, String note);

    void addHoursToVolunteerProgress(Integer id, Integer organizerId, Integer opportunityId, Integer hours);
    void withdrawFromVolunteeringOpportunity(Integer id, Integer volunteerId);
    void kickVolunteerFromVolunteeringOpportunity(Integer id, Integer supervisorId);
}
