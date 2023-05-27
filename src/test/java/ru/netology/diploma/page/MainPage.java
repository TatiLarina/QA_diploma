package ru.netology.diploma.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.diploma.data.DataHelper;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    List<SelenideElement> input = $$(".input__control");
    SelenideElement cardNumberField = input.get(0);
    SelenideElement monthField = input.get(1);
    SelenideElement yearField = input.get(2);
    SelenideElement nameField = input.get(3);
    SelenideElement cvcOrCvvNumberField = input.get(4);
    SelenideElement buttonNext = $$(".button__content").find(exactText("Продолжить"));
    SelenideElement buyForMoneyButton = $$(".button__content").find(exactText("Купить"));
    SelenideElement buyOnCreditButton = $$(".button__content").find(exactText("Купить в кредит"));
    SelenideElement messageSuccess = $(byText("Операция одобрена Банком."));
    SelenideElement messageError = $(byText("Ошибка! Банк отказал в проведении операции."));
    List<SelenideElement> inputWarning = $$(".input__sub");
    SelenideElement message = $(".notification__title");

    // Выбор "Купить" или "Купить в кредит"
    public void changeBuyForMoney() {
        buyForMoneyButton.click();
        $(byText("Оплата по карте")).shouldBe(Condition.visible);
    }

    public void changeBuyOnCredit() {
        buyOnCreditButton.click();
        $(byText("Кредит по данным карты")).shouldBe(Condition.visible);
    }

    // Заполнение всех полей
    public void insertCard(DataHelper.CardInfo info) {
        cardNumberField.setValue(info.getNumberCard());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        nameField.setValue(info.getName());
        cvcOrCvvNumberField.setValue(info.getCvc());
    }

    // Проверка видимости предупреждений
    public void checkMessageSuccess() {
        messageSuccess.shouldBe(Condition.visible);
    }

    public void checkMessageError() {
        messageError.shouldBe(Condition.visible);
    }

    // Нажатие кнопки "Продолжить"
    public void clickButtonNext() {
        buttonNext.click();
    }

    // Очищение поля
    public void clearField(int numberField) {
        SelenideElement field = input.get(numberField);
        field.sendKeys(Keys.CONTROL + "A");
        field.sendKeys(Keys.BACK_SPACE);
    }

    // Заполнение поля
    public void setValueField(int numberField, String text) {
        input.get(numberField).setValue(text);
    }

    // Получение содержимого поля
    public String getValueField(int numberField) {
        return input.get(numberField).getAttribute("value");
    }

    // Получение текста предупреждения
    public String getInputWarning(int numberField) {
        return inputWarning.get(numberField).getText().trim();
    }

    // Предупреждение
    public SelenideElement inputWarning(int numberField) {
        return inputWarning.get(numberField);
    }

    public void messageVisible() {
        message.shouldBe(Condition.visible);
    }


}
