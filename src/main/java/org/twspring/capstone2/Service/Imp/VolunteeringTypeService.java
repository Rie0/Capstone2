package org.twspring.capstone2.Service.Imp;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone2.Api.ApiException;
import org.twspring.capstone2.Model.Volunteering.VolunteeringType;
import org.twspring.capstone2.Repository.VolunteeringTypeRepository;
import org.twspring.capstone2.Service.Interfaces.IVolunteeringTypeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteeringTypeService implements IVolunteeringTypeService {

    private final VolunteeringTypeRepository volunteeringTypeRepository;

    @Override
    public List<VolunteeringType> getAllVolunteeringTypes() {
        List<VolunteeringType> volunteeringTypes = volunteeringTypeRepository.findAll();
        if (volunteeringTypes.isEmpty()) {
            throw new EntityNotFoundException("No volunteering types found");
        }
        return volunteeringTypes;
    }

    @Override
    public void addVolunteeringType(VolunteeringType volunteeringType) {
        volunteeringTypeRepository.save(volunteeringType);
    }

    @Override
    public void updateVolunteeringType(Integer id, VolunteeringType volunteeringType) {
        VolunteeringType v = volunteeringTypeRepository.findVolunteeringTypeById(id);
        if (v == null) {
            throw new ApiException("Volunteering Type with ID " + id + " not found");
        }
        v.setName(volunteeringType.getName());
        volunteeringTypeRepository.save(v);
    }

    @Override
    public void deleteVolunteeringType(Integer id) {
        VolunteeringType v = volunteeringTypeRepository.findVolunteeringTypeById(id);
        if (v == null) {
            throw new ApiException("Volunteering Type with ID " + id + " not found");
        }
        volunteeringTypeRepository.delete(v);
    }
}
