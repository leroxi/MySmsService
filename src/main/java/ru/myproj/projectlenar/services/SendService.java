package ru.myproj.projectlenar.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.myproj.projectlenar.configs.AppProperties;
import ru.myproj.projectlenar.kafka.KafkaProducer;
import ru.myproj.projectlenar.mapper.ClientMapper;
import ru.myproj.projectlenar.model.ClientInfo;
import ru.myproj.projectlenar.model.KafkaMessage;
import ru.myproj.projectlenar.services.implementor.ClientServiceImpl;

import java.util.List;
@Slf4j
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
        clients.forEach(client -> {
            if (client.isMessageSend()) {
                return;
            }
            String messageText = client.getFullName() + ", в этом месяце для вас действует скидка " + appProperties.getDiscount() + "%";
            KafkaMessage message = new KafkaMessage();
            message.setPhone(client.getPhone());
            message.setMessage(messageText);
            kafkaProducer.sendMessage("messageSMS", message);
            client.setMessageSend(true);
            clientService.saveClient(clientMapper.toClient(client));
            System.out.println(messageText);
        });
    }
}
