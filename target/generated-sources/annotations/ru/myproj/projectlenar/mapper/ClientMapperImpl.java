package ru.myproj.projectlenar.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.myproj.projectlenar.model.Client;
import ru.myproj.projectlenar.model.ClientInfo;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-04T19:34:09+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientInfo toClientInfo(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientInfo clientInfo = new ClientInfo();

        clientInfo.setId( client.getId() );
        clientInfo.setFullName( client.getFullName() );
        clientInfo.setPhone( client.getPhone() );
        clientInfo.setBirthday( client.getBirthday() );
        clientInfo.setMessageSend( client.isMessageSend() );

        return clientInfo;
    }

    @Override
    public Client toClient(ClientInfo clientInfo) {
        if ( clientInfo == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( clientInfo.getId() );
        client.setFullName( clientInfo.getFullName() );
        client.setPhone( clientInfo.getPhone() );
        client.setBirthday( clientInfo.getBirthday() );
        client.setMessageSend( clientInfo.isMessageSend() );

        return client;
    }
}
