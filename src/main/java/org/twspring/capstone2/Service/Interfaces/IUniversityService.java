package org.twspring.capstone2.Service.Interfaces;

import org.twspring.capstone2.Model.Organizations.University;

import java.util.List;

public interface IUniversityService {
    public List<University> getAllUniversities();

    public void addUniversity(University university);

    public void updateUniversity(Integer id, University university);

    public void deleteUniversity(Integer id);
}