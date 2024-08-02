package ru.myproj.projectlenar;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.myproj.projectlenar.model.Client;

import java.util.List;

@FeignClient(name = "clients", url = "${clients.url}")
public interface ClientsFeignClient {
    @GetMapping("/api/v1/getAllClients")
        // todo опять же через дто
    List<Client> getAllClients();

    @GetMapping("/api/v1/getClient/{clientId}")
        // todo опять же через дто
    Client getClientById(@PathVariable("clientId")  int id);
}
