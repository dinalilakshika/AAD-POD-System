package com.example.aad_pos_system.Dao.Impl;


import com.example.aad_pos_system.Dao.CustomerDao;
import com.example.aad_pos_system.Dto.CustomerDto;
import com.example.aad_pos_system.Entity.CustomerEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    static String SAVE_CUSTOMER = "INSERT INTO customer (id,name,salary,address) VALUES (?,?,?,?)";
    static String GET_CUSTOMER = "SELECT * FROM customer WHERE id=?";
    static String DELETE_CUSTOMER = "DELETE FROM customer WHERE id=?";
    static String UPDATE_CUSTOMER = "UPDATE customer SET name=?,salary=?,address=? WHERE id=?";
    static String GET_ALL = "SELECT * FROM customer";

    @Override
    public boolean saveCustomer(CustomerEntity customerEntity, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CUSTOMER);
            preparedStatement.setString(1, customerEntity.getId());
            preparedStatement.setString(2, customerEntity.getName());
            preparedStatement.setString(3, customerEntity.getSalary());
            preparedStatement.setString(4, customerEntity.getAddress());

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public CustomerDto getCustomer(String customerId, Connection connection) {
        CustomerDto customerDto = new CustomerDto();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CUSTOMER);
            preparedStatement.setString(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerDto.setId(resultSet.getString("id"));
                customerDto.setName(resultSet.getString("name"));
                customerDto.setSalary(resultSet.getString("salary"));
                customerDto.setAddress(resultSet.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerDto;
    }

    @Override
    public boolean delete(String id, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER);
            preparedStatement.setString(1,id);
            return preparedStatement.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(CustomerEntity customerEntity, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER);
            preparedStatement.setString(1,customerEntity.getName());
            preparedStatement.setString(2,customerEntity.getSalary());
            preparedStatement.setString(3,customerEntity.getAddress());
            preparedStatement.setString(4,customerEntity.getId());

            return preparedStatement.executeUpdate()>0;

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<CustomerEntity> getAllCustomers(Connection connection) {
        List<CustomerEntity>customerEntities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                CustomerEntity customerEntity = new CustomerEntity();
                customerEntity.setId(resultSet.getString("id"));
                customerEntity.setName(resultSet.getString("name"));
                customerEntity.setSalary(resultSet.getString("salary"));
                customerEntity.setAddress(resultSet.getString("address"));
                customerEntities.add(customerEntity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return customerEntities;
    }
}
