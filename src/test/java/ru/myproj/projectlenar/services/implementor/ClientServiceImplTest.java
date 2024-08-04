package ru.myproj.projectlenar.services.implementor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.myproj.projectlenar.mapper.ClientMapper;
import ru.myproj.projectlenar.model.Client;
import ru.myproj.projectlenar.model.ClientInfo;
import ru.myproj.projectlenar.repository.ClientRepository;
import ru.myproj.projectlenar.ClientsFeignClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientServiceImplTest {

    @Mock
    private ClientsFeignClient clientsFeignClient;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    private List<Client> clientList;
    private List<ClientInfo> clientInfoList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        LocalDate birthday1 = LocalDate.of(1995, 7, 3);
        LocalDate birthday2 = LocalDate.of(1992, 7, 27);

        Client client1 = new Client(1, "Andrey Golkov", "+79274533217", birthday1, false);
        Client client2 = new Client(2, "Nora Bendich", "89065432107", birthday2, true);
        clientList = List.of(client1, client2);

        ClientInfo clientInfo1 = new ClientInfo(1, "Andrey Golkov", "+79274533217", birthday1, false);
        ClientInfo clientInfo2 = new ClientInfo(2, "Nora Bendich", "89065432107", birthday2, true);
        clientInfoList = List.of(clientInfo1, clientInfo2);

        when(clientMapper.toClient(any(ClientInfo.class))).thenAnswer(invocation -> {
            ClientInfo clientInfo = invocation.getArgument(0);
            return new Client(
                    clientInfo.getId(),
                    clientInfo.getFullName(),
                    clientInfo.getPhone(),
                    clientInfo.getBirthday(),
                    clientInfo.isMessageSend()
            );
        });

        when(clientMapper.toClientInfo(any(Client.class))).thenAnswer(invocation -> {
            Client client = invocation.getArgument(0);
            return new ClientInfo(
                    client.getId(),
                    client.getFullName(),
                    client.getPhone(),
                    client.getBirthday(),
                    client.isMessageSend()
            );
        });
    }

    @Test
    public void shouldDoGetClients() {
        when(clientRepository.findAll()).thenReturn(clientList);
        List<ClientInfo> result = clientServiceImpl.getAllClients();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Andrey Golkov", result.get(0).getFullName());
        assertEquals("+79274533217", result.get(0).getPhone());
    }

    @Test
    public void shouldReturnClientById() {
        Client expectedClient = clientList.get(0);
        when(clientRepository.findById(1)).thenReturn(Optional.of(expectedClient));
        ClientInfo result = clientServiceImpl.getClientById(1);
        assertNotNull(result);
        assertEquals(expectedClient.getFullName(), result.getFullName());
        assertEquals(expectedClient.getPhone(), result.getPhone());
    }

    @Test
    public void shouldAddAllClients() {
        when(clientRepository.saveAll(anyList())).thenReturn(clientList);
        clientServiceImpl.addAllClients(clientInfoList);
        verify(clientRepository, times(1)).saveAll(clientList);
    }

    @Test
    public void shouldUpdateListOfClient() {
        when(clientsFeignClient.getAllClients()).thenReturn(clientInfoList);
        List<Client> filteredClients = clientList.stream()
                .filter(client -> client.getPhone().endsWith("7"))
                .filter(client -> client.getBirthday().getMonth() == LocalDate.now().getMonth())
                .collect(Collectors.toList());
        clientServiceImpl.updateListOfClient();
        verify(clientRepository, times(1)).saveAll(filteredClients);
    }
}