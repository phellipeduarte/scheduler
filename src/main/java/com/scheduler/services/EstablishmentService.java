package com.scheduler.services;

import com.scheduler.dtos.EstablishmentRequestDTO;
import com.scheduler.dtos.EstablishmentResponseDTO;
import com.scheduler.exceptions.EstablishmentNotFoundException;
import com.scheduler.models.Establishment;
import com.scheduler.repositories.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstablishmentService {

    @Autowired
    EstablishmentRepository establishmentRepository;


    public List<EstablishmentResponseDTO> getAllEstablishments() {
        List<EstablishmentResponseDTO> establishments = establishmentRepository.findAll().stream().map(EstablishmentResponseDTO::new).toList();
        return establishments;
    }

    public EstablishmentResponseDTO getEstablishment(Integer id) throws EstablishmentNotFoundException {
        Optional<Establishment> establishmentOptional = establishmentRepository.findById(id);
        if(establishmentOptional.isEmpty()){
            throw new EstablishmentNotFoundException();
        }
        Establishment establishment = establishmentOptional.get();
        return new EstablishmentResponseDTO(establishment);
    }

    public Establishment saveEstablishment(EstablishmentRequestDTO establishmentRequestDTO){
        Establishment establishment = new Establishment(establishmentRequestDTO);
        return establishmentRepository.save(establishment);
    }
}
