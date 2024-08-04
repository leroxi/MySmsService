package ru.myproj.projectlenar.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myproj.projectlenar.configs.AppProperties;
import ru.myproj.projectlenar.kafka.KafkaProducer;
import ru.myproj.projectlenar.mapper.ClientMapper;
import ru.myproj.projectlenar.model.ClientInfo;
import ru.myproj.projectlenar.services.implementor.ClientServiceImpl;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SendService {
    private final KafkaProducer kafkaProducer;
    private final ClientServiceImpl clientService;
    private final ClientMapper clientMapper;
    private final AppProperties appProperties;


    public void send() {
        List<ClientInfo> clients = clientService.getAllClients();
        if (clients.isEmpty()) {
            return;
        }
        clients.stream()
                .filter(client -> !client.isMessageSend())
                .forEach(client -> {
                    String message = client.getFullName() + ", в этом месяце для вас действует скидка " + appProperties.getDiscount() + "%";
                    kafkaProducer.sendMessage("messageSMS", client.getPhone() + ": " + message);
                    client.setMessageSend(true);
                    clientService.saveClient(clientMapper.toClient(client));
                });
    }
}
