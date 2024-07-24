package com.scheduler.controllers;

import com.scheduler.dtos.OccupationRequestDTO;
import com.scheduler.dtos.OccupationResponseDTO;
import com.scheduler.exceptions.AttendantNotFoundException;
import com.scheduler.exceptions.OccupationNotFoundException;
import com.scheduler.services.OccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/occupation")
public class OccupationController {

    @Autowired
    OccupationService occupationService;

    @GetMapping("/attendant/{uuid}")
    public ResponseEntity getOccupationsByAttendantId(@PathVariable(value = "uuid")UUID attendantId){
        try{
            List<OccupationResponseDTO> occupations = occupationService.getOccupationsByAttendant(attendantId);
            return ResponseEntity.status(HttpStatus.OK).body(occupations);
        } catch (AttendantNotFoundException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity saveOccupations(@RequestBody List<OccupationRequestDTO> occupationsRequest){
        try {
            List<OccupationResponseDTO> occupations = occupationService.saveAttendantOccupations(occupationsRequest);
            return ResponseEntity.status(HttpStatus.OK).body(occupations);
        } catch (AttendantNotFoundException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOccupation(@PathVariable(value = "id") Integer occupationId, @RequestBody OccupationRequestDTO occupationRequestDto){
        try {
            OccupationResponseDTO occupationResponseDTO = occupationService.updateAttendantOccupation(occupationRequestDto, occupationId);
            return ResponseEntity.status(HttpStatus.OK).body(occupationResponseDTO);
        } catch (AttendantNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOccupation(@PathVariable(value = "id") Integer id){
        try {
            occupationService.deleteAttendantOccupation(id);
            return ResponseEntity.status(HttpStatus.OK).body("Carga horária removida permanentemente.");
        } catch (OccupationNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
