package com.example.mscourierservice.service;

import com.example.mscourierservice.annotation.CacheEvict;
import com.example.mscourierservice.annotation.RedisCache;
import com.example.mscourierservice.dto.CourierCriteria;
import com.example.mscourierservice.dto.CourierRequestDto;
import com.example.mscourierservice.dto.CourierResponse;
import com.example.mscourierservice.dto.PageCriteria;
import com.example.mscourierservice.entity.Courier;
import com.example.mscourierservice.enums.CourierStatus;
import com.example.mscourierservice.exception.CourierNotFoundException;
import com.example.mscourierservice.mapper.CourierMapper;
import com.example.mscourierservice.repository.CourierRepository;
import com.example.mscourierservice.repository.specification.CourierSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierService {

    private final CourierRepository courierRepository;
    private final CourierMapper courierMapper;

    @Transactional
    public CourierResponse createCourier(CourierRequestDto request) {
        log.info("Creating a new courier with name: {}", request.getName());
        Courier courier = courierMapper.mapToEntity(request);
        return courierMapper.toResponse(courierRepository.save(courier));
    }

    @Transactional
    @CacheEvict(key = "available_couriers_")
    public void updateCourierStatus(Long courierId, CourierStatus status) {
        log.info("Updating status for Courier ID: {} to {}", courierId, status);
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(() -> new CourierNotFoundException("Courier not found with ID: " + courierId));

        courier.setStatus(status);
        courierRepository.save(courier);
    }

    @RedisCache(key = "available_couriers_", ttl = 300)
    public Page<CourierResponse> getAvailableCouriers(CourierCriteria courierCriteria, PageCriteria pageCriteria) {
        log.info("Fetching available couriers with criteria: {}", courierCriteria);

        Pageable pageable = PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by("id").ascending());
        CourierSpecification specification = new CourierSpecification(courierCriteria);

        return courierRepository.findAll(specification, pageable)
                .map(courierMapper::toResponse);
    }
}