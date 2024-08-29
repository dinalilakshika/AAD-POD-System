package com.example.aad_pos_system.Bo;

import com.example.aad_pos_system.Dto.CustomerDto;

import java.sql.Connection;
import java.util.List;

public interface CustomerBo {
    List<CustomerDto> getAllCustomers(Connection connection);

    boolean saveCustomer(CustomerDto customerDto, Connection connection);

    boolean update(CustomerDto customerDto, Connection connection);

    boolean delete(String id, Connection connection);
}
