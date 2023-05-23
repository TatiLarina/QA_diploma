package ru.netology.diploma.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.diploma.data.DataHelper;
import ru.netology.diploma.page.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class NegativeBuyTest {
    private MainPage mainPage;
    private String expected;
    private String actual;
    private DataHelper.CardInfo cardInfo;

    @BeforeAll
    static void setUpAll() {
        open("http://localhost:8080/");
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUpPage() {
        mainPage = new MainPage();
        cardInfo = DataHelper.getRandomValidCard();
        mainPage.changeBuyForMoney();
    }

    @AfterEach
    void downPage() {
        mainPage.changeBuyOnCredit();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Отправка пустых полей")
    void emptyFields() {
        mainPage.clickButtonNext();
        expected = "Неверный формат";
        actual = $$(".input__sub").get(0).getText().trim();
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
    }

    @Test
    @DisplayName("Валидация поля номер карты")
    void validNumberCard() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField($$(".input__control").get(0));
        $$(".input__control").get(0).setValue("asdf 1111 !`'_+=-");
        expected = "1111";
        actual = $$(".input__control").get(0).getAttribute("value").trim();
        assertEquals(expected, actual);
        mainPage.clickButtonNext();
        $$(".input__sub").get(0).shouldBe(Condition.visible);
        expected = "Неверный формат";
        actual = $$(".input__sub").get(0).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля месяц. 00")
    void validMonth00() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField($$(".input__control").get(1));
        $$(".input__control").get(1).setValue("00");
        mainPage.clickButtonNext();
        $(".input__sub").shouldBe(Condition.visible);
        expected = "Неверно указан срок действия карты";
        actual = $(".input__sub").getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля месяц. 13")
    void validMonth13() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField($$(".input__control").get(1));
        $$(".input__control").get(1).setValue("13");
        mainPage.clickButtonNext();
        $(".input__sub").shouldBe(Condition.visible);
        expected = "Неверно указан срок действия карты";
        actual = $(".input__sub").getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля год. Прошлый год")
    void validLastYear() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField($$(".input__control").get(2));
        $$(".input__control").get(2).setValue(DataHelper.getLastYear());
        mainPage.clickButtonNext();
        $(".input__sub").shouldBe(Condition.visible);
        expected = "Истёк срок действия карты";
        actual = $(".input__sub").getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля год. Текущий год + 6 лет")
    void validYearPlus6() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField($$(".input__control").get(2));
        $$(".input__control").get(2).setValue(DataHelper.getYearPlus6());
        mainPage.clickButtonNext();
        $(".input__sub").shouldBe(Condition.visible);
        expected = "Неверно указан срок действия карты";
        actual = $(".input__sub").getText().trim();
        assertEquals(expected, actual);
    }




}
