package ru.myproj.projectlenar.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode
@Schema(description = "ДТО клиента")
@AllArgsConstructor
@NoArgsConstructor
public class ClientInfo {
    @Schema(description = "Индефикатор нашего клиента")
    private Integer id;
    @Schema(description = "Полное имя нашего клиента")
    private String fullName;
    @Schema(description = "Номер телефона нашего клиента")
    private String phone;
    @Schema(description = "День рождение нашего клиента")
    private LocalDate birthday;
    @Schema(description = "Статус отправленного сообщения клиенту")
    private boolean messageSend;

    public Integer getId() {
        return id;
    }

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
