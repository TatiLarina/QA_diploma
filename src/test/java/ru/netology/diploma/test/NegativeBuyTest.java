package ru.netology.diploma.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.diploma.data.DataHelper;
import ru.netology.diploma.page.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NegativeBuyTest {
    private MainPage mainPage;
    private String expected;
    private String actual;
    private DataHelper.CardInfo cardInfo;
    private int fieldCardNumber = 0;
    private int fieldMounth = 1;
    private int fieldYear = 2;
    private int fieldName = 3;
    private int fieldCVC = 4;

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
        actual = mainPage.getInputWarning(0);
        assertEquals(expected, actual);
        actual = mainPage.getInputWarning(1);
        assertEquals(expected, actual);
        actual = mainPage.getInputWarning(2);
        assertEquals(expected, actual);
        actual = mainPage.getInputWarning(4);
        assertEquals(expected, actual);
        expected = "Поле обязательно для заполнения";
        actual = mainPage.getInputWarning(3);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Очищение предупреждений после исправления ошибок в заполнении полей")
    void correctionFields() {
        mainPage.clickButtonNext();
        mainPage.insertCard(cardInfo);
        mainPage.clickButtonNext();
        mainPage.inputWarning(0).shouldBe(Condition.hidden);
        int expected = 0;
        int actual = $$(".input__sub").size();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля номер карты")
    void validNumberCard() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField(fieldCardNumber);
        mainPage.setValueField(fieldCardNumber, "asdf 1111 !`'_+=-");
        expected = "1111";
        actual = mainPage.getValueField(fieldCardNumber);
        assertEquals(expected, actual);
        mainPage.clickButtonNext();
        mainPage.inputWarning(0).shouldBe(Condition.visible);
        expected = "Неверный формат";
        actual = mainPage.getInputWarning(0);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля месяц. 00")
    void validMonth00() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField(fieldMounth);
        mainPage.setValueField(fieldMounth, "00");
        mainPage.clickButtonNext();
        mainPage.inputWarning(0).shouldBe(Condition.visible);
        expected = "Неверно указан срок действия карты";
        actual = mainPage.getInputWarning(0);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля месяц. 13")
    void validMonth13() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField(fieldMounth);
        mainPage.setValueField(fieldMounth, "13");
        mainPage.clickButtonNext();
        mainPage.inputWarning(0).shouldBe(Condition.visible);
        expected = "Неверно указан срок действия карты";
        actual = mainPage.getInputWarning(0);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля месяц. Неверный формат")
    void validMonthWrongFormat() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField(fieldMounth);
        mainPage.setValueField(fieldMounth, "1");
        mainPage.clickButtonNext();
        mainPage.inputWarning(0).shouldBe(Condition.visible);
        expected = "Неверный формат";
        actual = mainPage.getInputWarning(0);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля год. Прошлый год")
    void validLastYear() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField(fieldYear);
        mainPage.setValueField(fieldYear, DataHelper.getLastYear());
        mainPage.clickButtonNext();
        mainPage.inputWarning(0).shouldBe(Condition.visible);
        expected = "Истёк срок действия карты";
        actual = mainPage.getInputWarning(0);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля год. Текущий год + 6 лет")
    void validYearPlus6() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField(fieldYear);
        mainPage.setValueField(fieldYear, DataHelper.getYearPlus6());
        mainPage.clickButtonNext();
        mainPage.inputWarning(0).shouldBe(Condition.visible);
        expected = "Неверно указан срок действия карты";
        actual = mainPage.getInputWarning(0);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля год. 00")
    void validYear00() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField(fieldYear);
        mainPage.setValueField(fieldYear, "00");
        mainPage.clickButtonNext();
        mainPage.inputWarning(0).shouldBe(Condition.visible);
        expected = "Истёк срок действия карты";
        actual = mainPage.getInputWarning(0);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля год. Неверный формат")
    void validYearWrongFormat() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField(fieldYear);
        mainPage.setValueField(fieldYear, "0");
        mainPage.clickButtonNext();
        mainPage.inputWarning(0).shouldBe(Condition.visible);
        expected = "Неверный формат";
        actual = mainPage.getInputWarning(0);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Срок карты вышел в прошлом месяце")
    void validLastMonth() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField(fieldMounth);
        mainPage.clearField(fieldYear);
        mainPage.setValueField(fieldMounth, DataHelper.getLastMonth());
        mainPage.setValueField(fieldYear, DataHelper.getYearForLastMonth());
        mainPage.clickButtonNext();
        mainPage.inputWarning(0).shouldBe(Condition.visible);
        expected = "Истёк срок действия карты";
        actual = mainPage.getInputWarning(0);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля имя. Цифры")
    void validNameNumbers() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField(fieldName);
        mainPage.setValueField(fieldName, "12345");
        mainPage.clickButtonNext();
        expected = "Неверный формат";
        actual = mainPage.getInputWarning(0);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля имя. Кириллица")
    void validNameCirilica() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField(fieldName);
        mainPage.setValueField(fieldName, "Иван Иванов");
        mainPage.clickButtonNext();
        expected = "Неверный формат";
        actual = mainPage.getInputWarning(0);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Валидация поля CVC. Буквы")
    void validCVC() {
        mainPage.insertCard(cardInfo);
        mainPage.clearField(fieldCVC);
        mainPage.setValueField(fieldCVC, "ab");
        mainPage.clickButtonNext();
        expected = "Неверный формат";
        actual = mainPage.getInputWarning(0);
        assertEquals(expected, actual);
    }


}
