package com.monolitospizza.messaging;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Matt Stine
 */
@EnableRabbit
@Configuration
@Profile("site")
public class SiteAmqpConfig {
}
