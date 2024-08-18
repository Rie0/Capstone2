package org.twspring.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone2.Model.Volunteering.VolunteeringType;

@Repository
public interface VolunteeringTypeRepository extends JpaRepository<VolunteeringType, Integer> {

    VolunteeringType findVolunteeringTypeById(Integer id);
}
