package org.twspring.capstone2.Service.Imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone2.Api.ApiException;
import org.twspring.capstone2.Model.Organizations.University;
import org.twspring.capstone2.Model.Users.Volunteer;
import org.twspring.capstone2.Model.Volunteering.VolunteeringOpportunity;
import org.twspring.capstone2.Repository.UniversityRepository;
import org.twspring.capstone2.Repository.VolunteerRepository;
import org.twspring.capstone2.Repository.VolunteeringOpportunityRepository;
import org.twspring.capstone2.Service.Interfaces.IUniversityService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService implements IUniversityService {

    private final UniversityRepository universityRepository;
    private final VolunteerRepository volunteerRepository;
    private final VolunteeringOpportunityRepository volunteeringOpportunityRepository;

    @Override
    public List<University> getAllUniversities() {
        List<University> universities = universityRepository.findAll();
        if (universities.isEmpty()) {
            throw new ApiException("No universities found");
        }
        return universities;
    }

    //@Override
    public List<Volunteer> getAllStudents(Integer universityId) {
        University university = universityRepository.findById(universityId).orElseThrow(() ->
                new ApiException("University with ID " + universityId + " not found"));

        List<Integer> studentIds = university.getStudentIds();
        return volunteerRepository.findAllById(studentIds);
    }

    //@Override
    public List<VolunteeringOpportunity> getSuggestedOpportunitiesForStudents(Integer universityId, Integer studentId) {
        University university = universityRepository.findById(universityId).orElseThrow(() ->
                new ApiException("University with ID " + universityId + " not found"));

        if (!university.getStudentIds().contains(studentId)) {
            throw new ApiException("Student with ID " + studentId + " is not enrolled in this university");
        }

        List<Integer> suggestedOpportunityIds = university.getSuggestedOpportunityIds();
        return volunteeringOpportunityRepository.findAllById(suggestedOpportunityIds);
    }

    @Override
    public void addUniversity(University university) {
        universityRepository.save(university);
    }

    @Override
    public void updateUniversity(Integer id, University university) {
        University existingUniversity = universityRepository.findUniversityById(id);
        if (existingUniversity == null) {
            throw new ApiException("University with ID " + id + " not found");
        }
        existingUniversity.setName(university.getName());
        universityRepository.save(existingUniversity);
    }
    //@Override
    public void addStudentToUniversity(Integer universityId, Integer volunteerId) {
        University university = universityRepository.findById(universityId).orElseThrow(() ->
                new ApiException("University with ID " + universityId + " not found"));

        List<Integer> studentIds = university.getStudentIds();
        if (!studentIds.contains(volunteerId)) {
            if (!volunteerRepository.findVolunteerById(volunteerId).getEmploymentStatus().equalsIgnoreCase("university student")) {
                throw new ApiException("Volunteer with ID " + volunteerId + " is not a student");
            }
            studentIds.add(volunteerId);
            university.setStudentIds(studentIds);
            universityRepository.save(university);
        } else {
            throw new ApiException("Volunteer with ID " + volunteerId + " is already a student at this university");
        }
    }

    //@Override
    public void addSuggestedOpportunityToUniversity(Integer universityId, Integer opportunityId) {
        University university = universityRepository.findById(universityId).orElseThrow(() ->
                new ApiException("University with ID " + universityId + " not found"));

        List<Integer> suggestedOpportunityIds = university.getSuggestedOpportunityIds();
        if (!suggestedOpportunityIds.contains(opportunityId)) {
            suggestedOpportunityIds.add(opportunityId);
            university.setSuggestedOpportunityIds(suggestedOpportunityIds);
            universityRepository.save(university);
        } else {
            throw new ApiException("Opportunity with ID " + opportunityId + " is already suggested to this university");
        }
    }

    @Override
    public void deleteUniversity(Integer id) {
        University university = universityRepository.findUniversityById(id);
        if (university == null) {
            throw new ApiException("University with ID " + id + " not found");
        }
        universityRepository.delete(university);
    }
}
