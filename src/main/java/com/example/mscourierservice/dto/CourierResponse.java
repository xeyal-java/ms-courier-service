package com.example.mscourierservice.dto;

import com.example.mscourierservice.enums.CourierStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourierResponse {

    private Long id;
    private String name;
    private String phone;
    private CourierStatus status;

    @Override
    public String toString() {
        return "CourierResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
