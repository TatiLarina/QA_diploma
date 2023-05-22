package ru.netology.diploma.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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
    public void changeBuyForMoney() {
        buyForMoneyButton.click();
    }

    public void changeBuyOnCredit() {
        buyOnCreditButton.click();
        $(byText("Кредит по данным карты")).shouldBe(Condition.visible);
    }

    public void insertCard(DataHelper.CardInfo info) {
        cardNumberField.setValue(info.getNumberCard());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        nameField.setValue(info.getName());
        cvcOrCvvNumberField.setValue(info.getCvc());
        buttonNext.click();
    }

    public void checkMessageSuccess() {
        messageSuccess.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void checkMessageError() {
        messageError.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void clickButtonNext() {
        buttonNext.click();
    }

    public void validError() {
        messageSuccess.shouldBe(Condition.hidden);
        messageError.shouldBe(Condition.hidden);
    }
}
