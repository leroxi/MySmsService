package ru.myproj.projectlenar.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.myproj.projectlenar.model.KafkaMessage;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, KafkaMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, KafkaMessage message) {
        kafkaTemplate.send(topic, message);
    }
}
