package ru.myprog.progectlenar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import ru.myprog.progectlenar.kafka.KafkaProducer;
import ru.myprog.progectlenar.model.ClientInfo;
import ru.myprog.progectlenar.service.implementor.ClientServiceImpl;

import java.util.List;

@EnableKafka
@RestController
@EnableFeignClients
@RequestMapping("/api/v2")

public class UsersController  {
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
    public List<ClientInfo> getAllClients() {
        List<ClientInfo> clients = clientService.getAllClients();
        System.out.println("Филтроваенные : " + clients);
        return clients;
    }

    @GetMapping("/getClient/{userId}")
    public ClientInfo getClientById(@PathVariable int clientId) {
        return clientService.getClientById(clientId);
    }
    @Scheduled(cron = "0 0 0-19 * * *")
    @PostMapping("/kafka/send")
    public String send(@RequestParam int id) {
        ClientInfo client = clientService.getClientById(id);
        kafkaProducer.sendMessage("phone : " + client.getPhone() + "\n" + "message " + client.getFullName() + ", в этом месяце для вас действует скидка" + discount +"%");
        return "Success";
    }
}
