package one.synergy.synergyone.service;

import one.synergy.synergyone.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll();

    Optional<Client> clientDataById(Long id);

    Client createNewClient(Client client);

    Client updateClientById(Long id, Client client);
}
