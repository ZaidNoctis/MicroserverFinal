package net.javaguides.Login.consumer;

import net.javaguides.Login.dto.LoginDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
    @RabbitListener(queues = "${rabbitmq.queue.json.name}")
    public void Jsonconsumer(LoginDTO user){
        LOGGER.info("Recibido mensaje -> {}", user);
    }
}
