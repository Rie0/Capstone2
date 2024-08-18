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
        // Verify the organization
        Organization organization = organizationRepository.findOrganizationById(organizationId);
        if (organization == null) {
            throw new ApiException("Organization with ID " + organizationId + " not found");
        }

        // Verify the organizer and check if they are a supervisor
        Organizer organizer = organizerRepository.findOrganizerById(organizerId);
        if (organizer == null) {
            throw new ApiException("Organizer with ID " + organizerId + " not found");
        }
        if (organizer.getOrganizationId()!=organizationId) {
            throw new ApiException("Organizer with ID " + organizerId + " does not belong to the organization");
        }
        if (!"SUPERVISOR".equals(organizer.getRole())) {
            throw new ApiException("Organizer with ID " + organizerId + " is not authorized to add volunteering opportunities");
        }
        volunteeringOpportunity.setOrganizationId(organizationId);
        volunteeringOpportunity.setSupervisorId(organizerId);
        volunteeringOpportunity.setCurrentCapacity(0);

        volunteeringOpportunityRepository.save(volunteeringOpportunity);
    }

    @Override
    public void updateVolunteeringOpportunity(Integer id, VolunteeringOpportunity volunteeringOpportunity) {

        VolunteeringOpportunity existingOpportunity = volunteeringOpportunityRepository.findVolunteeringOpportunityById(id);
        if (existingOpportunity == null) {
            throw new ApiException("Volunteering opportunity with ID " + id + " not found");
        }

        // Update the opportunity
        existingOpportunity.setTitle(volunteeringOpportunity.getTitle());
        existingOpportunity.setDescription(volunteeringOpportunity.getDescription());
        existingOpportunity.setStartDate(volunteeringOpportunity.getStartDate());
        existingOpportunity.setEndDate(volunteeringOpportunity.getEndDate());
        existingOpportunity.setMaxCapacity(volunteeringOpportunity.getMaxCapacity());
        existingOpportunity.setLocation(volunteeringOpportunity.getLocation());
        existingOpportunity.setWorkType(volunteeringOpportunity.getWorkType());
        existingOpportunity.setRequiredGender(volunteeringOpportunity.getRequiredGender());
        existingOpportunity.setRequiredEmploymentStatus(volunteeringOpportunity.getRequiredEmploymentStatus());
        existingOpportunity.setMinAge(volunteeringOpportunity.getMinAge());
        existingOpportunity.setPhysicallyFit(volunteeringOpportunity.isPhysicallyFit());
        existingOpportunity.setAvailableForWeekends(volunteeringOpportunity.isAvailableForWeekends());
        existingOpportunity.setHasDriversLicense(volunteeringOpportunity.isHasDriversLicense());
        existingOpportunity.setCanWorkWithChildren(volunteeringOpportunity.isCanWorkWithChildren());
        existingOpportunity.setCanWorkWithElderly(volunteeringOpportunity.isCanWorkWithElderly());
        existingOpportunity.setHasFirstAidCertification(volunteeringOpportunity.isHasFirstAidCertification());
        existingOpportunity.setCanTravel(volunteeringOpportunity.isCanTravel());

        // Save the updated opportunity
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

