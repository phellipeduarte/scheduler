package com.scheduler.services;

import com.scheduler.dtos.AttendantRequestDTO;
import com.scheduler.dtos.AttendantResponseDTO;
import com.scheduler.models.Attendant;
import com.scheduler.models.Establishment;
import com.scheduler.repositories.AttendantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AttendantService {
    
    @Autowired
    AttendantRepository attendantRepository;

    @Autowired
    GetOrThrowNotFoundService GetOrThrowNotFoundService;

    public List<AttendantResponseDTO> getAttendants(Integer establishmentId) {
        Establishment establishment = GetOrThrowNotFoundService.getEstablishmentOrThrowEstablishmentNotFound(establishmentId);
        List<AttendantResponseDTO> attendants = establishment.getAttendants().stream().map(AttendantResponseDTO::new).toList();
        return attendants;
    }

    public AttendantResponseDTO getAttendant(UUID uuid) {
        AttendantResponseDTO attendant = new AttendantResponseDTO(GetOrThrowNotFoundService.getAttendantOrThrowAttendantNotFound(uuid));
        return attendant;
    }

    public List<AttendantResponseDTO> saveAttendant(AttendantRequestDTO attendantRequestDTO) {
        Establishment establishment = GetOrThrowNotFoundService.getEstablishmentOrThrowEstablishmentNotFound(attendantRequestDTO.establishmentId());
        Attendant newAttendant = new Attendant(attendantRequestDTO, establishment);
        attendantRepository.save(newAttendant);
        List<AttendantResponseDTO> updatedAttendants = establishment.getAttendants().stream().map(AttendantResponseDTO::new).toList();
        return updatedAttendants;
    }

    public AttendantResponseDTO updateAttendant(UUID uuid, AttendantRequestDTO attendantRequestDTO) {
        Attendant attendant = GetOrThrowNotFoundService.getAttendantOrThrowAttendantNotFound(uuid);
        BeanUtils.copyProperties(attendantRequestDTO, attendant);
        attendantRepository.save(attendant);
        AttendantResponseDTO updatedAttendant = new AttendantResponseDTO(attendant);
        return updatedAttendant;
    }

    public void deleteAttendant(UUID uuid) {
        Attendant attendant = GetOrThrowNotFoundService.getAttendantOrThrowAttendantNotFound(uuid);
        attendantRepository.delete(attendant);
    }
}
