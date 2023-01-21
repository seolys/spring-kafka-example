package seol.study.springkafka.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import seol.study.springkafka.domain.service.dto.KafkaMessage;
import seol.study.springkafka.domain.service.dto.MyMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProduceService {

    private static final String TOPIC_NAME = "topic5";
    @Qualifier("kafkaTemplate")
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Qualifier("jsonKafkaTemplate")
    private final KafkaTemplate<String, KafkaMessage> jsonKafkaTemplate;

    public void send(final String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }

    public void sendWithCallback(final String message) {
        final ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(final Throwable ex) {
                log.error("Failed {} due to : {}", message, ex.getMessage(), ex);
            }

            @Override
            public void onSuccess(final SendResult<String, String> result) {
                log.info("Sent {} offset: {}", message, result.getRecordMetadata().offset());
            }
        });
    }

    public void sendJson(final MyMessage message) {
        jsonKafkaTemplate.send(TOPIC_NAME, KafkaMessage.of(message));
    }

}
