package ru.myprog.progectlenar.repository;

import org.springframework.stereotype.Repository;
import ru.myprog.progectlenar.model.ClientInfo;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryClientsRep {
    private final List<ClientInfo> clients = new ArrayList<>();

    public List<ClientInfo> getAllClients() {
        return new ArrayList<>(clients);
    }

    public ClientInfo getClientById(int id) {
        return clients.stream().filter(client -> client.getId() == id).findFirst().orElse(null);
    }

    public void addAllClients(List<ClientInfo> clients) {
        this.clients.clear();
        this.clients.addAll(clients);
    }
}