package com.example.aad_pos_system.Dao;

import com.example.aad_pos_system.Dto.ItemDto;
import com.example.aad_pos_system.Entity.ItemEntity;

import java.sql.Connection;
import java.util.List;

public interface ItemDao{
    boolean SaveItem(ItemEntity itemEntity, Connection connection);

    ItemDto getAll(String id, Connection connection);

    boolean Update(ItemEntity itemEntity, Connection connection);

    boolean deleteItem(String code, Connection connection);

    List<ItemEntity> getAllItems(Connection connection);
}
