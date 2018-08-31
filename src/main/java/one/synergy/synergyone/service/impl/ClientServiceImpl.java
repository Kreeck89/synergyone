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

    @Autowired
    private ClientRepository clientRepository;

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
    public Client updateClientById(Long id) {
        Optional<Client> byId = clientRepository.findById(id);
        return byId.map(client -> clientRepository.saveAndFlush(client)).orElse(null);
    }
}
