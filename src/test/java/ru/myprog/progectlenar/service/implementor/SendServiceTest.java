package ru.myprog.progectlenar.service.implementor;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ru.myprog.progectlenar.kafka.KafkaProducer;
import ru.myprog.progectlenar.model.ClientInfo;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SendServiceTest {
    @Mock
    KafkaProducer kafkaProducer;
    @Mock
    private ClientServiceImpl clientService;
    @InjectMocks
    private SendService sendService;
    private int discount = 10;
    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(sendService, "discount", discount);
    }
    @Test
    public void sendTest() {
        LocalDate birthday1 = LocalDate.of(1995, 7, 3);
        LocalDate birthday2 = LocalDate.of(1992, 7, 27);
        ClientInfo clientInfo1 = new ClientInfo(1, "Andrey Golkov", "+79274533217", birthday1, false);
        ClientInfo clientInfo2 = new ClientInfo(2, "Nora Bendich", "89065432107", birthday2, true);
        List<ClientInfo> clientInfoList = List.of(clientInfo1, clientInfo2);
        when(clientService.getAllClients()).thenReturn(clientInfoList);
        sendService.send();
        verify(kafkaProducer, times(1)).sendMessage("messageSMS", "+79274533217: Andrey Golkov, в этом месяце для вас действует скидка 10%");
        verify(clientService, times(1)).saveClient(clientInfo1);
        verify(clientService, times(0)).saveClient(clientInfo2);

    }
}
