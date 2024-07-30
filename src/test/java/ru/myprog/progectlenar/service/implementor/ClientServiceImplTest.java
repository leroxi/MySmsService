package ru.myprog.progectlenar.service.implementor;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.myprog.progectlenar.ClientsFeignClient;
import ru.myprog.progectlenar.model.ClientInfo;
import ru.myprog.progectlenar.repository.ClientRepository;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientsFeignClient clientsFeignClient;
    @InjectMocks
    private ClientServiceImpl clientServiceImpl;
    private List<ClientInfo> clientInfoList;

    @BeforeEach
    void setUp() {
        clientInfoList = getAllClientsTester();
    }

    @Test
    public void shouldDoGetClients() {
        List<ClientInfo> clients = getAllClientsTester();

        Mockito.when(clientRepository.findAll()).thenReturn(clients);

        List<ClientInfo> result = clientServiceImpl.getAllClients();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void shouldReturnClientById() {
        List<ClientInfo> clients = getAllClientsTester();
        ClientInfo expectedClient = clients.get(0);
        Mockito.when(clientRepository.findById(1)).thenReturn(Optional.of(expectedClient));
        ClientInfo result = clientServiceImpl.getClientById(1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedClient, result);
    }

    @Test
    public void shouldReturnupdateListOfClient() {
        when(clientsFeignClient.getAllClients()).thenReturn(clientInfoList);
        clientServiceImpl.updateListOfClient();
        List<ClientInfo> expectedFilteredClients = clientInfoList.stream()
                .filter(client -> client.getPhone().endsWith("7"))
                .filter(client -> client.getBirthday().getMonth() == LocalDate.now().getMonth())
                .collect(Collectors.toList());
        verify(clientRepository, times(1)).saveAll(expectedFilteredClients);
    }

    @Test
    public void shouldAddAllClients() {
        List<ClientInfo> clients = getAllClientsTester();
        clientServiceImpl.addAllClients(clients);
        verify(clientRepository, times(1)).saveAll(clients);
    }


    private List<ClientInfo> getAllClientsTester() {
        LocalDate birthday1 = LocalDate.of(1995, 7, 3);
        LocalDate birthday2 = LocalDate.of(1992, 7, 27);
        ClientInfo clientInfo1 = new ClientInfo(1, "Andrey Golkov", "+79274533217", birthday1, false);
        ClientInfo clientInfo2 = new ClientInfo(2, "Nora Bendich", "89065432107", birthday2, true);
        return List.of(clientInfo1, clientInfo2);

    }
}
