package asserters.core;

import io.qameta.allure.Step;
import models.infrastructure.headers.Header;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttpResponseAsserter<T extends HttpResponseAsserter<T, R>, R> {

    protected Response<R> response;

    public T useResponse(Response<R> response) {
        this.response = response;
        return (T) this;
    }

    public Response<R> getResponse() {
        return response;
    }

    @Step("Assert that status code is successful")
    public T assertSuccessful() {
        assertTrue(this.response.isSuccessful(), "Response wasn't successful");
        return (T) this;
    }

    @Step("Assert that response body is not null")
    public T assertResponseBodyIsNotNull() {
        assertNotNull(this.response.body());
        return (T) this;
    }

    @Step("Assert that response headers contain specific header")
    public T assertHeadersContain(Header expected) {
        var actualHeader = response.headers().get(expected.getName());
        assertEquals(actualHeader, expected.getValue());
        return (T) this;
    }
}
