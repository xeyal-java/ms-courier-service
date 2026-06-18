package com.example.mscourierservice.consumer.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAssignedEvent {
    private Long orderId;
    private Long courierId;
}