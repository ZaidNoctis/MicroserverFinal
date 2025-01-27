package net.javaguides.Login.controller;

import net.javaguides.Login.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// http://localhost:8080/api/v1/publish
@RequestMapping("/api/v1")
public class MessageController {

    private final RabbitMQProducer producer;
    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    // http://localhost:8080/api/v1/publish?message=hello
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        if (message == null || message.isBlank()) {
            return ResponseEntity.badRequest().body("Message cannot be empty");
        }
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ ... ");
    }

}
