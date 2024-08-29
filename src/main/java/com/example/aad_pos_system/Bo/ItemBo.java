package com.example.aad_pos_system.Bo;


import com.example.aad_pos_system.Dto.ItemDto;
import java.sql.Connection;
import java.util.List;


public interface ItemBo {
    List<ItemDto> getAllItems(Connection connection);

    boolean SaveItem(ItemDto itemDto, Connection connection);

    boolean Update(ItemDto itemDto, Connection connection);

    boolean deleteItem(String code, Connection connection);

    ItemDto getAll(String id, Connection connection);
}