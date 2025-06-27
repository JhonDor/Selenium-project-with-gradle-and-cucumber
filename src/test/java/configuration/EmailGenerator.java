package configuration;

import com.github.javafaker.Faker;

public class EmailGenerator {
    private static String randomEmail;

    static {
        Faker faker = new Faker();
        randomEmail = faker.internet().emailAddress();
    }

    public static String getRandomEmail() {
        return randomEmail;
    }
}