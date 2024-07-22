package com.scheduler.controllers;

import com.scheduler.dtos.AppointmentRequestDTO;
import com.scheduler.dtos.AppointmentResponseDTO;
import com.scheduler.exceptions.AppointmentNotFoundException;
import com.scheduler.services.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/{uuid}")
    public ResponseEntity getAppointment(@PathVariable(value = "uuid") UUID appointmentId){
        try {
            AppointmentResponseDTO appointment = appointmentService.getAppointment(appointmentId);
            return ResponseEntity.status(HttpStatus.OK).body(appointment);
        } catch (AppointmentNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity saveAppointment(@RequestBody @Valid AppointmentRequestDTO appointmentRequestDTO){
        try {
            AppointmentResponseDTO newAppointment = appointmentService.saveAppointment(appointmentRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);
        } catch (RuntimeException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
