package net.javaguides.Login.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue("javaguides");
    }

    @Bean
    public Queue jsonQueue() {
        return new Queue("javaguides_json");
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("javaguides_exchange");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("javaguides_routing_key");
    }

    @Bean
    public Binding jsonBinding(Queue jsonQueue, DirectExchange exchange) {
        return BindingBuilder.bind(jsonQueue).to(exchange).with("javaguides_routing_json_key");
    }
}
