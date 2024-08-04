package ru.myproj.projectlenar.services.implementor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ru.myproj.projectlenar.kafka.KafkaProducer;
import ru.myproj.projectlenar.mapper.ClientMapper;
import ru.myproj.projectlenar.model.ClientInfo;
import ru.myproj.projectlenar.model.Client;
import ru.myproj.projectlenar.services.SendService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SendServiceTest {
    @Mock
    KafkaProducer kafkaProducer;
    @Mock
    private ClientServiceImpl clientService;
    @InjectMocks
    private SendService sendService;
    private int discount = 10;
    @Mock
    private ClientMapper clientMapper;
    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(sendService, "discount", discount);
    }
    @Test
    public void sendTest() {
        LocalDate birthday1 = LocalDate.of(1995, 7, 3);
        LocalDate birthday2 = LocalDate.of(1992, 7, 27);
        ClientInfo client1 = new ClientInfo(1, "Andrey Golkov", "+79274533217", birthday1, false);
        ClientInfo client2 = new ClientInfo(2, "Nora Bendich", "89065432107", birthday2, true);
        List<ClientInfo> clientList = List.of(client1, client2);

        when(clientService.getAllClients()).thenReturn(clientList);
        when(clientMapper.toClient(client1)).thenReturn(new Client(1, "Andrey Golkov", "+79274533217", birthday1, true));
        when(clientMapper.toClient(client2)).thenReturn(new Client(2, "Nora Bendich", "89065432107", birthday2, true));

        sendService.send();

        verify(kafkaProducer).sendMessage("messageSMS", "+79274533217: Andrey Golkov, в этом месяце для вас действует скидка 10%");
        verify(clientService).saveClient(new Client(1, "Andrey Golkov", "+79274533217", birthday1, true));
        verify(clientService, never()).saveClient(new Client(2, "Nora Bendich", "89065432107", birthday2, true));
    }
}
