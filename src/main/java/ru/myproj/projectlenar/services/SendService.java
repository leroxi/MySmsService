package ru.myproj.projectlenar.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.myproj.projectlenar.kafka.KafkaProducer;
import ru.myproj.projectlenar.mapper.ClientMapping;
import ru.myproj.projectlenar.model.ClientInfo;
import ru.myproj.projectlenar.services.implementor.ClientServiceImpl;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SendService {
    private final KafkaProducer kafkaProducer;
    private final ClientServiceImpl clientService;
    private final ClientMapping clientMapping;
    @Value("${app.discount}")
    private int discount;

    public void send() {
        List<ClientInfo> clients = clientService.getAllClients();
        if (clients.isEmpty()) {
            return;
        }
        clients.stream()
                .filter(client -> !client.isMessageSend())
                .forEach(client -> {
                    String message = client.getFullName() + ", в этом месяце для вас действует скидка " + discount + "%";
                    kafkaProducer.sendMessage("messageSMS", client.getPhone() + ": " + message);
                    client.setMessageSend(true);
                    clientService.saveClient(clientMapping.toClient(client));
                });
    }
}
