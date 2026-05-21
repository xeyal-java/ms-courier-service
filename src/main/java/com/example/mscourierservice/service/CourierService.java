package com.example.mscourierservice.service;

import com.example.mscourierservice.dto.CourierRequestDto;
import com.example.mscourierservice.dto.CourierResponse;
import com.example.mscourierservice.entity.Courier;
import com.example.mscourierservice.enums.CourierStatus;
import com.example.mscourierservice.exception.CourierNotAvailable;
import com.example.mscourierservice.exception.CourierNotFoundException;
import com.example.mscourierservice.mapper.CourierMapper;
import com.example.mscourierservice.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;
    private final CourierMapper courierMapper;

    public CourierResponse createCourier(CourierRequestDto request) {
        Courier courier = courierMapper.mapToEntity(request);
        Courier savedCourier = courierRepository.save(courier);

        return courierMapper.toResponse(savedCourier);
    }

    public CourierResponse getAvailableCourier() {
        Courier courier = courierRepository.findFirstByStatus(CourierStatus.FREE)
                .orElseThrow(() -> new CourierNotAvailable("No available courier found!"));

        return courierMapper.toResponse(courier);
    }

    public void updateCourierStatus(Long courierId, CourierStatus status) {
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(() -> new CourierNotFoundException("Courier not found!"));
        courier.setStatus(status);
        courierRepository.save(courier);
    }

}
