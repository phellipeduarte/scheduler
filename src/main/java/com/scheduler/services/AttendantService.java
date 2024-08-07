package com.scheduler.services;

import com.scheduler.dtos.AppointmentResponseDTO;
import com.scheduler.dtos.AttendantRequestDTO;
import com.scheduler.dtos.AttendantResponseDTO;
import com.scheduler.models.Attendant;
import com.scheduler.models.Establishment;
import com.scheduler.repositories.AttendantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<AppointmentResponseDTO> getAppointments(UUID attendantId){
        Attendant attendant = GetOrThrowNotFoundService.getAttendantOrThrowAttendantNotFound(attendantId);
        List<AppointmentResponseDTO> appointments = attendant.getAppointments().stream().filter(appointment -> appointment.getStart().isAfter(LocalDateTime.now())).map(AppointmentResponseDTO::new).toList();
        return appointments;
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
        BeanUtils.copyProperties(attendantRequestDTO, attendant, "establishmentId");
        attendantRepository.save(attendant);
        AttendantResponseDTO updatedAttendant = new AttendantResponseDTO(attendant);
        return updatedAttendant;
    }

    public void deleteAttendant(UUID uuid) {
        Attendant attendant = GetOrThrowNotFoundService.getAttendantOrThrowAttendantNotFound(uuid);
        attendantRepository.delete(attendant);
    }
}
