package org.twspring.capstone2.Service.Interfaces;

import org.twspring.capstone2.Model.Users.Volunteer;

import java.util.List;

public interface IVolunteerService {
    List<Volunteer> getAllVolunteers();

    //
    Volunteer getVolunteerProfileForWatching(Integer volunteerId);

    Volunteer getVolunteerProfileForOrganizer(Integer organizerId, Integer volunteerId);

    void addVolunteer(Volunteer volunteer);
    void updateVolunteer(Integer id, Volunteer volunteer);
    void deleteVolunteer(Integer id);
}
