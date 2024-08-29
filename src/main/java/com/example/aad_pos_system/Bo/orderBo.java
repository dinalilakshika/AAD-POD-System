package com.example.aad_pos_system.Bo;


import com.example.aad_pos_system.Dto.OrderDto;

import java.sql.Connection;


public interface orderBo {
    boolean SaveOrder(OrderDto orderDto, Connection connection);
}
