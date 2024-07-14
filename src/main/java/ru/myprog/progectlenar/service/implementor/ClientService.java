package ru.myprog.progectlenar.service.implementor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myprog.progectlenar.model.ClientInfo;
import ru.myprog.progectlenar.repository.InMemoryClientsRep;

import java.util.List;

@Service
public class ClientService {
    private final InMemoryClientsRep repository;

    @Autowired
    public ClientService(InMemoryClientsRep repository) {
        this.repository = repository;
    }

    public List<ClientInfo> getFilteredClients() {
        return repository.getAllClients();
    }

    public ClientInfo getFilteredClientById(int id) {
        return repository.getClientById(id);
    }
}
