package com.example.mscourierservice.consumer;

import com.example.mscourierservice.config.RabbitMQConfig;
import com.example.mscourierservice.consumer.event.OrderAssignedEvent;
import com.example.mscourierservice.consumer.event.OrderDeliveredEvent;
import com.example.mscourierservice.enums.CourierStatus;
import com.example.mscourierservice.service.CourierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CourierEventConsumer {

    private final CourierService courierService;

    @RabbitListener(queues = RabbitMQConfig.COURIER_ASSIGNED_QUEUE)
    public void consumeOrderAssignedEvent(OrderAssignedEvent event) {
        log.info("OrderAssignedEvent received. Courier ID: {}, Order ID: {}", event.getCourierId(), event.getOrderId());
        courierService.updateCourierStatus(event.getCourierId(), CourierStatus.BUSY);
    }

    @RabbitListener(queues = RabbitMQConfig.COURIER_DELIVERED_QUEUE)
    public void consumeOrderDeliveredEvent(OrderDeliveredEvent event) {
        log.info("OrderDeliveredEvent received. Courier ID: {}, Order ID: {}", event.getCourierId(), event.getOrderId());
        courierService.updateCourierStatus(event.getCourierId(), CourierStatus.FREE);
    }
}