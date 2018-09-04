package one.synergy.synergyone.service.impl;

import one.synergy.synergyone.model.*;
import one.synergy.synergyone.repository.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    private final String MSG = "Maybe method was changed.";

    @Mock
    ClientRepository clientRepository;
    private ClientServiceImpl clientService;
    private Order order;
    private Client client;
    @Before
    public void setUp() throws Exception {
        clientService = new ClientServiceImpl(clientRepository);

        Long id = 1L;
        client = new Client();
        client.setName("Alex");
        client.setSurname("Jush");
        client.setSex(Sex.MAN);
        client.setBirthday(LocalDate.of(1990, 2, 12));
        client.setItn(123456);
        client.setRoles(Collections.singleton(Role.USER));
        client.setId(id);

        order = new Order();
        order.setId(1L);
        order.setValue(12.12);
        order.setStatus(OrderStatus.inProcessing);
        order.setCurrency(Currency.UAH);
        order.setClient(client);
        order.setDate(LocalDate.now());
    }

    @Test
    public void entitiesNotNull() {
        assertNotNull(MSG, clientService);
        assertNotNull(MSG, client);
        assertNotNull(MSG, order);
    }

    @Test
    public void findAllIsCalled() {
        clientService.findAll();
        verify(clientRepository).findAll();
    }

    @Test
    public void findAllIsNull() {
        assertTrue(MSG, clientService.findAll().isEmpty());
    }

    @Test
    public void clientDataByIdIsCalled() {
        clientService.clientDataById(1L);
        verify(clientRepository).findById(1L);
    }

    @Test(expected = NoSuchElementException.class)
    public void clientByIdIsNull() {
        clientService.clientDataById(1L).get();
    }

    @Test
    public void createNewClientIsCalled() {
        clientService.createNewClient(client);
        verify(clientRepository).save(client);
    }

    @Test
    public void createNewClientIsNullReturned() {
        assertNull(MSG, clientService.createNewClient(client));
    }

    @Test
    public void updateClientByIdIsCalled() {
        clientService.updateClientById(1L, client);
        verify(clientRepository).save(client);
    }

    @Test
    public void updateClientByIdIsNullReturned() {
        assertNull(MSG, clientService.updateClientById(1L, client));
    }
}