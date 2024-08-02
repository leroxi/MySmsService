package ru.myproj.projectlenar.model;


import java.time.LocalDate;

public record ClientInfo(
        String fullName,
        String phone,
        LocalDate birthday,
        boolean messageSend) {

}


