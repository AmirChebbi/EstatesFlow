package com.example.EstatesFlow.Entities.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Role {
    @Id
    long id;

    @Column(nullable = false, unique = true)
    String name;
    public Role(String name){
        this.name=name;
    }
    public Role(){

    }
}
