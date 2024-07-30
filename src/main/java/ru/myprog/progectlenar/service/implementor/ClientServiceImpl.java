
package ru.myprog.progectlenar.service.implementor;


import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.myprog.progectlenar.model.ClientInfo;
import ru.myprog.progectlenar.repository.ClientRepository;
import ru.myprog.progectlenar.service.ClientService;
import ru.myprog.progectlenar.ClientsFeignClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientsFeignClient clientsFeignClient;
    private final ClientRepository clientRepository;

    @Override
    public List<ClientInfo> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public ClientInfo getClientById(int id) {
        Optional<ClientInfo> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            return optionalClient.get();
        }else {
            return null;
        }
    }

    @Override
    public void addAllClients(List<ClientInfo> clients) {
        clientRepository.saveAll(clients);
    }

    @Scheduled(initialDelay = 3600000, fixedDelay = 3600000)
    public void updateListOfClient() {
        List<ClientInfo> clients = clientsFeignClient.getAllClients();
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
