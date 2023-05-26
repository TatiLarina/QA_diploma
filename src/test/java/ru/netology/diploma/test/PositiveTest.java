package ru.netology.diploma.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.var;
import org.junit.jupiter.api.*;
import ru.netology.diploma.data.DataHelper;
import ru.netology.diploma.page.MainPage;

import static com.codeborne.selenide.Selenide.*;

public class PositiveTest {
    private MainPage mainPage;

    @BeforeAll
    static void setUpAll() {
        open("http://localhost:8080/");
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true).savePageSource(true));
    }

    @BeforeEach
    void setUpPage() {
        mainPage = new MainPage();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Купить активной картой")
    void shouldApprovedCardBuy() {
        var cardInfo = DataHelper.getCardApproved();
        mainPage.changeBuyForMoney();
        mainPage.insertCard(cardInfo);
        mainPage.clickButtonNext();
        mainPage.checkMessageSuccess();
    }

    @Test
    @DisplayName("Купить активной картой со сроком истечения в текущем месяце")
    void shouldApprovedCardBuyMonthNow() {
        var cardInfo = DataHelper.getCardApproved();
        mainPage.changeBuyForMoney();
        mainPage.insertCard(cardInfo);
        mainPage.clearField(1);
        mainPage.clearField(2);
        mainPage.setValueField(1, DataHelper.getMonthNow());
        mainPage.setValueField(2, DataHelper.getYearForLastMonth());
        mainPage.clickButtonNext();
        mainPage.checkMessageSuccess();
    }

    @Test
    @DisplayName("Купить активной картой в кредит")
    void shouldApprovedCardCredit() {
        var cardInfo = DataHelper.getCardApproved();
        mainPage.changeBuyOnCredit();
        mainPage.insertCard(cardInfo);
        mainPage.clickButtonNext();
        mainPage.checkMessageSuccess();
    }

    @Test
    @DisplayName("Купить активной картой со сроком истечения в текущем месяце в кредит")
    void shouldApprovedCardCreditMonthNow() {
        var cardInfo = DataHelper.getCardApproved();
        mainPage.changeBuyOnCredit();
        mainPage.insertCard(cardInfo);
        mainPage.clearField(1);
        mainPage.clearField(2);
        mainPage.setValueField(1, DataHelper.getMonthNow());
        mainPage.setValueField(2, DataHelper.getYearForLastMonth());
        mainPage.clickButtonNext();
        mainPage.checkMessageSuccess();
    }

    @Test
    @DisplayName("Купить неактивной картой")
    void shouldDeclinedCardBuy() {
        var cardInfo = DataHelper.getCardDeclined();
        mainPage.changeBuyForMoney();
        mainPage.insertCard(cardInfo);
        mainPage.clickButtonNext();
        mainPage.checkMessageError();
    }

    @Test
    @DisplayName("Купить неактивной картой в кредит")
    void shouldDeclinedCardCredit() {
        var cardInfo = DataHelper.getCardDeclined();
        mainPage.changeBuyOnCredit();
        mainPage.insertCard(cardInfo);
        mainPage.clickButtonNext();
        mainPage.checkMessageError();
    }

    @Test
    @DisplayName("Купить картой со случайными валидными данными")
    void shouldRandomCardBuy() {
        var cardInfo = DataHelper.getRandomValidCard();
        mainPage.changeBuyForMoney();
        mainPage.insertCard(cardInfo);
        mainPage.clickButtonNext();
        mainPage.checkMessageError();
    }

    @Test
    @DisplayName("Купить картой со случайными валидными данными в кредит")
    void shouldRandomCardCredit() {
        var cardInfo = DataHelper.getRandomValidCard();
        mainPage.changeBuyOnCredit();
        mainPage.insertCard(cardInfo);
        mainPage.clickButtonNext();
        mainPage.checkMessageError();
    }


}
