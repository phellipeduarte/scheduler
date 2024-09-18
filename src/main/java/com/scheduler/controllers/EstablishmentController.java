package com.scheduler.controllers;

import com.scheduler.dtos.EstablishmentRequestDTO;
import com.scheduler.dtos.EstablishmentResponseDTO;
import com.scheduler.exceptions.EstablishmentNotFoundException;
import com.scheduler.services.EstablishmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {

    @Autowired
    EstablishmentService establishmentService;

    @GetMapping("/")
    public ResponseEntity<List<EstablishmentResponseDTO>> getEstablishments(){
        return ResponseEntity.status(HttpStatus.OK).body(establishmentService.getAllEstablishments());
    }

    @GetMapping("/{id}")
    public ResponseEntity getEstablishment(@PathVariable(value = "id") Integer id){
        try {
            EstablishmentResponseDTO establishment = establishmentService.getEstablishment(id);
            return ResponseEntity.status(HttpStatus.OK).body(establishment);
        } catch (EstablishmentNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity saveEstablishment(@RequestBody @Valid EstablishmentRequestDTO establishmentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(establishmentService.saveEstablishment(establishmentRequestDTO));
    }

}
