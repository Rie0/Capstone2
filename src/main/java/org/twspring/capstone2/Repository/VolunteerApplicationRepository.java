package org.twspring.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.twspring.capstone2.Model.Volunteering.VolunteerApplication;

import java.util.List;

@Repository
public interface VolunteerApplicationRepository extends JpaRepository<VolunteerApplication, Integer> {
    VolunteerApplication findVolunteerApplicationById(Integer id);

    List<VolunteerApplication> findVolunteerApplicationByVolunteerId(Integer volunteerId);
    List<VolunteerApplication>  findVolunteerApplicationByOpportunityId(Integer opportunityId);

    @Query ("SELECT a FROM VolunteerApplication a WHERE a.opportunityId=?1 AND a.status='Pending'")
    List<VolunteerApplication> findVolunteerApplicationWithPendingStatus(Integer opportunityId);

    @Query("SELECT a FROM VolunteerApplication a WHERE a.opportunityId=?1 AND (a.suitability='Excellent' OR a.suitability='Good') ORDER BY a.suitability")
    List<VolunteerApplication> findBestQualifiedVolunteerApplications(Integer opportunityId);
}
