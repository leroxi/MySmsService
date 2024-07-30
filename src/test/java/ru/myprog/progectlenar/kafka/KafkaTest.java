package ru.myprog.progectlenar.kafka;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

@ExtendWith(MockitoExtension.class)
public class KafkaTest {

    @Mock
    KafkaTemplate<String, String> kafkaTemplate;
    @InjectMocks
    KafkaProducer kafkaProducer;
    @Test
    public void kafkaProducerTest(){
        String topic = "test1";
        String message = "test2";
        kafkaProducer.sendMessage(topic, message);
        verify(kafkaTemplate, times(1)).send(topic, message);
    }


}
