package org.twspring.capstone2.Service.Interfaces;

import org.twspring.capstone2.Model.Users.Organizer;

import java.util.List;

public interface IOrganizerService {
    List<Organizer> getAllOrganizers();
    void addOrganizer(Organizer organizer);
    void updateOrganizer(Integer id, Organizer organizer);
    void deleteOrganizer(Integer id);
}
