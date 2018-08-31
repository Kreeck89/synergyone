package one.synergy.synergyone.controller;

import one.synergy.synergyone.model.Client;
import one.synergy.synergyone.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/all")
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @GetMapping(value = "/getById")
    public @ResponseBody Client getById(@RequestParam Long id) {
        return clientService.clientDataById(id).get();
    }

    @PostMapping(value = "/createNew")
    public Client createNewClient(@RequestBody Client client) {
        return clientService.createNewClient(client);
    }

    @PutMapping(value = "/updateClient")
    public Client updateClient(@RequestParam Long id) {
        return clientService.updateClientById(id);
    }
}
