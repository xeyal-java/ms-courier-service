package com.example.mscourierservice.mapper;

import com.example.mscourierservice.dto.CourierRequestDto;
import com.example.mscourierservice.dto.CourierResponse;
import com.example.mscourierservice.entity.Courier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourierMapper {

    @Mapping(target = "status", constant = "FREE")
    @Mapping(target = "id", ignore = true)
    Courier mapToEntity(CourierRequestDto request);

    CourierResponse toResponse(Courier courier);
}