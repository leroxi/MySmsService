package ru.myprog.progectlenar;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.myprog.progectlenar.model.ClientInfo;

import java.util.List;

@FeignClient(name = "clients", url = "${clients.url}")
public interface ClientsFeignClient {
    @GetMapping("/api/v1/getAllClients")
    List<ClientInfo> getAllClients();

    @GetMapping("/api/v1/getClient/{clientId}")
    ClientInfo getClientById(@PathVariable("clientId") int id);
}
