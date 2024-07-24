package com.scheduler.services;

import com.scheduler.dtos.OccupationRequestDTO;
import com.scheduler.dtos.OccupationResponseDTO;
import com.scheduler.exceptions.InvalidOccupationException;
import com.scheduler.models.Attendant;
import com.scheduler.models.Occupation;
import com.scheduler.repositories.OccupationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OccupationService {

    @Autowired
    GetOrThrowNotFoundService GetOrThrowNotFoundService;

    @Autowired
    OccupationRepository occupationRepository;

    public List<OccupationResponseDTO> getOccupationsByAttendant(UUID attendantId){
        Attendant attendant = GetOrThrowNotFoundService.getAttendantOrThrowAttendantNotFound(attendantId);
        List<OccupationResponseDTO> occupations = occupationRepository.findByAttendant(attendant).stream().map(OccupationResponseDTO::new).toList();
        return occupations;
    }

    public List<OccupationResponseDTO> saveAttendantOccupations(List<OccupationRequestDTO> occupations){
        Attendant attendant = GetOrThrowNotFoundService.getAttendantOrThrowAttendantNotFound(occupations.get(0).attendantId());
        for (OccupationRequestDTO occupation : occupations){
            if (!attendant.getUuid().equals(occupation.attendantId())){
                throw new InvalidOccupationException();
            }
            Occupation newOccupation = new Occupation(occupation, attendant);
            occupationRepository.save(newOccupation);
        }
        List<OccupationResponseDTO> updatedOccupations = attendant.getOccupations().stream().map(OccupationResponseDTO::new).toList();
        return updatedOccupations;
    }

    public OccupationResponseDTO updateAttendantOccupation(OccupationRequestDTO occupationRequestDTO, Integer occupationId){
        Occupation occupation = GetOrThrowNotFoundService.getOccupationOrThrowOccupationNotFound(occupationId);
        BeanUtils.copyProperties(occupationRequestDTO, occupation, "attendantId");
        occupationRepository.save(occupation);
        return new OccupationResponseDTO(occupation);
    }

    public void deleteAttendantOccupation(Integer occupationId){
        Occupation occupation = GetOrThrowNotFoundService.getOccupationOrThrowOccupationNotFound(occupationId);
        occupationRepository.delete(occupation);
    }
}
