package ru.myprog.progectlenar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import ru.myprog.progectlenar.kafka.KafkaProducer;
import ru.myprog.progectlenar.model.ClientInfo;
import ru.myprog.progectlenar.repository.InMemoryClientsRep;
import ru.myprog.progectlenar.service.implementor.ClientService;

import java.util.List;

@EnableKafka
@RestController
@EnableFeignClients
@RequestMapping("/api/v2")

public class UsersController {
    private final ClientService clientService;
    private final KafkaProducer kafkaProducer;
    private final InMemoryClientsRep clientsRep;
    @Value("${app.discount}")
    private int discount;

    @Autowired
    public UsersController(ClientService clientService, KafkaProducer kafkaProducer, InMemoryClientsRep clientsRep) {
        this.clientService = clientService;
        this.kafkaProducer = kafkaProducer;
        this.clientsRep = clientsRep;
    }

    @GetMapping("/getClients")
    public List<ClientInfo> getAllClients() {
        List<ClientInfo> clients = clientService.getFilteredClients();
        System.out.println("Филтроваенные : " + clients);
        return clients;
    }

    @GetMapping("/getClient/{userId}")
    public ClientInfo getUserById(@PathVariable int userId) {
        return clientService.getFilteredClientById(userId);
    }
    @Scheduled(cron = "0 0 0-19 * * *")
    @PostMapping("/kafka/send")
    public String send(@RequestParam int id) {
        ClientInfo client = clientsRep.getClientById(id);
        kafkaProducer.sendMessage("phone : " + client.getPhone() + "\n" + "message " + client.getFullName() + ", в этом месяце для вас действует скидка" + discount +"%");
        return "Success";
    }
}
