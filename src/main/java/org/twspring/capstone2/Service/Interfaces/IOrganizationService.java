package org.twspring.capstone2.Service.Interfaces;

import org.twspring.capstone2.Model.Organizations.Organization;

import java.util.List;

public interface IOrganizationService {
    List<Organization> getAllOrganizations();
    void addOrganization(Integer adminId, Organization organization);
    void updateOrganization(Integer id, Organization organization);
    void deleteOrganization(Integer id);
}
