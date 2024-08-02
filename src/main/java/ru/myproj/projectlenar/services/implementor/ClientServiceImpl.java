
package ru.myproj.projectlenar.services.implementor;


import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.myproj.projectlenar.mapper.ClientMapping;
import ru.myproj.projectlenar.model.Client;
import ru.myproj.projectlenar.model.ClientInfo;
import ru.myproj.projectlenar.repository.ClientRepository;
import ru.myproj.projectlenar.services.interfaces.ClientService;
import ru.myproj.projectlenar.ClientsFeignClient;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientsFeignClient clientsFeignClient;
    private final ClientRepository clientRepository;
    private final ClientMapping clientMapping;


    @Override
    public List<ClientInfo> getAllClients() {
        return clientRepository.findAll().stream().map(clientMapping::toClientInfo).collect(Collectors.toList());
    }

    @Override
    public ClientInfo getClientById(int id) {
        return clientRepository
                .findById(id)
                .map(clientMapping::toClientInfo)
                .orElse(null);
    }

    @Override
    public void addAllClients(List<ClientInfo> clients) {
        List<Client> entities = clients.stream().map(clientMapping::toClient).collect(Collectors.toList());
        clientRepository.saveAll(entities);
    }

    @Scheduled(initialDelay = 3600000, fixedDelay = 3600000)
    public void updateListOfClient() {
        List<ClientInfo> clients = clientsFeignClient.getAllClients();
        List<Client> filteredClients = clients.stream()
                .filter(client -> client.getPhone().endsWith("7"))
                .filter(client -> client.getBirthday().getMonth() == LocalDate.now().getMonth())
                .map(clientMapping::toClient)
                .collect(Collectors.toList());
        clientRepository.saveAll(filteredClients);
    }

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

}
