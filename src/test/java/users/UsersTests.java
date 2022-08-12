package users;

import abstractions.AbstractTest;
import models.factories.UsersFactory;
import models.infrastructure.headers.Header;
import models.infrastructure.headers.HeaderNames;
import org.junit.jupiter.api.Test;
import users.modules.UsersTestsModule;

import java.io.IOException;


public class UsersTests extends AbstractTest<UsersTestsModule, UsersTestsSteps> {
    @Override
    protected UsersTestsModule getModule() {
        return new UsersTestsModule();
    }

    @Test
    public void postUserTest() throws IOException {
        var user = UsersFactory.getUser();

        var postResponse = testSteps.postUser(user)
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertHeadersContain(Header.create(HeaderNames.CONTENT_TYPE, "application/json; charset=utf-8"))
                .assertUser(user)
                .getResponse();

        var getResponse = testSteps.getUserById(postResponse.body().getId())
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertHeadersContain(Header.create(HeaderNames.CONTENT_TYPE, "application/json; charset=utf-8"))
                .assertUser(user)
                .getResponse();
    }
}
