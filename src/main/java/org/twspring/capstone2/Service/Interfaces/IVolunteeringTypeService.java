package org.twspring.capstone2.Service.Interfaces;

import org.twspring.capstone2.Model.Volunteering.VolunteeringType;

import java.util.List;

public interface IVolunteeringTypeService {

    List<VolunteeringType> getAllVolunteeringTypes();

    void addVolunteeringType(VolunteeringType volunteeringType);

    void updateVolunteeringType(Integer id, VolunteeringType volunteeringType);

    void deleteVolunteeringType(Integer id);
}
