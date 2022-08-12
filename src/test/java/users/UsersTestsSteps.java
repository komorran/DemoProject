package users;

import abstractions.AbstractTestSteps;
import api.UsersService;
import io.qameta.allure.Step;
import models.User;
import retrofit2.Response;

import javax.inject.Inject;
import java.io.IOException;

public class UsersTestsSteps extends AbstractTestSteps {
    private final UsersService usersService;

    @Inject
    public UsersTestsSteps(UsersService usersService) {
        this.usersService = usersService;
    }

    @Step("Get an user by ID: {id}")
    public Response<User> getUserById(int id) throws IOException {
        return usersService.getUser(id).execute();
    }

    @Step("Post user")
    public Response<User> postUser(User user) throws IOException {
        return usersService.postUser(user).execute();
    }
}
