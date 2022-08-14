package demo.contexts;

import demo.api.UsersService;
import demo.asserters.users.UserAsserter;
import demo.models.User;
import io.qameta.allure.Step;
import retrofit2.Response;

import javax.inject.Inject;
import java.io.IOException;

public class UsersApiContext {
    private final UsersService usersService;
    private final UserAsserter.UserAsserterBuilder userAsserterBuilder;

    @Inject
    public UsersApiContext(UsersService usersService, UserAsserter.UserAsserterBuilder userAsserterBuilder) {
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
