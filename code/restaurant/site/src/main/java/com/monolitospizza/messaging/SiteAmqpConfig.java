package com.monolitospizza.messaging;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Matt Stine
 */
@EnableRabbit
@Configuration
public class SiteAmqpConfig {

    @Bean
    public RabbitTemplate amqpTemplate() {
        return new RabbitTemplate(new CachingConnectionFactory("localhost", 5672));
    }
}
