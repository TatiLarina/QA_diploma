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
    private static QueryRunner runner;
    private static Connection conn;

    public SQLHelper() {
    }

    public static void setUp() throws SQLException {
        runner = new QueryRunner();
        conn = DriverManager.getConnection(url, username, password);
    }

    public static void clearAllData() throws SQLException {
        setUp();
        runner.update(conn, "DELETE FROM credit_request_entity;");
        runner.update(conn, "DELETE FROM payment_entity;");
        runner.update(conn, "DELETE FROM order_entity;");
    }

    public static void checkPaymentStatus(Status status) throws SQLException {
        setUp();
        val paymentDataSQL = "SELECT status FROM payment_entity;";
        val payment = runner.query(conn, paymentDataSQL, new BeanHandler<>(PaymentModel.class));
        assertEquals(status, payment.getStatus());
    }

    public static void checkCreditStatus(Status status) throws SQLException {
        setUp();
        val creditDataSQL = "SELECT status FROM credit_request_entity;";
        val credit = runner.query(conn, creditDataSQL, new BeanHandler<>(CreditModel.class));
        assertEquals(status, credit.getStatus());
    }

}
