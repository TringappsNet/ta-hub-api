package tahub.sdapitahub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tahub.sdapitahub.entity.Client;
import tahub.sdapitahub.repository.ClientRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }


    public List<Client> getClientViewAll() {
        return clientRepository.ClientViewAll();
    }


    public Optional<Client> getClientById(Long id) {
        return Optional.ofNullable(clientRepository.findById(id));
    }

    public Client createClient(Client client) {
        client.setCreatedAt(LocalDateTime.now());
        client.setLastUpdated(LocalDateTime.now());
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client client) {
        client.setClientId(id);
        client.setLastUpdated(LocalDateTime.now());
        return clientRepository.update(client);
    }


    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
