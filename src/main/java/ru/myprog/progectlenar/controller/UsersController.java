package ru.myprog.progectlenar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import ru.myprog.progectlenar.kafka.KafkaProducer;
import ru.myprog.progectlenar.model.ClientInfo;
import ru.myprog.progectlenar.service.implementor.ClientServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@EnableKafka
@RestController
@EnableFeignClients
@RequestMapping("/api/v2")
@Api(description = "Контроллер SMS сервиса адаптера")
public class UsersController {
    private final ClientServiceImpl clientService;
    private final KafkaProducer kafkaProducer;
    @Value("${app.discount}")
    private int discount;

    @Autowired
    public UsersController(ClientServiceImpl clientService, KafkaProducer kafkaProducer) {
        this.clientService = clientService;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/getClients")
    @ApiOperation("Получение списка всех клиентов")
    public List<ClientInfo> getAllClients() {
        List<ClientInfo> clients = clientService.getAllClients();
        System.out.println("Филтроваенные : " + clients);
        return clients;
    }

    @GetMapping("/getClient/{userId}")
    @ApiOperation("Получение одного клиента по его ID")
    public ClientInfo getClientById(@PathVariable int id) {
        return clientService.getClientById(id);
    }

    @Scheduled(cron = "0 0 0-19 * * *")
    @PostMapping("/kafka/send")
    public void send() {
        List<ClientInfo> clients = clientService.getAllClients();
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
