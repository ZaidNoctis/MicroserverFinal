package net.javaguides.Login.controller;

import net.javaguides.Login.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import net.javaguides.Login.dto.LoginDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {
    private final RabbitMQJsonProducer jsonProducer;
    public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer= jsonProducer;
    }
    // http://localhost:8080/api/v1/publish?message=hello
    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody LoginDTO user) {
        if (user == null) {
            return ResponseEntity.badRequest().body("Message cannot be empty");
        }
        jsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Message sent to RabbitMQ ... ");
    }

}
