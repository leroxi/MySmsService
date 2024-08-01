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
// todo окей, ты сделал пакет имплементор (не видел на проектах, чтобы так делали, ну и ладно)
//      но что имплементит этот сервис?
public class SendService {
    private final KafkaProducer kafkaProducer;
    private final ClientServiceImpl clientService;

    // todo для такого, чаще используют ApplicationProperty
    @Value("${app.discount}")
    private int discount;


    public void send() {
        List<ClientInfo> clients = clientService.getAllClients();
        // todo тут тебе вернётся просто пустой лист и он не будет наллом
        if (clients == null || clients.isEmpty()) {
            return;
        }
        List<ClientInfo> filteredClients = clients.stream()
                .filter(client -> !client.isMessageSend())
                .collect(Collectors.toList());

        // todo стримы круто и ты умеешь их использовать, но зачем forEach циклом
        for (ClientInfo client : filteredClients) {
            String message = client.getFullName() + ", в этом месяце для вас действует скидка " + discount + "%";
            kafkaProducer.sendMessage("messageSMS", client.getPhone() + ": " + message);
            client.setMessageSend(true);
            clientService.saveClient(client);
        }
    }
}
