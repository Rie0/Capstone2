package org.twspring.capstone2.Service.Interfaces;

import org.twspring.capstone2.Model.Users.Admin;

import java.util.List;

public interface IAdminService {
    List<Admin> getAllAdmins();
    void addAdmin(Admin admin);
    void updateAdmin(Integer id, Admin admin);
    void deleteAdmin(Integer id);
}
