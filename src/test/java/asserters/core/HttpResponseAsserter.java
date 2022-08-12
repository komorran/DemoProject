package asserters.core;

import io.qameta.allure.Step;
import models.infrastructure.headers.Header;
import okhttp3.Headers;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttpResponseAsserter {
    @Step("Assert that status code is successful")
    public static void assertSuccessful(Response<?> response) {
        assertTrue(response.isSuccessful(), "Response wasn't successful");
    }

    @Step("Assert that response body is not null")
    public static <T> void assertResponseBodyIsNotNull(Response<T> response) {
        assertNotNull(response.body());
    }

    @Step("Assert that response headers contain specific header")
    public static void assertHeadersContain(Headers actual, Header expected) {
        var actualHeader = actual.get(expected.getName());
        assertEquals(actualHeader, expected.getValue());
    }
}
