package demo.asserters.core;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.Getter;
import demo.models.infrastructure.headers.Header;
import okhttp3.Headers;
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

    public Headers getResponseHeaders() {
        return getResponse().headers();
    }

    @Step("Assert that status code is successful")
    public T assertSuccessful() {
        attachResponseData();
        assertTrue(getResponse().isSuccessful(), "Response wasn't successful, current response status code: " + response.code());
        return (T) this;
    }

    @Step("Assert that response body is not null")
    public T assertResponseBodyIsNotNull() {
        attachResponseData();
        assertNotNull(getResponseBody());
        return (T) this;
    }

    @Step("Assert that response headers contain specific header")
    public T assertHeadersContain(Header... expected) {
        attachResponseData();

        for (Header expectedHeader : expected) {
            var actualHeader = getResponseHeaders().get(expectedHeader.getName());

            assertNotNull(actualHeader, "Header not found: " + expectedHeader.getName());
            assertEquals(actualHeader, expectedHeader.getValue());
        }

        return (T) this;
    }

    protected void attachResponseData() {
        Allure.addAttachment("Response", String.valueOf(getResponse()));
        attachResponseBody();
        Allure.addAttachment("Response Headers", String.valueOf(getResponseHeaders()));
    }

    protected void attachResponseBody() {
        Allure.addAttachment("Response Body", String.valueOf(getResponseBody()));
    }
}
