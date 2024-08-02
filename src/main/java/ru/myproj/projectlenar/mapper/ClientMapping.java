package ru.myproj.projectlenar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.myproj.projectlenar.model.Client;
import ru.myproj.projectlenar.model.ClientInfo;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapping {
    ClientInfo toClientInfo(Client client);
    List<ClientInfo> toClientInfosList(List<Client> clients);
    Client toClient(ClientInfo clientInfo);
    List<Client> toClientList(List<ClientInfo> clientInfo);


}
