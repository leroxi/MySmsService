package ru.myproj.projectlenar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myproj.projectlenar.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
