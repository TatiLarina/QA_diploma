package ru.netology.diploma.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.var;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        String numberCard;
        String month;
        String year;
        String name;
        String cvc;
    }

    private static String generateRandomNumberCard() {
        return faker.numerify("################");
    }

    private static String generateRandomMonth() {
        var random = new SecureRandom();
        List<String> list = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        return list.get(random.nextInt(list.size()));
    }

    private static String generateRandomValidYear() {
        return Integer.toString(faker.number().numberBetween(24, 28));
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
