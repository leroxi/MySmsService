
package ru.myprog.progectlenar.service.implementor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.myprog.progectlenar.model.ClientInfo;
import ru.myprog.progectlenar.repository.ClientRepository;
import ru.myprog.progectlenar.service.ClientServiceInterface;
import ru.myprog.progectlenar.service.ClientsFeignInterface;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientServiceInterface {
    private final ClientsFeignInterface clientsFeignInterface;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientsFeignInterface clientsFeignInterface, ClientRepository clientRepository) {
        this.clientsFeignInterface = clientsFeignInterface;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientInfo> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public ClientInfo getClientById(int id) {
        return clientRepository.getOne(id);
    }

    @Override
    public void addAllClients(List<ClientInfo> clients) {
        clientRepository.saveAll(clients);
    }

    @Scheduled(initialDelay = 3600000, fixedDelay = 3600000)
    public void updateListOfClient() {
        List<ClientInfo> clients = clientsFeignInterface.getAllClients();
        List<ClientInfo> filteredClients = clients.stream()
                .filter(client -> client.getPhone().endsWith("7"))
                .filter(client -> client.getBirthday().getMonth() == LocalDate.now().getMonth())
                .collect(Collectors.toList());
        addAllClients(filteredClients);
    }

    public void saveClient(ClientInfo client) {
        clientRepository.save(client);
    }
}
