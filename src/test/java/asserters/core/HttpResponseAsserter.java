package asserters.core;

import io.qameta.allure.Step;
import lombok.Getter;
import models.infrastructure.headers.Header;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.*;

public class HttpResponseAsserter<T extends HttpResponseAsserter<T, R>, R> {
    @Getter
    private final Response<R> response;

    protected HttpResponseAsserter(Response<R> response) {
        this.response = response;
    }

    public R getResponseBody() {
        return getResponse().body();
    }

    @Step("Assert that status code is successful")
    public T assertSuccessful() {
        assertTrue(getResponse().isSuccessful(), "Response wasn't successful, current response status code: " + response.code());
        return (T) this;
    }

    @Step("Assert that response body is not null")
    public T assertResponseBodyIsNotNull() {
        assertNotNull(getResponseBody());
        return (T) this;
    }

    @Step("Assert that response headers contain specific header")
    public T assertHeadersContain(Header expected) {
        var actualHeader = getResponse().headers().get(expected.getName());
        assertEquals(actualHeader, expected.getValue());
        return (T) this;
    }
}
