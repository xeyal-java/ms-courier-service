package com.example.mscourierservice.dto;

import com.example.mscourierservice.enums.CourierStatus;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourierResponse {
    private Long id;
    private String name;
    private String phone;
    private CourierStatus status;

}
