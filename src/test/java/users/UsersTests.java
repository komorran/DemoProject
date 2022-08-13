package users;

import abstractions.AbstractTest;
import models.factories.UsersFactory;
import models.infrastructure.headers.Header;
import models.infrastructure.headers.HeaderNames;
import org.junit.jupiter.api.Test;
import users.modules.UsersTestModule;

import javax.inject.Inject;
import java.io.IOException;


public class UsersTests extends AbstractTest<UsersTestModule> {

    @Inject
    private UsersTestsContext usersTestsContext;

    @Override
    protected UsersTestModule getModule() {
        return new UsersTestModule();
    }

    @Test
    public void postUserTest() throws IOException {
        var user = UsersFactory.getUser();

        var postResponse = usersTestsContext.postUser(user)
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertHeadersContain(Header.create(HeaderNames.CONTENT_TYPE, "application/json; charset=utf-8"))
                .assertUser(user)
                .getResponseBody();

        var getResponse = usersTestsContext.getUserById(postResponse.getId())
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertHeadersContain(Header.create(HeaderNames.CONTENT_TYPE, "application/json; charset=utf-8"))
                .assertUser(user)
                .getResponseBody();
    }
}
