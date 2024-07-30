package ru.myprog.progectlenar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myprog.progectlenar.model.ClientInfo;

public interface ClientRepository extends JpaRepository<ClientInfo, Integer> {
}
