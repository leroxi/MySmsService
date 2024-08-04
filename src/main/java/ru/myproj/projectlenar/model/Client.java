package ru.myproj.projectlenar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CLIENTS")
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "birthday")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @Column(name = "message_send")
    private boolean messageSend;
    public Integer getId() {
        return id;
    }

    // todo аннотация Data
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean isMessageSend() {
        return messageSend;
    }

    public void setMessageSend(boolean messageSend) {
        this.messageSend = messageSend;
    }
}


