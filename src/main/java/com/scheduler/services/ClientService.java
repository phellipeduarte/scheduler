package com.scheduler.services;

import com.scheduler.dtos.ClientRequestDTO;
import com.scheduler.dtos.ClientResponseDTO;
import com.scheduler.exceptions.ClientAlreadyExistsException;
import com.scheduler.exceptions.ClientNotFoundException;
import com.scheduler.models.Client;
import com.scheduler.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {


    @Autowired
    ClientRepository clientRepository;

    public List<ClientResponseDTO> getAllClients(){
        List<ClientResponseDTO> clients = clientRepository.findAll().stream().map(ClientResponseDTO::new).toList();
        return clients;
    }

    public Client getClientByUuid(UUID uuid) throws ClientNotFoundException{
        Optional<Client> clientOptional = clientRepository.findByUuid(uuid);
        if(clientOptional.isEmpty()){
            throw new ClientNotFoundException();
        }
        Client client = clientOptional.get();
        return client;
    }

    public Client getClientByPhone(String phone) throws ClientNotFoundException{
        Optional<Client> clientOptional = clientRepository.findByPhone(phone);
        if(clientOptional.isEmpty()){
            throw new ClientNotFoundException();
        }
        Client client = clientOptional.get();
        return client;
    }

    public Client saveClient(ClientRequestDTO clientRequestDTO) throws ClientAlreadyExistsException {
        Optional<Client> clientOptional = clientRepository.findByPhone(clientRequestDTO.phone());
        if(clientOptional.isPresent()){
            throw new ClientAlreadyExistsException();
        }
        Client client = new Client(clientRequestDTO);
        return clientRepository.save(client);
    }
}
