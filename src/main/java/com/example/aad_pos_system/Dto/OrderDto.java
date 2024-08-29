package com.example.aad_pos_system.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private String orderId;
    private String amount;
    private String netTotal;
    private String discount;
    private String finalTotal;

    private List<OrderDetailDto> orderDetails = new ArrayList<>();
}
