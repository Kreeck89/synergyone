package one.synergy.synergyone.service.impl;

import one.synergy.synergyone.model.Client;
import one.synergy.synergyone.repository.ClientRepository;
import one.synergy.synergyone.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> clientDataById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client createNewClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClientById(Long id, Client client) {
        return clientRepository.findById(id)
                .map(newClient -> {
                    newClient.setName(client.getName());
                    newClient.setSurname(client.getSurname());
                    newClient.setSex(client.getSex());
                    newClient.setBirthday(client.getBirthday());
                    newClient.setItn(client.getItn());
                    return clientRepository.save(newClient);
                })
                .orElseGet(() -> {
                    client.setId(id);
                    return clientRepository.save(client);
                });
    }
}
