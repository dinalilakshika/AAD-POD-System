package com.example.aad_pos_system.Dao.Impl;


import com.example.aad_pos_system.Dao.ItemDao;
import com.example.aad_pos_system.Dto.ItemDto;
import com.example.aad_pos_system.Entity.ItemEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public final class ItemDaoImpl implements ItemDao {
    static String SAVE_ITEM = "INSERT INTO item (code,itemName,price,qty) VALUES (?,?,?,?)";
    static String GET_ITEM = "SELECT code, itemName, price, qty FROM item WHERE code = ?";

    static String GET_ALL = "SELECT * FROM item";
    static String DELETE_ITEM = "DELETE FROM item WHERE code=?";
    static String UPDATE_ITEM = "UPDATE item SET itemName=?,price=?,qty=? WHERE code=?";

    @Override
    public boolean SaveItem(ItemEntity itemEntity, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ITEM);
            preparedStatement.setString(1,itemEntity.getCode());
            preparedStatement.setString(2,itemEntity.getItemName());
            preparedStatement.setString(3,itemEntity.getPrice());
            preparedStatement.setString(4,itemEntity.getQty());

            return preparedStatement.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public ItemDto getAll(String id, Connection connection) {
        ItemDto itemDto = new ItemDto();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                itemDto.setCode(resultSet.getString("code"));
                itemDto.setItemName(resultSet.getString("itemName"));
                itemDto.setPrice(resultSet.getString("price"));
                itemDto.setQty(resultSet.getString("qty"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemDto;
    }

    @Override
    public boolean Update(ItemEntity itemEntity, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ITEM);
            preparedStatement.setString(1,itemEntity.getItemName());
            preparedStatement.setString(2,itemEntity.getPrice());
            preparedStatement.setString(3,itemEntity.getQty());
            preparedStatement.setString(4,itemEntity.getCode());
            return preparedStatement.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteItem(String code, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ITEM);
            preparedStatement.setString(1,code);
            return preparedStatement.executeUpdate()>0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<ItemEntity> getAllItems(Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ItemEntity>itemEntities = new ArrayList<>();
            while (resultSet.next()){
                ItemEntity itemEntity = new ItemEntity();
                itemEntity.setCode(resultSet.getString("code"));
                itemEntity.setItemName(resultSet.getString("itemName"));
                itemEntity.setPrice(resultSet.getString("price"));
                itemEntity.setQty(resultSet.getString("qty"));
                itemEntities.add(itemEntity);
            }
            return itemEntities;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public ItemDto getItemByCode(String itemCode, Connection connection) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(GET_ITEM);
//            preparedStatement.setString(1,itemCode);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            ItemDto itemDto = new ItemDto();
//            while (resultSet.next()){
//                itemDto.setCode(resultSet.getString("code"));
//                itemDto.setItemName(resultSet.getString("itemName"));
//                itemDto.setPrice(resultSet.getString("price"));
//                itemDto.setQty(resultSet.getString("qty"));
//            }
//            return itemDto;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
