package demo.models.factories;

import com.github.javafaker.Faker;
import demo.models.User;

public class UsersFactory {
    private static final Faker faker = new Faker();

    public static User getUser() {
        return User.builder()
                .name(faker.name().fullName())
                .gender("male")
                .email(faker.internet().emailAddress())
                .status("active")
                .build();
    }
}
