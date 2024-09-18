package com.scheduler.controllers;

import com.scheduler.dtos.ClientRequestDTO;
import com.scheduler.dtos.ClientResponseDTO;
import com.scheduler.exceptions.ClientAlreadyExistsException;
import com.scheduler.exceptions.ClientNotFoundException;
import com.scheduler.models.Client;
import com.scheduler.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/")
    public ResponseEntity<List<ClientResponseDTO>> getClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getAllClients());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity getClientByUuid(@PathVariable(value = "uuid") UUID uuid){
        try {
            ClientResponseDTO client = new ClientResponseDTO(clientService.getClientByUuid(uuid));
            return ResponseEntity.status(HttpStatus.OK).body(client);
        } catch (ClientNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity getClientByPhone(@PathVariable(value = "phone") String phone){
        try {
            ClientResponseDTO client = new ClientResponseDTO(clientService.getClientByPhone(phone));
            return ResponseEntity.status(HttpStatus.OK).body(client);
        } catch (ClientNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }

    @PostMapping("/")
    public ResponseEntity saveClient(@RequestBody @Valid ClientRequestDTO clientRequestDTO){
        try {
            Client newClient = clientService.saveClient(clientRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
        } catch (ClientAlreadyExistsException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
