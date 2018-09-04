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

    @GetMapping(value = "/getById/{id}")
    public @ResponseBody
    Client getById(@PathVariable("id") Long id) {
        return clientService.clientDataById(id).get();
    }

    @PostMapping(value = "/createNew")
    public Client createNewClient(@RequestBody Client client) {
        return clientService.createNewClient(client);
    }

    @PutMapping(value = "/updateClient/{id}")
    public @ResponseBody
    Client updateClient(@RequestBody Client client,
                        @PathVariable("id") Long id) {
        System.out.println(id);
        System.out.println(client);
        return clientService.updateClientById(id, client);
    }
}
