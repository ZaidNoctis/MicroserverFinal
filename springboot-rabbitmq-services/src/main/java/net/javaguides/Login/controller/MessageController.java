package net.javaguides.Login.controller;

import net.javaguides.Login.dto.LoginDTO;
import net.javaguides.Login.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final RabbitMQProducer producer;

    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        if (message == null || message.isBlank()) {
            return ResponseEntity.badRequest().body("Message cannot be empty");
        }
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ ... ");
    }

    @PostMapping("/publish/login")
    public ResponseEntity<String> sendLogin(@RequestBody LoginDTO loginDTO) {
        try {
            // Añade la fecha y hora actual al DTO
            producer.sendLoginDTO(loginDTO);

            return ResponseEntity.ok("Login data sent to RabbitMQ ... ");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el login");
        }
    }
}
