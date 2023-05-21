package ru.netology.diploma.test;

import com.codeborne.selenide.Condition;
import lombok.var;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.diploma.data.DataHelper;
import ru.netology.diploma.page.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    @BeforeAll
    static void setup() {
        open("http://localhost:8080/");
    }

    @Test
    @DisplayName("Купить активной картой")
    void shouldApprovedCardBuy() {
        var mainPage = new MainPage();
        var cardInfo = DataHelper.getCardApproved();
        $$(".button__content").find(exactText("Купить")).click();
        $(byText("Оплата по карте")).shouldBe(Condition.visible);
        mainPage.insertCard(cardInfo);
        $(byText("Операция одобрена Банком.")).shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Купить активной картой в кредит")
    void shouldApprovedCardCredit() {
        var mainPage = new MainPage();
        var cardInfo = DataHelper.getCardApproved();
        $$(".button__content").find(exactText("Купить в кредит")).click();
        $(byText("Кредит по данным карты")).shouldBe(Condition.visible);
        mainPage.insertCard(cardInfo);
        $(byText("Операция одобрена Банком.")).shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    @Test
    @DisplayName("Купить неактивной картой")
    void shouldDeclinedCardBuy() {
        var mainPage = new MainPage();
        var cardInfo = DataHelper.getCardDeclined();
        $$(".button__content").find(exactText("Купить")).click();
        mainPage.insertCard(cardInfo);
        $(byText("Ошибка! Банк отказал в проведении операции.")).shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    @Test
    @DisplayName("Купить неактивной картой в кредит")
    void shouldDeclinedCardCredit() {
        var mainPage = new MainPage();
        var cardInfo = DataHelper.getCardDeclined();
        $$(".button__content").find(exactText("Купить в кредит")).click();
        mainPage.insertCard(cardInfo);
        $(byText("Ошибка! Банк отказал в проведении операции.")).shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    @Test
    @DisplayName("Купить картой со случайными валидными данными")
    void shouldRandomCardBuy() {
        var mainPage = new MainPage();
        var cardInfo = DataHelper.getRandomValidCard();
        $$(".button__content").find(exactText("Купить")).click();
        mainPage.insertCard(cardInfo);
        $(byText("Ошибка! Банк отказал в проведении операции.")).shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    @Test
    @DisplayName("Купить картой со случайными валидными данными в кредит")
    void shouldRandomCardCredit() {
        var mainPage = new MainPage();
        var cardInfo = DataHelper.getRandomValidCard();
        $$(".button__content").find(exactText("Купить в кредит")).click();
        mainPage.insertCard(cardInfo);
        $(byText("Ошибка! Банк отказал в проведении операции.")).shouldBe(Condition.visible, Duration.ofSeconds(30));
    }


}
