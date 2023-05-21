package ru.netology.diploma.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        private String numberCard;
        private String month;
        private String year;
        private String name;
        private String cvc;
    }

    private static String generateRandomNumberCard() {
        return faker.numerify("################");
    }

    private static String generateRandomMonth() {
        return Integer.toString(faker.number().numberBetween(1, 13));
    }

    private static String generateRandomValidYear() {
        return Integer.toString(faker.number().numberBetween(23, 33));
    }

    private static String generateRandomName() {
        return faker.name().fullName();
    }

    private static String generateRandomCvc() {
        return faker.numerify("###");
    }

    public static CardInfo getCardApproved() {
        return new CardInfo("4444 4444 4444 4441", generateRandomMonth(), generateRandomValidYear(),
                generateRandomName(), generateRandomCvc());
    }

    public static CardInfo getCardDeclined() {
        return new CardInfo("4444 4444 4444 4442", generateRandomMonth(), generateRandomValidYear(),
                generateRandomName(), generateRandomCvc());
    }

    public static CardInfo getRandomValidCard() {
        return new CardInfo(generateRandomNumberCard(), generateRandomMonth(), generateRandomValidYear(),
                generateRandomName(), generateRandomCvc());
    }


}
