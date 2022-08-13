package contexts;

import api.UsersService;
import asserters.users.UserAsserter;
import io.qameta.allure.Step;
import models.User;
import retrofit2.Response;

import javax.inject.Inject;
import java.io.IOException;

public class UsersTestsContext {
    private final UsersService usersService;
    private final UserAsserter.UserAsserterBuilder userAsserterBuilder;

    @Inject
    public UsersTestsContext(UsersService usersService, UserAsserter.UserAsserterBuilder userAsserterBuilder) {
        this.usersService = usersService;
        this.userAsserterBuilder = userAsserterBuilder;
    }

    @Step("Get an user by ID: {id}")
    public UserAsserter getUserById(int id) throws IOException {
        var response = usersService.getUser(id).execute();
        return buildAsserter(response);
    }

    @Step("Post an user")
    public UserAsserter postUser(User user) throws IOException {
        var response = usersService.postUser(user).execute();
        return buildAsserter(response);
    }

    private UserAsserter buildAsserter(Response<User> response) {
        return userAsserterBuilder.response(response).build();
    }
}
