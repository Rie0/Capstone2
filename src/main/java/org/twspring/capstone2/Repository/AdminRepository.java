package org.twspring.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.capstone2.Model.Users.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findAdminById(Integer id);
}
