package users;

import abstractions.AbstractTestSteps;
import api.UsersService;
import asserters.users.UserAsserter;
import io.qameta.allure.Step;
import models.User;
import retrofit2.Response;

import javax.inject.Inject;
import java.io.IOException;

public class UsersTestsSteps extends AbstractTestSteps {
    private final UsersService usersService;
    private UserAsserter userAsserter;

    @Inject
    public UsersTestsSteps(UsersService usersService, UserAsserter userAsserter) {
        this.usersService = usersService;
        this.userAsserter = userAsserter;
    }

    @Step("Get an user by ID: {id}")
    public UserAsserter getUserById(int id) throws IOException {
        var response = usersService.getUser(id).execute();
        return userAsserter.useResponse(response);
    }

    @Step("Post an user")
    public UserAsserter postUser(User user) throws IOException {
        var response = usersService.postUser(user).execute();
        return userAsserter.useResponse(response);
    }
}
