package org.twspring.capstone2.Service.Imp;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone2.Api.ApiException;
import org.twspring.capstone2.Model.Users.Admin;
import org.twspring.capstone2.Repository.AdminRepository;
import org.twspring.capstone2.Service.Interfaces.IAdminService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {

    private final AdminRepository adminRepository;

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        if (admins.isEmpty()) {
            throw new EntityNotFoundException("No admins found");
        }
        return admins;
    }

    @Override
    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void updateAdmin(Integer id, Admin admin) { //might add more attributes
        Admin a = adminRepository.findAdminById(id);
        if(a == null) {
            throw new ApiException("Admin with ID "+id+" not found");
        }
        //commented are the ones that doesn't make sense to change
        a.setEmail(admin.getEmail());
        a.setPhoneNumber(admin.getPhoneNumber());
        a.setUsername(admin.getUsername());
        a.setFirstName(admin.getFirstName());
        a.setLastName(admin.getLastName());
        a.setPassword(admin.getPassword());
        adminRepository.save(a);
    }

    @Override
    public void deleteAdmin(Integer id) {
        Admin a = adminRepository.findAdminById(id);
        if(a == null) {
            throw new ApiException("Admin with ID "+id+" not found");
        }
        adminRepository.delete(a);
    }
}
