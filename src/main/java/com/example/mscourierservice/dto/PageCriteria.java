package com.example.mscourierservice.dto;
import lombok.Data;

@Data
public class PageCriteria {
    private Integer page = 0;
    private Integer count = 5;
}
