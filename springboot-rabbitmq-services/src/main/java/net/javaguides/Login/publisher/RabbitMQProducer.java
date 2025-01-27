package net.javaguides.Login.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.Login.dto.LoginDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper; // Serializador JSON

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void sendMessage(String message) {
        LOGGER.info("Message sent -> {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    public void sendLoginDTO(LoginDTO loginDTO) {
        try {
            // Serializar el DTO a JSON
            String jsonMessage = objectMapper.writeValueAsString(loginDTO);
            LOGGER.info("LoginDTO sent -> {}", jsonMessage);
            rabbitTemplate.convertAndSend(exchange, routingJsonKey, jsonMessage);
        } catch (Exception e) {
            LOGGER.error("Error while sending LoginDTO -> {}", e.getMessage());
            throw new RuntimeException("Error al serializar el LoginDTO", e);
        }
    }
}
