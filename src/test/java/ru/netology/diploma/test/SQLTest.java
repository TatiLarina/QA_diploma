package ru.netology.diploma.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.var;
import org.junit.jupiter.api.*;
import ru.netology.diploma.data.DataHelper;
import ru.netology.diploma.data.SQLHelper;
import ru.netology.diploma.data.Status;
import ru.netology.diploma.page.MainPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class SQLTest {
    private MainPage mainPage;

    @BeforeAll
    static void setUpAll() {
        open("http://localhost:8080/");
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true).savePageSource(true));
        Configuration.timeout = 15_000;
    }

    @BeforeEach
    void setUpPage() {
        mainPage = new MainPage();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    void clearAll() throws SQLException{
        SQLHelper.clearAllData();
    }

    @Test
    @DisplayName("Купить активной картой")
    void checkApprovedStatusBuy() throws SQLException {
        var cardInfo = DataHelper.getCardApproved();
        mainPage.changeBuyForMoney();
        mainPage.insertCard(cardInfo);
        mainPage.clickButtonNext();
        mainPage.messageVisible();
        SQLHelper.checkPaymentStatus(Status.APPROVED);
    }

    @Test
    @DisplayName("Купить неактивной картой")
    void checkDeclinedStatusBuy() throws SQLException {
        var cardInfo = DataHelper.getCardDeclined();
        mainPage.changeBuyForMoney();
        mainPage.insertCard(cardInfo);
        mainPage.clickButtonNext();
        mainPage.messageVisible();
        SQLHelper.checkPaymentStatus(Status.DECLINED);
    }

    @Test
    @DisplayName("Купить активной картой в кредит")
    void checkApprovedStatusCredit() throws SQLException {
        var cardInfo = DataHelper.getCardApproved();
        mainPage.changeBuyOnCredit();
        mainPage.insertCard(cardInfo);
        mainPage.clickButtonNext();
        mainPage.messageVisible();
        SQLHelper.checkCreditStatus(Status.APPROVED);
    }

    @Test
    @DisplayName("Купить неактивной картой в кредит")
    void shouldDeclinedCardCredit() throws SQLException {
        var cardInfo = DataHelper.getCardDeclined();
        mainPage.changeBuyOnCredit();
        mainPage.insertCard(cardInfo);
        mainPage.clickButtonNext();
        mainPage.messageVisible();
        SQLHelper.checkCreditStatus(Status.DECLINED);
    }

}
