package ru.myprog.progectlenar.service.implementor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.myprog.progectlenar.model.ClientInfo;
import ru.myprog.progectlenar.repository.ClientRepository;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ShouldDoGetAllClients {
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientServiceImpl clientServiceImpl;


    @Test
    public void shouldDoGetClients() {
        List<ClientInfo> clients = getAllClients();

        Mockito.when(clientRepository.findAll()).thenReturn(clients);

        List<ClientInfo> result = clientServiceImpl.getAllClients();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }
    private List<ClientInfo> getAllClients() {
      ClientInfo clientInfo1 = new ClientInfo();
      ClientInfo clientInfo2 = new ClientInfo();
      clientInfo1.setId(1);
      clientInfo1.setFullName("Andrey Golkov");
      clientInfo1.setPhone("+79274533217");
      clientInfo1.setBirthday(LocalDate.ofEpochDay(1995-07-03));
      clientInfo1.setMessageSend(false);

      clientInfo2.setId(2);
      clientInfo2.setFullName("Nora Bendich");
      clientInfo2.setPhone("89065432107");
      clientInfo2.setBirthday(LocalDate.ofEpochDay(1992-07-27));
      clientInfo2.setMessageSend(true);
      return List.of(clientInfo1, clientInfo2);

    }
}
