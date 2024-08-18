package org.twspring.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone2.Model.Volunteering.VolunteerProgress;

import java.util.List;

@Repository
public interface VolunteerProgressRepository extends JpaRepository<VolunteerProgress, Integer> {
    VolunteerProgress findVolunteerProgressById(Integer id);
    List<VolunteerProgress> findVolunteerProgressByVolunteerId(Integer id);
    List<VolunteerProgress> findVolunteerProgressByOpportunityId(Integer id);
}
