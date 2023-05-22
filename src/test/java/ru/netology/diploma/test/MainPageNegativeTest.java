package ru.netology.diploma.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.diploma.page.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageNegativeTest {
    private MainPage mainPage;

    @BeforeAll
    static void setUpAll() {
        open("http://localhost:8080/");
        SelenideLogger.addListener("allure", new AllureSelenide());
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
    @DisplayName("Отправка пустых полей в обычной покупке")
    void shouldEmptyFieldsBuy() {
        mainPage.changeBuyForMoney();
        mainPage.clickButtonNext();
        String expected = "Неверный формат";
        String actual = $$(".input__sub").get(0).getText().trim();
        assertEquals(expected, actual);
        actual = $$(".input__sub").get(1).getText().trim();
        assertEquals(expected, actual);
        actual = $$(".input__sub").get(2).getText().trim();
        assertEquals(expected, actual);
        actual = $$(".input__sub").get(4).getText().trim();
        assertEquals(expected, actual);
        expected = "Поле обязательно для заполнения";
        actual = $$(".input__sub").get(3).getText().trim();
        assertEquals(expected, actual);
        mainPage.validError();
    }

    @Test
    @DisplayName("Отправка пустых полей в покупке в кредит")
    void shouldEmptyFieldsCredit() {
        mainPage.changeBuyOnCredit();
        mainPage.clickButtonNext();
        String expected = "Неверный формат";
        String actual = $$(".input__sub").get(0).getText().trim();
        assertEquals(expected, actual);
        actual = $$(".input__sub").get(1).getText().trim();
        assertEquals(expected, actual);
        actual = $$(".input__sub").get(2).getText().trim();
        assertEquals(expected, actual);
        actual = $$(".input__sub").get(4).getText().trim();
        assertEquals(expected, actual);
        expected = "Поле обязательно для заполнения";
        actual = $$(".input__sub").get(3).getText().trim();
        assertEquals(expected, actual);
        mainPage.validError();
    }

}
