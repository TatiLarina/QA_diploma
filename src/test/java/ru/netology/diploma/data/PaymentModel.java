package ru.netology.diploma.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentModel {
    private Status status;
    private String id;
    private int amount;
    private Timestamp created;
    private String transaction_id;
}