package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.constants.ClientMsgs;
import tahub.sdapitahub.dto.Client.ClientCreateDTO;
import tahub.sdapitahub.dto.Client.ClientUpdateDTO;
import tahub.sdapitahub.dto.Client.ClientDTO;
import tahub.sdapitahub.entity.Client;
import tahub.sdapitahub.service.ClientService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Client", description = "Operations related to Clients")
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        List<ClientDTO> clientDTOs = clients.stream().map(client -> {
            ClientDTO dto = new ClientDTO();
            dto.setClientId(client.getClientId());
            dto.setClientName(client.getClientName());
            dto.setClientSpocName(client.getClientSpocName());
            dto.setClientSpocContact(client.getClientSpocContact());
            dto.setClientLocation(client.getClientLocation());
            dto.setCreatedAt(client.getCreatedAt());
            dto.setLastUpdated(client.getLastUpdated());
            dto.setJobTitle(client.getJobTitle());
            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(clientDTOs, HttpStatus.OK);
    }

    @GetMapping("/clientPositions")
    public ResponseEntity<List<ClientDTO>> getClientViewAll() {
        List<Client> clients = clientService.getClientViewAll();
        List<ClientDTO> clientDTOs = clients.stream().map(client -> {
            ClientDTO dto = new ClientDTO();
            dto.setClientId(client.getClientId());
            dto.setClientName(client.getClientName());
            dto.setClientSpocName(client.getClientSpocName());
            dto.setClientSpocContact(client.getClientSpocContact());
            dto.setClientLocation(client.getClientLocation());
            dto.setCreatedAt(client.getCreatedAt());
            dto.setLastUpdated(client.getLastUpdated());
            dto.setJobTitle(client.getJobTitle());
            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(clientDTOs, HttpStatus.OK);
    }

    @GetMapping("client/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long id) {
        Optional<Client> clientOptional = clientService.getClientById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            ClientDTO dto = new ClientDTO();
            dto.setClientId(client.getClientId());
            dto.setClientName(client.getClientName());
            dto.setClientSpocName(client.getClientSpocName());
            dto.setClientSpocContact(client.getClientSpocContact());
            dto.setClientLocation(client.getClientLocation());
            dto.setCreatedAt(client.getCreatedAt());
            dto.setLastUpdated(client.getLastUpdated());
            dto.setJobTitle(client.getJobTitle());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/client")

    public ResponseEntity<Client> createClient(@RequestBody ClientCreateDTO clientCreateDTO) {
        Client client = new Client.Builder()

                .clientName(clientCreateDTO.getClientName())
                .jobTitle(clientCreateDTO.getJobTitle())
                .clientSpocName(clientCreateDTO.getClientSpocName())
                .clientSpocContact(clientCreateDTO.getClientSpocContact())
                .clientLocation(clientCreateDTO.getClientLocation())
                .jobTitle(clientCreateDTO.getJobTitle())
                .build();

        Client createdClient = clientService.createClient(client);

        if( createdClient!=null) {
//        return new ResponseEntity<>(createdCandidate, HttpStatus.CREATED);
            return ResponseEntity.status(200).body(ClientMsgs.CLIENT_CREATED.getMessage());
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ClientMsgs.CLIENT_NOT_CREATED.getMessage());
        }
    }

    @PutMapping("client/{id}")

    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody ClientUpdateDTO clientUpdateDTO) {
        Optional<Client> existingClientOptional = clientService.getClientById(id);
        if (existingClientOptional.isPresent()) {
            Client existingClient = existingClientOptional.get();
            existingClient.setClientId(clientUpdateDTO.getClientId());
            existingClient.setClientName(clientUpdateDTO.getClientName());
            existingClient.setClientSpocName(clientUpdateDTO.getClientSpocName());
            existingClient.setClientSpocContact(clientUpdateDTO.getClientSpocContact());
            existingClient.setClientLocation(clientUpdateDTO.getClientLocation());
            existingClient.setJobTitle(clientUpdateDTO.getJobTitle());
            Client updatedClient = clientService.updateClient(id, existingClient);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("client/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).body(ClientMsgs.CLIENT_DELETED.getMessage());

    }
}
