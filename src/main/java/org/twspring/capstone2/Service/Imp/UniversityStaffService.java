package org.twspring.capstone2.Service.Imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone2.Api.ApiException;
import org.twspring.capstone2.Model.Users.UniversityStaff;
import org.twspring.capstone2.Repository.UniversityStaffRepository;
import org.twspring.capstone2.Service.Interfaces.IUniversityStaffService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityStaffService implements IUniversityStaffService {

    private final UniversityStaffRepository universityStaffRepository;

    @Override
    public List<UniversityStaff> getAllUniversityStaff() {
        List<UniversityStaff> universityStaff = universityStaffRepository.findAll();
        if (universityStaff.isEmpty()) {
            throw new ApiException("No university staff found");
        }
        return universityStaff;
    }

    @Override
    public void addUniversityStaff(UniversityStaff universityStaff) {
        universityStaffRepository.save(universityStaff);
    }

    @Override
    public void updateUniversityStaff(Integer id, UniversityStaff universityStaff) {
        UniversityStaff existingStaff = universityStaffRepository.findUniversityStaffById(id);
        if (existingStaff == null) {
            throw new ApiException("University staff with ID " + id + " not found");
        }
        //commented fields are the ones that don't make sense to change
        existingStaff.setEmail(universityStaff.getEmail());
        existingStaff.setPhoneNumber(universityStaff.getPhoneNumber());
        existingStaff.setUsername(universityStaff.getUsername());
//        existingStaff.setFirstName(universityStaff.getFirstName());
//        existingStaff.setLastName(universityStaff.getLastName());
        existingStaff.setPassword(universityStaff.getPassword());
//        existingStaff.setUniversityId(universityStaff.getUniversityId());
        universityStaffRepository.save(existingStaff);
    }

    @Override
    public void deleteUniversityStaff(Integer id) {
        UniversityStaff universityStaff = universityStaffRepository.findUniversityStaffById(id);
        if (universityStaff == null) {
            throw new ApiException("University staff with ID " + id + " not found");
        }
        universityStaffRepository.delete(universityStaff);
    }
}
