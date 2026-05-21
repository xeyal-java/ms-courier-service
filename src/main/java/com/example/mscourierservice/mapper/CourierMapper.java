package com.example.mscourierservice.mapper;

import com.example.mscourierservice.dto.CourierRequestDto;
import com.example.mscourierservice.dto.CourierResponse;
import com.example.mscourierservice.entity.Courier;
import com.example.mscourierservice.enums.CourierStatus;
import org.springframework.stereotype.Component;

@Component
public class CourierMapper {

    public Courier mapToEntity(CourierRequestDto request) {
        if (request == null) {
            return null;
        }
        return Courier.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .status(CourierStatus.FREE)
                .build();
    }

    public CourierResponse toResponse(Courier courier) {
        if (courier == null) {
            return null;
        }
        return CourierResponse.builder()
                .id(courier.getId())
                .name(courier.getName())
                .phone(courier.getPhone())
                .status(courier.getStatus())
                .build();
    }
}