package users;

import abstractions.AbstractTest;
import models.factories.UsersFactory;
import models.infrastructure.headers.Header;
import models.infrastructure.headers.HeaderNames;
import org.junit.jupiter.api.Test;
import users.modules.UsersTestsModule;

import java.io.IOException;

import static asserters.core.HttpResponseAsserter.*;
import static asserters.users.UserAsserter.*;


public class UsersTests extends AbstractTest<UsersTestsModule, UsersTestsSteps> {
    @Override
    protected UsersTestsModule getModule() {
        return new UsersTestsModule();
    }

    @Test
    public void postUserTest() throws IOException {
        var user = UsersFactory.getUser();
        var postResponse = testSteps.postUser(user);

        assertSuccessful(postResponse);
        assertResponseBodyIsNotNull(postResponse);
        assertUser(user, postResponse.body());
        assertHeadersContain(postResponse.headers(), Header.create(HeaderNames.CONTENT_TYPE, "application/json; charset=utf-8"));

        var getResponse = testSteps.getUserById(postResponse.body().getId());
        assertSuccessful(getResponse);
        assertResponseBodyIsNotNull(getResponse);
        assertUser(user, getResponse.body());
        assertHeadersContain(getResponse.headers(), Header.create(HeaderNames.CONTENT_TYPE, "application/json; charset=utf-8"));
    }
}
