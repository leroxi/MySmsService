package ru.myproj.projectlenar.shedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.myproj.projectlenar.controller.UsersController;
import ru.myproj.projectlenar.services.SendService;

@ExtendWith(SpringExtension.class)
public class Shedulers {
    @Mock
    private SendService sendService;
    @InjectMocks
    private UsersController usersController;
    @Test
    public void shouldSendShedulerKafka(){
        usersController.send();
        verify(sendService, times(1)).send();
    }
}
