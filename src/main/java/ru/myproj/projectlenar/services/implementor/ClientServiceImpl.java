
package ru.myproj.projectlenar.services.implementor;


import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.myproj.projectlenar.model.Client;
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


    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public void addAllClients(List<Client> clients) {
        clientRepository.saveAll(clients);
    }

    @Scheduled(initialDelay = 3600000, fixedDelay = 3600000)
    public void updateListOfClient() {
        List<Client> clients = clientsFeignClient.getAllClients();
        List<Client> filteredClients = clients.stream()
                .filter(client -> client.getPhone().endsWith("7"))
                .filter(client -> client.getBirthday().getMonth() == LocalDate.now().getMonth())
                .collect(Collectors.toList());
        addAllClients(filteredClients);
    }

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

}
