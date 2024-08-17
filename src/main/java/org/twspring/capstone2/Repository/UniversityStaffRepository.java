package org.twspring.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone2.Model.Users.UniversityStaff;

@Repository
public interface UniversityStaffRepository extends JpaRepository<UniversityStaff, Integer> {
    UniversityStaff findUniversityStaffById(Integer id);
}
