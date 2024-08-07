package ru.myproj.projectlenar.model;

import lombok.Data;

@Data
public class KafkaMessage {
    private String phone;
    private String message;
}
