package ru.myproj.projectlenar.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "ДТО клиента")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
}

