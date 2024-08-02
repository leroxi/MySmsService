package ru.myproj.projectlenar.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledTasksService {
    private final SendService sendService;

    @Scheduled(cron = "0 0 0-19 * * *")
    public void scheduledSend() {
        sendService.send();
    }
}
