package seol.study.springkafka.application.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import seol.study.springkafka.domain.service.dto.KafkaMessage;
import seol.study.springkafka.domain.service.dto.MyMessage;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private static final String TOPIC_NAME = "topic5";
    private final ObjectMapper objectMapper;


//    @KafkaListener(topics = TOPIC_NAME)
//    public void listenMessage(final String jsonMessage) {
//        try {
//            final var myMessage = objectMapper.readValue(jsonMessage, new TypeReference<KafkaMessage<MyMessage>>() {
//            });
//            log.info("Received message: {}, orgMessage: {}", myMessage, jsonMessage);
//        } catch (final JsonProcessingException e) {
//            log.error("Failed to parse json message : {}", jsonMessage, e);
//            throw new RuntimeException(e);
//        }
//    }

    @KafkaListener(topics = TOPIC_NAME)
    public void listenMessage(final KafkaMessage<MyMessage> myMessage) {
        log.info("Received message: {}", myMessage);
    }
}
