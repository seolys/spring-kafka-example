package seol.study.springkafka.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import seol.study.springkafka.domain.service.KafkaProduceService;
import seol.study.springkafka.domain.service.dto.MyMessage;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final KafkaProduceService producerService;

    @GetMapping("/publish")
    public String publish(final String message) {
        producerService.send(message);
        return "published as message : " + message;
    }

    @GetMapping("/publish2")
    public String publish2(final String message) {
        producerService.sendWithCallback(message);
        return "published as message : " + message;
    }

    @GetMapping("/publish3")
    public String publish2(final MyMessage message) {
        producerService.sendJson(message);
        return "published as message : " + message;
    }
}
