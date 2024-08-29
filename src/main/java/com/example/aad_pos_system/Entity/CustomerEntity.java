package com.example.aad_pos_system.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerEntity implements Serializable {
    private String id;
    private String name;
    private String salary;
    private String address;
}
