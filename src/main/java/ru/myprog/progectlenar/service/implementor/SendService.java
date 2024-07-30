package ru.myprog.progectlenar.service.implementor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.myprog.progectlenar.kafka.KafkaProducer;
import ru.myprog.progectlenar.model.ClientInfo;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class SendService {
    private final KafkaProducer kafkaProducer;
    private final ClientServiceImpl clientService;
    @Value("${app.discount}")
    private int discount;


    public void send(){
        List<ClientInfo> clients = clientService.getAllClients();
        if (clients == null || clients.isEmpty()) {
            return;
        }
        List<ClientInfo> filteredClients = clients.stream()
                .filter(client -> !client.isMessageSend())
                .collect(Collectors.toList());
        for (ClientInfo client : filteredClients) {
            String message = client.getFullName() + ", в этом месяце для вас действует скидка " + discount + "%";
            kafkaProducer.sendMessage("messageSMS", client.getPhone() + ": " + message);
            client.setMessageSend(true);
            clientService.saveClient(client);
        }
    }
}
