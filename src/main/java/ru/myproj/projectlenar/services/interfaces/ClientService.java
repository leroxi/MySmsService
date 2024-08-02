package ru.myproj.projectlenar.services.interfaces;

import ru.myproj.projectlenar.model.Client;
import ru.myproj.projectlenar.model.ClientInfo;

import java.util.List;

public interface ClientService {
    List<ClientInfo > getAllClients();

    ClientInfo  getClientById(int id);

    void addAllClients(List<ClientInfo > clients);
}
