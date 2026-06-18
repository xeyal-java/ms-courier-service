package com.example.mscourierservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final  String DELIVERY_EXCHANGE = "delivery.exchange";

    public static final String COURIER_ASSIGNED_QUEUE = "courier.order-assigned.queue";
    public static final String COURIER_DELIVERED_QUEUE = "courier.order-delivered.queue";

    public static final String ORDER_ASSIGNED_ROUTING_KEY = "order.assigned";
    public static final String ORDER_DELIVERED_ROUTING_KEY = "order.delivered";

    @Bean
    public TopicExchange deliveryExchange() {
        return new TopicExchange(DELIVERY_EXCHANGE);
    }

    @Bean
    public Queue orderAssignedQueue() {
        return new Queue(COURIER_ASSIGNED_QUEUE, true);
    }

    @Bean
    public Queue orderDeliveredQueue() {
        return new Queue(COURIER_DELIVERED_QUEUE, true);
    }

    @Bean
    public Binding bindOrderAssigned() {
        return BindingBuilder.bind(orderAssignedQueue()).to(deliveryExchange()).with(ORDER_ASSIGNED_ROUTING_KEY);
    }

    @Bean
    public Binding bindOrderDelivered() {
        return BindingBuilder.bind(orderDeliveredQueue()).to(deliveryExchange()).with(ORDER_DELIVERED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}