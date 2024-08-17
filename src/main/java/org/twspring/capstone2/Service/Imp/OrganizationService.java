package org.twspring.capstone2.Service.Imp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone2.Api.ApiException;
import org.twspring.capstone2.Model.Organizations.Organization;
import org.twspring.capstone2.Repository.OrganizationRepository;
import org.twspring.capstone2.Service.Interfaces.IOrganizationService;

import java.util.List;

@Service
@AllArgsConstructor
public class OrganizationService implements IOrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        if (organizations.isEmpty()) {
            throw new ApiException("No organizations found");
        }
        return organizations;
    }

    @Override
    public void addOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

    @Override
    public void updateOrganization(Integer id, Organization organization) { //add more
        Organization org = organizationRepository.findOrganizationById(id);
        if (org == null) {
            throw new ApiException("Organization with ID"+ id +"not found");
        }
        org.setName(organization.getName());
        organizationRepository.save(org);

    }

    @Override
    public void deleteOrganization(Integer id) {
        Organization org = organizationRepository.findOrganizationById(id);
        if (org == null) {
            throw new ApiException("Organization with ID"+ id +"not found");
        }
        organizationRepository.delete(org);
    }
}
