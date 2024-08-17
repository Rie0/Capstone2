package org.twspring.capstone2.Service.Interfaces;

import org.twspring.capstone2.Model.Volunteering.VolunteeringOpportunity;

import java.util.List;

public interface IVolunteeringOpportunityService {

    List<VolunteeringOpportunity> getAllVolunteeringOpportunities();
    List<VolunteeringOpportunity> getVolunteeringOpportunitiesByOrganization(Integer organizationId);
    //search
    void addVolunteeringOpportunity(Integer OrganizationId, Integer OrganizerId, VolunteeringOpportunity volunteeringOpportunity);
    void updateVolunteeringOpportunity(Integer id, VolunteeringOpportunity volunteeringOpportunity);
    void deleteVolunteeringOpportunity(Integer id);
}
