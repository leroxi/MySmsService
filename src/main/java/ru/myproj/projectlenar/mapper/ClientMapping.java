package ru.myproj.projectlenar.mapper;

import org.mapstruct.Mapper;
import ru.myproj.projectlenar.model.Client;
import ru.myproj.projectlenar.model.ClientInfo;

@Mapper(componentModel = "spring")
// todo обычно называют клайент маппер
public interface ClientMapping {
    ClientInfo toClientInfo(Client client);

    Client toClient(ClientInfo clientInfo);
}
