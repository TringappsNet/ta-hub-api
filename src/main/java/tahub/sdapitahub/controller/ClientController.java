package tahub.sdapitahub.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tahub.sdapitahub.constants.ClientMsgs;
import tahub.sdapitahub.entity.Client;
import tahub.sdapitahub.service.ClientService;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Client", description = "Operations related to Clients")
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/clientPositions")
    public ResponseEntity<List<Client>> getClientViewAll() {
        List<Client> clients = clientService.getClientViewAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("client/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        Optional<Client> clientOptional = clientService.getClientById(id);
        if (clientOptional.isPresent()) {
            return new ResponseEntity<>(clientOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/client")
    public ResponseEntity<String> createClient(@RequestBody Client client) {
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
    public ResponseEntity<String> updateClient(@PathVariable("id") Long id, @RequestBody Client client) {
        if (!id.equals(client.getClientId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClientMsgs.ID_NOT_MATCHED.getMessage());
        }
        else {
             Client updatedClient = clientService.updateClient(id, client);
             return ResponseEntity.status(HttpStatus.OK).body(ClientMsgs.CLIENT_UPDATED.getMessage());
        }

    }

    @DeleteMapping("client/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).body(ClientMsgs.CLIENT_DELETED.getMessage());

    }
}
