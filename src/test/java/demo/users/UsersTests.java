package demo.users;

import com.google.common.net.HttpHeaders;
import demo.AbstractTest;
import demo.contexts.UsersApiContext;
import demo.models.factories.UsersFactory;
import demo.models.infrastructure.headers.Header;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.qameta.allure.Allure.step;

public class UsersTests extends AbstractTest {

    @Inject
    private UsersApiContext usersApiContext;

    @Test
    public void postUserTest() {
        var user = UsersFactory.getUser();

        var postResponse = step("Create an user", () -> usersApiContext.postUser(user)
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertHeadersContain(Header.create(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8"))
                .assertUser(user)
                .getResponseBody());

        var getResponse = step("Get an user", () -> usersApiContext.getUserById(postResponse.getId())
                .assertSuccessful()
                .assertResponseBodyIsNotNull()
                .assertHeadersContain(Header.create(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8"))
                .assertUser(user)
                .getResponseBody());
    }
}
