package com.scheduler.controllers;

import com.scheduler.dtos.AppointmentResponseDTO;
import com.scheduler.dtos.AttendantRequestDTO;
import com.scheduler.dtos.AttendantResponseDTO;
import com.scheduler.exceptions.AttendantNotFoundException;
import com.scheduler.exceptions.EstablishmentNotFoundException;
import com.scheduler.services.AttendantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/attendant")
public class AttendantController {

    @Autowired
    AttendantService attendantService;

    @GetMapping("/establishment/{establishment_id}")
    public ResponseEntity getAttendants(@PathVariable(value = "establishment_id") Integer establishmentId){
        try{
            List<AttendantResponseDTO> attendants = attendantService.getAttendants(establishmentId);
            return ResponseEntity.status(HttpStatus.OK).body(attendants);
        } catch (EstablishmentNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/{uuid}")
    public ResponseEntity getAttendant(@PathVariable(value = "uuid") UUID uuid){
        try {
            AttendantResponseDTO attendant = attendantService.getAttendant(uuid);
            return ResponseEntity.status(HttpStatus.OK).body(attendant);
        } catch (AttendantNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/{uuid}/appointments")
    public ResponseEntity getAppointments(@PathVariable(value = "uuid") UUID uuid){
        try {
            List<AppointmentResponseDTO> appointments = attendantService.getAppointments(uuid);
            return ResponseEntity.status(HttpStatus.OK).body(appointments);
        } catch (AttendantNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity saveAttendant(@RequestBody @Valid AttendantRequestDTO attendantRequestDTO){
        List<AttendantResponseDTO> updatedAttendants = attendantService.saveAttendant(attendantRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedAttendants);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity updateAttendant(@PathVariable(value = "uuid") UUID uuid, @RequestBody @Valid AttendantRequestDTO attendantRequestDTO){
        try {
            AttendantResponseDTO attendant = attendantService.updateAttendant(uuid, attendantRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(attendant);
        } catch (AttendantNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deleteAttendant(@PathVariable(value = "uuid") UUID uuid){
        try {
            attendantService.deleteAttendant(uuid);
            return ResponseEntity.status(HttpStatus.OK).body("Atendente removido permanentemente.");
        } catch (AttendantNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
