package ru.netology.diploma.test;

import com.codeborne.selenide.Condition;
import lombok.var;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.diploma.data.DataHelper;
import ru.netology.diploma.page.MainPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPageTest {
    @BeforeAll
    static void setup() {
        open("http://localhost:8080/");
    }

    @Test
    void shouldSuccessCard() {
        var mainPage = new MainPage();
        var cardInfo = DataHelper.getCardApproved();
        $(byText("Купить")).click();
        mainPage.insertCard(cardInfo);
        $(byText("Операция одобрена Банком.")).shouldBe(Condition.visible);
    }
}
