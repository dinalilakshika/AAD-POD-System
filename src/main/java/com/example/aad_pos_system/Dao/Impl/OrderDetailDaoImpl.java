package com.example.aad_pos_system.Dao.Impl;

import com.example.aad_pos_system.Dao.OrderDetailDao;
import com.example.aad_pos_system.Dto.OrderDetailDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailDaoImpl implements OrderDetailDao {
    static String SAVE_ORDER_DETAIL = "INSERT INTO order_details (orderId,itemCode,qty,unitPrice,customerId,itemName) VALUES (?,?,?,?,?,?)";

    public boolean saveOrderDetail(OrderDetailDto orderDetailDto, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ORDER_DETAIL);
            preparedStatement.setString(1, orderDetailDto.getOrderId());
            preparedStatement.setString(2, orderDetailDto.getItemCode());
            preparedStatement.setString(3, orderDetailDto.getQty());
            preparedStatement.setString(4, orderDetailDto.getUnitPrice());
            preparedStatement.setString(5, orderDetailDto.getCustomerId());
            preparedStatement.setString(6, orderDetailDto.getItemName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
