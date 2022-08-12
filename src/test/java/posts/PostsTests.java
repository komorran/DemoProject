package posts;

import abstractions.AbstractTest;
import models.infrastructure.headers.Header;
import models.infrastructure.headers.HeaderNames;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import posts.modules.PostsModule;

import java.io.IOException;

import static asserters.core.HttpResponseAsserter.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostsTests extends AbstractTest<PostsModule, PostsTestSteps> {
    @Override
    protected PostsModule getModule() {
        return new PostsModule();
    }

    @ParameterizedTest
    @ValueSource(ints = {1642})
    public void getPostTest(int id) throws IOException {
        var result = testSteps.getPostById(id);

        assertSuccessful(result);
        assertResponseBodyIsNotNull(result);
        assertEquals(3562, result.body().getUserId());
        assertHeadersContain(result.headers(), Header.create(HeaderNames.CONTENT_TYPE, "application/json; charset=utf-8"));
    }
}
