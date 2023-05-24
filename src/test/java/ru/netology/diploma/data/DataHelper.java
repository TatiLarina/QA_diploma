package ru.netology.diploma.data;

import com.github.javafaker.Faker;
import lombok.*;

import java.security.SecureRandom;
import java.time.YearMonth;
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
        return Integer.toString(faker.number().numberBetween(getYearNow() + 1, getYearNow() + 5));
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

    public static int getYearNow() {
        return YearMonth.now().getYear() - 2000;
    }

    public static String getMonthNow() {
        String month = Integer.toString(YearMonth.now().getMonthValue());
        if (month.length() == 1) {
            month = "0" + month;
        }
        return month;

    }

    public static String getLastMonth() {
        String month = Integer.toString(YearMonth.now().getMonthValue() - 1);
        if (month.equals("0")) {
            month = "12";
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        return month;
    }

    public static String getLastYear() {
        return Integer.toString(getYearNow() - 1);
    }

    public static String getYearForLastMonth() {
        int month = YearMonth.now().getMonthValue();
        int year = YearMonth.now().getYear() - 2000;
        if (month == 1) {
            year = year - 1;
        }
        return Integer.toString(year);
    }

    public static String getYearPlus6() {
        return Integer.toString(getYearNow() + 6);
    }


}
