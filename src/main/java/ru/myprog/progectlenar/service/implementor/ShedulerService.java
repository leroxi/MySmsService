package ru.myprog.progectlenar.service.implementor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.myprog.progectlenar.model.ClientInfo;
import ru.myprog.progectlenar.repository.InMemoryClientsRep;
import ru.myprog.progectlenar.service.ClientsFeignInterface;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShedulerService {
    private final ClientsFeignInterface clientsFeignInterface;
    public InMemoryClientsRep repository;

    @Autowired
    public ShedulerService(ClientsFeignInterface clientsFeignInterface, InMemoryClientsRep repository) {
        this.clientsFeignInterface = clientsFeignInterface;
        this.repository = repository;
    }

    @Scheduled(initialDelay = 3600000, fixedDelay = 3600000)
    public void updateListOfClient() {
        List<ClientInfo> clients = clientsFeignInterface.getAllClients();
        List<ClientInfo> filteredClients = clients.stream()
                .filter(client -> client.getPhone().endsWith("7"))
                .filter(client -> client.getBirthday().getMonth() == LocalDate.now().getMonth())
                .collect(Collectors.toList());
        repository.addAllClients(filteredClients);
    }
}
