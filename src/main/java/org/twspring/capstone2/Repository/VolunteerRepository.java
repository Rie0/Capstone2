package org.twspring.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone2.Model.Users.Volunteer;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
    Volunteer findVolunteerById(Integer id);
}
