package ru.myprog.progectlenar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import ru.myprog.progectlenar.model.ClientInfo;
import ru.myprog.progectlenar.service.implementor.ClientServiceImpl;
import ru.myprog.progectlenar.service.implementor.SendService;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/api/v2")
@Tag(name = "Контроллер SMS сервиса адаптера")
public class UsersController {
    private final ClientServiceImpl clientService;
    private final SendService sendService;


    @GetMapping("/getClients")
    @Operation(summary = "Получение списка всех клиентов")
    public List<ClientInfo> getAllClients() {
        List<ClientInfo> clients = clientService.getAllClients();
        return clients;
    }

    @GetMapping("/getClient/{id}")
    @Operation(summary = "Получение одного клиента по его ID")
    public ClientInfo getClientById(@PathVariable int id) {
        return clientService.getClientById(id);
    }

    @Scheduled(cron = "0 0 0-19 * * *")
    @PostMapping("/kafka/send")
    public void send() {
        sendService.send();
    }
}
