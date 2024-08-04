package ru.myproj.projectlenar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.myproj.projectlenar.model.Client;
import ru.myproj.projectlenar.model.ClientInfo;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "messageSend", target = "messageSend")
    ClientInfo toClientInfo(Client client);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "messageSend", target = "messageSend")
    Client toClient(ClientInfo clientInfo);
}
