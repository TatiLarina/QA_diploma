package ru.netology.diploma.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.diploma.data.DataHelper;

import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    List<SelenideElement> input = $$(".input__control");
    SelenideElement cardNumberField = input.get(0);
    SelenideElement monthField = input.get(1);
    SelenideElement yearField = input.get(2);
    SelenideElement nameField = input.get(3);
    SelenideElement cvcOrCvvNumberField = input.get(4);
    SelenideElement buttonNext = $$(".button__content").find(exactText("Продолжить"));


    public void insertCard(DataHelper.CardInfo info) {
        cardNumberField.setValue(info.getNumberCard());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        nameField.setValue(info.getName());
        cvcOrCvvNumberField.setValue(info.getCvc());
        buttonNext.click();
    }

}
