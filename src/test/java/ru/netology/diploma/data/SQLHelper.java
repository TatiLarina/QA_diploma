package ru.netology.diploma.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SQLHelper {

    private static String url = System.getProperty("db.url");
    private static String username = System.getProperty("db.username");
    private static String password = System.getProperty("db.password");

    public SQLHelper() {
    }

    public static void clearAllData() throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection conn = DriverManager.getConnection(url, username, password);
        runner.update(conn, "DELETE FROM credit_request_entity;");
        runner.update(conn, "DELETE FROM payment_entity;");
        runner.update(conn, "DELETE FROM order_entity;");
    }

    public static void checkPaymentStatus(Status status) throws SQLException {
        QueryRunner runner = new QueryRunner();
        val conn = DriverManager.getConnection(url, username, password);
        val paymentDataSQL = "SELECT status FROM payment_entity;";
        val payment = runner.query(conn, paymentDataSQL, new BeanHandler<>(PaymentModel.class));
        assertEquals(status, payment.status);
    }

    public static void checkCreditStatus(Status status) throws SQLException {
        QueryRunner runner = new QueryRunner();
        val conn = DriverManager.getConnection(url, username, password);
        val creditDataSQL = "SELECT status FROM credit_request_entity;";
        val credit = runner.query(conn, creditDataSQL, new BeanHandler<>(CreditModel.class));
        assertEquals(status, credit.status);
    }

}
