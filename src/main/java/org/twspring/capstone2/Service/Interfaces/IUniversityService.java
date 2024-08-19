package org.twspring.capstone2.Service.Interfaces;

import org.twspring.capstone2.Model.Organizations.University;
import org.twspring.capstone2.Model.Volunteering.VolunteeringOpportunity;

import java.util.List;

public interface IUniversityService {
    public List<University> getAllUniversities();

    List<VolunteeringOpportunity> getSuggestedOpportunitiesForStudents(Integer universityId, Integer studentId);

    public void addUniversity(University university);

    public void updateUniversity(Integer id, University university);

    public void deleteUniversity(Integer id);
}