package ru.myproj.projectlenar.model;


import lombok.Data;

import java.time.LocalDate;

@Data
// todo а сваггер где?
public class ClientInfo {
    private String fullName;
    private String phone;
    private LocalDate birthday;
    private boolean messageSend;
}

