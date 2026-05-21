package com.example.mscourierservice.controller;

import com.example.mscourierservice.dto.CourierRequestDto;
import com.example.mscourierservice.dto.CourierResponse;
import com.example.mscourierservice.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/couriers")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CourierResponse createCourier(@RequestBody CourierRequestDto request) {
        return courierService.createCourier(request);
    }

    @GetMapping("/available")
    public CourierResponse getAvailableCourier() {
        return courierService.getAvailableCourier();
    }
}