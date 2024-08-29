package com.example.aad_pos_system.Dao;


import com.example.aad_pos_system.Dto.CustomerDto;
import com.example.aad_pos_system.Entity.CustomerEntity;
import java.sql.Connection;
import java.util.List;

public interface CustomerDao {
    boolean saveCustomer(CustomerEntity customerEntity, Connection connection);

    CustomerDto getCustomer(String customerId, Connection connection);


    boolean delete(String id, Connection connection);

    boolean update(CustomerEntity customerEntity, Connection connection);

    List<CustomerEntity> getAllCustomers(Connection connection);
}
