package org.twspring.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone2.Model.Users.Organizer;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
    Organizer findOrganizerById(Integer id);
}
