package ru.myproj.projectlenar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.myproj.projectlenar.model.ClientInfo;
import ru.myproj.projectlenar.services.implementor.ClientServiceImpl;
import ru.myproj.projectlenar.services.SendService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
@Tag(name = "Контроллер SMS сервиса адаптера")
public class UsersController {
    private final ClientServiceImpl clientService;
    private final SendService sendService;

    // todo А ты пробовал вызвать ручку эту?
    @GetMapping("/get-сlients")
    @Operation(summary = "Получение списка всех клиентов")
    public List<ClientInfo> getAllClients() {
        return clientService.getAllClients();
    }

    // todo А ты пробовал вызвать ручку эту?
    @GetMapping("/get-сlient/{id}")
    @Operation(summary = "Получение одного клиента по его ID")
    public ClientInfo getClientById(@PathVariable int id) {
        return clientService.getClientById(id);
    }

    @PostMapping("/kafka/send")
    public void send() {
        sendService.send();
    }
}
