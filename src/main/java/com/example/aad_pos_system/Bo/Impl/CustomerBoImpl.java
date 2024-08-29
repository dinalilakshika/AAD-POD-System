package com.example.aad_pos_system.Bo.Impl;



import com.example.aad_pos_system.Bo.CustomerBo;
import com.example.aad_pos_system.Dao.Impl.CustomerDaoImpl;
import com.example.aad_pos_system.Dto.CustomerDto;
import com.example.aad_pos_system.Entity.CustomerEntity;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    CustomerDaoImpl customerDao =  new CustomerDaoImpl();
    @Override
    public List<CustomerDto> getAllCustomers(Connection connection) {
        List<CustomerDto> customerDto = new ArrayList<>();
        List<CustomerEntity>customerEntities = customerDao.getAllCustomers(connection);
        for (CustomerEntity customer:customerEntities) {
            customerDto.add(new CustomerDto(customer.getId(),customer.getName(),customer.getSalary(),customer.getAddress()));
        }
        return customerDto;
    }

    @Override
    public boolean saveCustomer(CustomerDto customerDto, Connection connection) {
        return customerDao.saveCustomer(new CustomerEntity(customerDto.getId(),customerDto.getName(),customerDto.getSalary(),customerDto.getAddress()),connection);
    }

    @Override
    public boolean update(CustomerDto customerDto, Connection connection) {
        return customerDao.update(new CustomerEntity(customerDto.getId(),customerDto.getName(),customerDto.getSalary(),customerDto.getAddress()),connection);
    }

    @Override
    public boolean delete(String id, Connection connection) {
        return customerDao.delete(id,connection);
    }
}
