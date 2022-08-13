package demo.asserters.users;

import demo.asserters.core.HttpResponseAsserter;
import io.qameta.allure.Step;
import lombok.Builder;
import demo.models.User;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAsserter extends HttpResponseAsserter<UserAsserter, User> {
    @Builder
    public UserAsserter(Response<User> response) {
        super(response);
    }

    @Step("Assert an user")
    public UserAsserter assertUser(User expected) {
        attachResponseBody();

        var user = getResponseBody();

        assertEquals(expected.getName(), user.getName(), "User names should be equal");
        assertEquals(expected.getEmail(), user.getEmail(), "User emails should be equal");
        assertEquals(expected.getGender(), user.getGender(), "User genders should be equal");
        assertEquals(expected.getStatus(), user.getStatus(), "User statuses should be equal");

        return this;
    }
}
