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
// todo а зачем автовайрд? всё и без него работает
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/api/v2")
@Tag(name = "Контроллер SMS сервиса адаптера")
public class UsersController {
    private final ClientServiceImpl clientService;
    private final SendService sendService;


    // todo не оч важное замечание
    //      чаще всего нейминг запроса пишут через тире
    //      например, get-clients
    @GetMapping("/getClients")
    @Operation(summary = "Получение списка всех клиентов")
    // todo возвращать энтити в контроллере не оч хорошо
    //      для этого используют отдельные дто, плюсом к ней нужно подключить сваггер
    //      для маппинга в дто, например, используется mapstruct (чаще всего)
    public List<ClientInfo> getAllClients() {
        // todo сделай сразу return
        List<ClientInfo> clients = clientService.getAllClients();
        return clients;
    }

    @GetMapping("/getClient/{id}")
    @Operation(summary = "Получение одного клиента по его ID")
    public ClientInfo getClientById(@PathVariable int id) {
        return clientService.getClientById(id);
    }


    // todo странное решение, чем руководствовался
    //      чтобы оставить крону в контроллере?
    @Scheduled(cron = "0 0 0-19 * * *")
    @PostMapping("/kafka/send")
    public void send() {
        sendService.send();
    }
}
