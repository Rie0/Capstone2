package org.twspring.capstone2.Service.Imp;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone2.Model.Organizations.University;
import org.twspring.capstone2.Repository.UniversityRepository;
import org.twspring.capstone2.Service.Interfaces.IUniversityService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService implements IUniversityService {

    private final UniversityRepository universityRepository;

    @Override
    public List<University> getAllUniversities() {
        List<University> universities = universityRepository.findAll();
        if (universities.isEmpty()) {
            throw new EntityNotFoundException("No universities found");
        }
        return universities;
    }

    @Override
    public void addUniversity(University university) {
        universityRepository.save(university);
    }

    @Override
    public void updateUniversity(Integer id, University university) {
        University existingUniversity = universityRepository.findUniversityById(id);
        if (existingUniversity == null) {
            throw new EntityNotFoundException("University with ID " + id + " not found");
        }
        existingUniversity.setName(university.getName());
        universityRepository.save(existingUniversity);
    }

    @Override
    public void deleteUniversity(Integer id) {
        University university = universityRepository.findUniversityById(id);
        if (university == null) {
            throw new EntityNotFoundException("University with ID " + id + " not found");
        }
        universityRepository.delete(university);
    }
}
