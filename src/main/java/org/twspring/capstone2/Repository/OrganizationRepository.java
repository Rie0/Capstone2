package org.twspring.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone2.Model.Organizations.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
     Organization findOrganizationById(Integer id);
}
