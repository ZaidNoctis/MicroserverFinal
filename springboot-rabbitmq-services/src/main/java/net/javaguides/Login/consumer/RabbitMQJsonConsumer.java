package net.javaguides.Login.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.Login.dto.LoginDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQJsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
    private final ObjectMapper objectMapper = new ObjectMapper(); // Crear un serializador local

    @RabbitListener(queues = "${rabbitmq.queue.json.name}")
    public void Jsonconsumer(String jsonMessage) {
        try {
            // Deserializar el JSON manualmente a LoginDTO
            LoginDTO loginDTO = objectMapper.readValue(jsonMessage, LoginDTO.class);
            LOGGER.info("Received LoginDTO -> {}", loginDTO);
            // Procesar el LoginDTO aquí
        } catch (Exception e) {
            LOGGER.error("Error deserializing JSON message -> {}", e.getMessage());
        }
    }
}
