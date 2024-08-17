package org.twspring.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone2.Model.Organizations.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
    University findUniversityById(Integer id);
}
