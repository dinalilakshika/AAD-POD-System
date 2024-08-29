package com.example.aad_pos_system.Bo.Impl;


import com.example.aad_pos_system.Bo.orderBo;
import com.example.aad_pos_system.Dao.Impl.OrderDaoImpl;
import com.example.aad_pos_system.Dao.Impl.OrderDetailDaoImpl;
import com.example.aad_pos_system.Dto.OrderDto;
import com.example.aad_pos_system.Entity.OrderEntity;
import jakarta.transaction.Transactional;

import java.sql.Connection;

public class OrderBoImpl implements orderBo {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    OrderDetailDaoImpl orderDetailDao = new OrderDetailDaoImpl();

    @Transactional
    @Override
    public boolean SaveOrder(OrderDto orderDto, Connection connection) {

        return orderDao.saveOrder(new OrderEntity(orderDto.getOrderId(), orderDto.getAmount(), orderDto.getNetTotal(), orderDto.getDiscount(), orderDto.getFinalTotal()), connection);
    }

}