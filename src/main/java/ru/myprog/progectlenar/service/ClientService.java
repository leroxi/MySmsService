package ru.myprog.progectlenar.service;

import ru.myprog.progectlenar.model.ClientInfo;

import java.util.List;

public interface ClientService {
    List<ClientInfo> getAllClients();

    ClientInfo getClientById(int id);

    void addAllClients(List<ClientInfo> clients);
}
