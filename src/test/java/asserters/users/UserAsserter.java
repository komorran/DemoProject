package asserters.users;

import asserters.core.HttpResponseAsserter;
import io.qameta.allure.Step;
import models.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAsserter extends HttpResponseAsserter<UserAsserter, User> {
    @Step("Assert an user")
    public UserAsserter assertUser(User expected) {
        var user = response.body();

        assertEquals(expected.getName(), user.getName(), "User names should be equal");
        assertEquals(expected.getEmail(), user.getEmail(), "User emails should be equal");
        assertEquals(expected.getGender(), user.getGender(), "User genders should be equal");
        assertEquals(expected.getStatus(), user.getStatus(), "User statuses should be equal");

        return this;
    }
}
