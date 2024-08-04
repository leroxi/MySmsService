package ru.myproj.projectlenar.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode
@Schema(description = "ДТО клиента")
public class ClientInfo {
    @Schema(description = "")
    private Integer id;
    @Schema(description = "")
    private String fullName;
    @Schema(description = "")
    private String phone;
    @Schema(description = "")
    private LocalDate birthday;
    @Schema(description = "")
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
