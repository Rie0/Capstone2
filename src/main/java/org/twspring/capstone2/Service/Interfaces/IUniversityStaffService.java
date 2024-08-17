package org.twspring.capstone2.Service.Interfaces;

import org.twspring.capstone2.Model.Users.UniversityStaff;

import java.util.List;

public interface IUniversityStaffService {

    public List<UniversityStaff> getAllUniversityStaff();

    public void addUniversityStaff(UniversityStaff universityStaff);

    public void updateUniversityStaff(Integer id, UniversityStaff universityStaff);

    public void deleteUniversityStaff(Integer id);
}
