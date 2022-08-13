package demo.users;

import demo.AbstractTest;
import demo.contexts.UsersTestsContext;
import demo.models.factories.UsersFactory;
import demo.models.infrastructure.headers.Header;
import demo.models.infrastructure.headers.HeaderNames;
import org.junit.jupiter.api.Test;
import demo.modules.UsersTestModule;

import javax.inject.Inject;

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
