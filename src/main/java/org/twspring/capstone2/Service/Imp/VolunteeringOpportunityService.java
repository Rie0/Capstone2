package org.twspring.capstone2.Service.Imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone2.Api.ApiException;
import org.twspring.capstone2.Model.Organizations.Organization;
import org.twspring.capstone2.Model.Users.Organizer;
import org.twspring.capstone2.Model.Volunteering.VolunteeringOpportunity;
import org.twspring.capstone2.Repository.OrganizationRepository;
import org.twspring.capstone2.Repository.OrganizerRepository;
import org.twspring.capstone2.Repository.VolunteeringOpportunityRepository;
import org.twspring.capstone2.Service.Interfaces.IVolunteeringOpportunityService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteeringOpportunityService implements IVolunteeringOpportunityService {
    private final VolunteeringOpportunityRepository volunteeringOpportunityRepository;
    private final OrganizationRepository organizationRepository;
    private final OrganizerRepository organizerRepository;

    @Override
    public List<VolunteeringOpportunity> getAllVolunteeringOpportunities() {
        List<VolunteeringOpportunity> opportunities = volunteeringOpportunityRepository.findAll();
        if (opportunities.isEmpty()) {
            throw new ApiException("No volunteering opportunities found");
        }
        return opportunities;
    }

    @Override
    public List<VolunteeringOpportunity> getVolunteeringOpportunitiesByOrganization(Integer organizationId) {
        List<VolunteeringOpportunity> opportunities = volunteeringOpportunityRepository.findVolunteeringOpportunityByOrganizationId(organizationId);
        if (opportunities.isEmpty()) {
            throw new ApiException("No volunteering opportunities found for the organization with ID " + organizationId);
        }
        return opportunities;
    }

    @Override
    public void addVolunteeringOpportunity(Integer organizationId, Integer organizerId, VolunteeringOpportunity volunteeringOpportunity) {
        Organization organization = organizationRepository.findOrganizationById(organizationId);
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organization == null) {
            throw new ApiException("Organization with ID " + organizationId + " not found");
        }
        if (organizer == null) {
            throw new ApiException("Organizer with ID " + organizerId + " not found");
        }

        volunteeringOpportunity.setOrganizationId(organizationId);
        volunteeringOpportunity.setOrganizerId(organizerId);
        volunteeringOpportunityRepository.save(volunteeringOpportunity);
    }

    @Override
    public void updateVolunteeringOpportunity(Integer id, VolunteeringOpportunity volunteeringOpportunity) {
        VolunteeringOpportunity existingOpportunity = volunteeringOpportunityRepository.findVolunteeringOpportunityById(id);
        if (existingOpportunity == null) {
            throw new ApiException("Volunteering opportunity with ID " + id + " not found");
        }

        existingOpportunity.setTitle(volunteeringOpportunity.getTitle());
        existingOpportunity.setCurrentCapacity(volunteeringOpportunity.getCurrentCapacity());
        existingOpportunity.setMaxCapacity(volunteeringOpportunity.getMaxCapacity());

        volunteeringOpportunityRepository.save(existingOpportunity);
    }

    @Override
    public void deleteVolunteeringOpportunity(Integer id) {
        VolunteeringOpportunity opportunity = volunteeringOpportunityRepository.findVolunteeringOpportunityById(id);
        if (opportunity == null) {
            throw new ApiException("Volunteering opportunity with ID " + id + " not found");
        }
        volunteeringOpportunityRepository.delete(opportunity);
    }
}

