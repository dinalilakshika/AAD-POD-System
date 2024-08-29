package com.example.aad_pos_system.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDto {
    private String orderId;
    private String itemCode;
    private String qty;
    private String unitPrice;
    private LocalDate localDate;
    private String customerId;
    private String itemName;

}