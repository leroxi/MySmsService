package ru.myprog.progectlenar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "Clients")
public class ClientInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "birthday")
    @JsonFormat(pattern ="yyyy-MM-dd")
    private LocalDate birthday;
    @Column(name = "message_send")
    private boolean messageSend;

    public ClientInfo(int id, String fullName, String phone, LocalDate birthday, boolean messageSend) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.birthday = birthday;
        this.messageSend = messageSend;
    }
}
