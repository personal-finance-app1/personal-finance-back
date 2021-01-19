package com.revature.personalfinance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @Entity
@NoArgsConstructor @AllArgsConstructor
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int accountId;
    private int userId;
    private String name;
    private int income;
    private int expenses;
}
