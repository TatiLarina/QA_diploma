package ru.netology.diploma.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditModel {
    private Status status;
    private String id;
    private String bank_id;
    private Timestamp created;
}