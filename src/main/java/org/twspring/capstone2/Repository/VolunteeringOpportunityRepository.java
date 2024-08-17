package org.twspring.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone2.Model.Volunteering.VolunteeringOpportunity;

import java.util.List;

@Repository
public interface VolunteeringOpportunityRepository extends JpaRepository<VolunteeringOpportunity, Integer> {
    VolunteeringOpportunity findVolunteeringOpportunityById(Integer id);
    List<VolunteeringOpportunity> findVolunteeringOpportunityByOrganizationId(Integer id);
}
