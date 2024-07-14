package ru.myprog.progectlenar.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "Clients")
public class ClientInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "messageSend")
    private boolean messageSend;
}
