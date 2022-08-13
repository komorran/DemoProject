package users;

import abstractions.AbstractTest;
import models.factories.UsersFactory;
import models.infrastructure.headers.Header;
import models.infrastructure.headers.HeaderNames;
import org.junit.jupiter.api.Test;
import users.modules.UsersTestModule;

import javax.inject.Inject;
import java.io.IOException;

import static io.qameta.allure.Allure.step;


public class UsersTests extends AbstractTest<UsersTestModule> {

    @Inject
    private UsersTestsContext usersTestsContext;

    @Override
    protected UsersTestModule getModule() {
        return new UsersTestModule();
    }

    @Test
    public void postUserTest() {
        var user = UsersFactory.getUser();

        var postResponse = step("Create an user", () -> usersTestsContext.postUser(user)
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertHeadersContain(Header.create(HeaderNames.CONTENT_TYPE, "application/json; charset=utf-8"))
                .assertUser(user)
                .getResponseBody());

        var getResponse = step("Get an user", () -> usersTestsContext.getUserById(postResponse.getId())
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertHeadersContain(Header.create(HeaderNames.CONTENT_TYPE, "application/json; charset=utf-8"))
                .assertUser(user)
                .getResponseBody());
    }
}
