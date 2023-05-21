package ru.netology.diploma.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.diploma.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private SelenideElement numberCardField = $(byText("Номер карты"));
    private SelenideElement monthField = $(byText("Месяц"));
    private SelenideElement yearField = $(byText("Год"));
    private SelenideElement nameField = $(byText("Владелец"));
    private SelenideElement cvcField = $(byText("CVC/CVV"));
    private SelenideElement buttonNext = $(byText("Продолжить"));

    public void insertCard(DataHelper.CardInfo info) {
        numberCardField.setValue(info.getNumberCard());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        nameField.setValue(info.getName());
        cvcField.setValue(info.getCvc());
        buttonNext.click();
    }

}
