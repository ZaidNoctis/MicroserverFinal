package net.javaguides.Login.controller;

import net.javaguides.Login.publisher.RabbitMQJsonProducer;
import net.javaguides.Login.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

    private final RabbitMQJsonProducer jsonProducer;

    public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    // Endpoint ajustado para evitar conflicto con el otro controlador
    @PostMapping("/publish/json")
    public ResponseEntity<String> sendJsonMessage(@RequestBody LoginDTO user) {
        if (user == null) {
            return ResponseEntity.badRequest().body("Message cannot be empty");
        }
        jsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Message sent to RabbitMQ as JSON ... ");
    }
}
