package org.twspring.capstone2.Service.Interfaces;

import org.twspring.capstone2.Model.Organizations.University;
import org.twspring.capstone2.Model.Users.Volunteer;
import org.twspring.capstone2.Model.Volunteering.VolunteeringOpportunity;

import java.util.List;

public interface IUniversityService {
    public List<University> getAllUniversities();

    List<Volunteer> getAllStudents(Integer universityId, Integer universityStaffId);

    List<VolunteeringOpportunity> getSuggestedOpportunitiesForStudents(Integer universityId, Integer studentId);

    public void addUniversity(University university);

    public void updateUniversity(Integer id, University university);

    void addStudentToUniversity(Integer universityId, Integer universityStaffId, Integer volunteerId);

    void addSuggestedOpportunityToUniversity(Integer universityId, Integer universityStaffId, Integer opportunityId);

    public void deleteUniversity(Integer id);
}