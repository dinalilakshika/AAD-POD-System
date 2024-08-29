package com.example.aad_pos_system.Dao;

import com.example.aad_pos_system.Entity.OrderEntity;

import java.sql.Connection;
public interface OrderDao {
    boolean saveOrder(OrderEntity orderEntity, Connection connection);
}
